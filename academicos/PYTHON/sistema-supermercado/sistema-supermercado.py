# Importa o módulo mysql.connector que permite conectar Python com MySQL
import mysql.connector
# Importa especificamente a classe Error do módulo para tratamento de exceções
from mysql.connector import Error

# Define uma constante com o nome do banco de dados que será criado/usado
DB_NAME = "bd_supermercado"

# Função para conectar ao servidor MySQL (sem selecionar um banco específico)
def conectar():
    try:  # Inicia bloco de tentativa (pode ocorrer erro)
        # Tenta estabelecer conexão com o servidor MySQL local
        # host="localhost" = servidor na própria máquina
        # user="root" = usuário administrador do MySQL
        # password="123" = senha do usuário root
        conn = mysql.connector.connect(host="localhost", user="root", password="123")
        return conn  # Retorna o objeto de conexão se bem-sucedido
    except Error as e:  # Captura qualquer erro específico do MySQL
        # Exibe mensagem de erro formatada com o detalhe do erro
        print(f"Erro ao conectar: {e}")
        return None  # Retorna None indicando falha na conexão

# Função para criar o banco de dados se ele não existir
def criar_banco():
    # Chama a função conectar() para obter conexão com o servidor
    conn = conectar()
    # Verifica se a conexão falhou (retornou None)
    if not conn:
        return  # Sai da função sem fazer nada se não conseguiu conectar
    # Cria um cursor (objeto que executa comandos SQL)
    cursor = conn.cursor()
    # Executa comando SQL para criar banco de dados
    # CREATE DATABASE = comando para criar banco
    # IF NOT EXISTS = só cria se não existir (evita erro)
    # {DB_NAME} = substitui pela constante com o nome do banco
    cursor.execute(f"CREATE DATABASE IF NOT EXISTS {DB_NAME}")
    # Fecha o cursor para liberar recursos
    cursor.close()
    # Fecha a conexão com o servidor
    conn.close()

# Função para conectar especificamente ao banco de dados do supermercado
def conectar_bd():
    try:  # Inicia bloco de tentativa
        # Tenta conectar ao banco específico
        # host, user, password são os mesmos
        # database=DB_NAME = seleciona o banco bd_supermercado
        conn = mysql.connector.connect(
            host="localhost", user="root", password="ceub123456", database=DB_NAME
        )
        return conn  # Retorna conexão bem-sucedida
    except Error as e:  # Captura erros de conexão
        # Exibe mensagem de erro específica para conexão ao banco
        print(f"Erro ao conectar ao banco: {e}")
        return None  # Retorna None em caso de falha

# Função para criar a tabela de produtos se ela não existir
def criar_tabela():
    # Obtém conexão com o banco de dados específico
    conn = conectar_bd()
    # Verifica se conseguiu conectar
    if not conn:
        return  # Sai da função se não há conexão
    # Cria cursor para executar comandos SQL
    cursor = conn.cursor()
    # Executa comando SQL para criar tabela
    cursor.execute("""
        CREATE TABLE IF NOT EXISTS tb_produto (  -- Cria tabela se não existir
            id INT AUTO_INCREMENT PRIMARY KEY,   -- ID único, automático, chave primária
            nome VARCHAR(100) NOT NULL,          -- Nome do produto, máximo 100 caracteres, obrigatório
            preco DECIMAL(10,2) NOT NULL,        -- Preço com 10 dígitos, sendo 2 decimais, obrigatório
            quantidade INT NOT NULL,             -- Quantidade em estoque, inteiro, obrigatório
            categoria VARCHAR(50)                -- Categoria, até 50 caracteres, opcional
        )
    """)
    # Confirma a criação da tabela no banco de dados
    conn.commit()
    # Fecha o cursor
    cursor.close()
    # Fecha a conexão
    conn.close()

# Função para adicionar um novo produto no banco de dados
def ler_float(mensagem):
    while True:
        try:
            valor = float(input(mensagem))
            return valor
        except ValueError:
            print("Entrada inválida! Digite um número decimal (ex: 19.90).")

def ler_int(mensagem):
    while True:
        try:
            valor = int(input(mensagem))
            return valor
        except ValueError:
            print("Entrada inválida! Digite um número inteiro.")

def adicionar():
    # Exibe cabeçalho da operação com quebras de linha
    print("\n--- ADICIONAR PRODUTO ---")
    # Solicita e armazena o nome do produto (string)
    while True:
        nome = input("Nome: ").strip()
        if nome:
            break
        print("O nome do produto não pode ficar vazio.")
    # Solicita preço com validação
    preco = ler_float("Preço: ")
    while preco <= 0:
        print("O preço deve ser maior que zero.")
        preco = ler_float("Preço: ")
    # Solicita quantidade com validação
    qtd = ler_int("Quantidade: ")
    while qtd < 0:
        print("A quantidade não pode ser negativa.")
        qtd = ler_int("Quantidade: ")
    # Solicita categoria (string)
    cat = input("Categoria: ").strip()
    # Conecta ao banco de dados
    conn = conectar_bd()
    # Verifica se a conexão foi bem-sucedida
    if not conn:
        return  # Sai da função se não conseguiu conectar
    # Cria cursor para executar comandos
    cursor = conn.cursor()
    # Define o comando SQL de inserção
    # %s são placeholders para evitar SQL injection
    sql = "INSERT INTO tb_produto (nome, preco, quantidade, categoria) VALUES (%s, %s, %s, %s)"
    # Executa o SQL passando os valores como tupla
    cursor.execute(sql, (nome, preco, qtd, cat))
    # Confirma a inserção no banco de dados
    conn.commit()
    # Exibe mensagem de sucesso com o ID gerado automaticamente
    # cursor.lastrowid retorna o último ID inserido
    print(f"Produto '{nome}' adicionado com sucesso! (ID: {cursor.lastrowid})")
    # Fecha o cursor
    cursor.close()
    # Fecha a conexão
    conn.close()

# Função para listar todos os produtos cadastrados
def listar():
    # Exibe cabeçalho
    print("\n--- LISTAR PRODUTOS ---")
    # Conecta ao banco
    conn = conectar_bd()
    # Verifica conexão
    if not conn:
        return
    # Cria cursor
    cursor = conn.cursor()
    # Executa SELECT para buscar todos os produtos ordenados por ID
    cursor.execute("SELECT id, nome, preco, quantidade, categoria FROM tb_produto ORDER BY id")
    # Recupera todos os resultados da consulta
    rows = cursor.fetchall()
    # Verifica se não há produtos (lista vazia)
    if not rows:
        print("Nenhum produto cadastrado.")
    else:
        # Exibe cabeçalho da tabela formatada
        # :<5 = alinhamento esquerdo com 5 espaços
        print(f"{'ID':<5} {'NOME':<25} {'PREÇO':<10} {'QTD':<6} {'CATEGORIA':<20}")
        # Linha separadora de 70 traços
        print("-" * 70)
        # Loop para percorrer cada linha de resultado
        for r in rows:
            # Exibe cada campo formatado
            # r[0]=ID, r[1]=nome, r[2]=preco, r[3]=quantidade, r[4]=categoria
            # .2f = formata preço com 2 casas decimais
            print(f"{r[0]:<5} {r[1]:<25} {r[2]:<10.2f} {r[3]:<6} {r[4]:<20}")
    # Fecha cursor
    cursor.close()
    # Fecha conexão
    conn.close()

# Função para atualizar dados de um produto existente
def atualizar():
    # Exibe cabeçalho
    print("\n--- ATUALIZAR PRODUTO ---")
    # Chama a função listar para mostrar todos os produtos
    listar()
    id_prod = ler_int("\nID do produto a atualizar: ")
    # Conecta ao banco
    conn = conectar_bd()
    if not conn:
        return
    # Cria cursor
    cursor = conn.cursor()
    # Busca o produto pelo ID
    cursor.execute("SELECT * FROM tb_produto WHERE id = %s", (id_prod,))
    # Recupera o primeiro resultado (fetchone)
    produto = cursor.fetchone()
    # Verifica se o produto existe
    if not produto:
        print("Produto não encontrado.")
        cursor.close()
        conn.close()
        return
    # Informa que pode deixar em branco para manter valor atual
    print("Deixe em branco para manter o valor atual.")
    # Solicita novo nome, se vazio mantém o atual (produto[1])
    nome = input(f"Nome [{produto[1]}]: ").strip() or produto[1]
    # Solicita novo preço com validação
    while True:
        preco_input = input(f"Preço [{produto[2]}]: ").strip()
        if not preco_input:
            preco = produto[2]
            break
        try:
            preco = float(preco_input)
            if preco <= 0:
                print("O preço deve ser maior que zero.")
                continue
            break
        except ValueError:
            print("Entrada inválida! Digite um número decimal (ex: 19.90).")
    # Solicita nova quantidade com validação
    while True:
        qtd_input = input(f"Quantidade [{produto[3]}]: ").strip()
        if not qtd_input:
            qtd = produto[3]
            break
        try:
            qtd = int(qtd_input)
            if qtd < 0:
                print("A quantidade não pode ser negativa.")
                continue
            break
        except ValueError:
            print("Entrada inválida! Digite um número inteiro.")
    # Solicita nova categoria, se vazio mantém atual (produto[4])
    cat = input(f"Categoria [{produto[4]}]: ").strip() or produto[4]
    # Comando SQL para atualizar o produto
    sql = "UPDATE tb_produto SET nome=%s, preco=%s, quantidade=%s, categoria=%s WHERE id=%s"
    # Executa a atualização com os novos valores
    cursor.execute(sql, (nome, preco, qtd, cat, id_prod))
    # Confirma a alteração
    conn.commit()
    print("Produto atualizado com sucesso!")
    cursor.close()
    conn.close()

# Função para excluir um produto do banco de dados
def excluir():
    # Exibe cabeçalho
    print("\n--- EXCLUIR PRODUTO ---")
    # Lista todos os produtos para o usuário escolher
    listar()
    id_prod = ler_int("\nID do produto a excluir: ")
    # Conecta ao banco
    conn = conectar_bd()
    if not conn:
        return
    # Cria cursor
    cursor = conn.cursor()
    # Busca apenas o nome do produto pelo ID
    cursor.execute("SELECT nome FROM tb_produto WHERE id = %s", (id_prod,))
    # Recupera o resultado
    produto = cursor.fetchone()
    # Verifica se produto existe
    if not produto:
        print("Produto não encontrado.")
        cursor.close()
        conn.close()
        return
    # Solicita confirmação do usuário (s/N = sim/Não)
    conf = input(f"Tem certeza que deseja excluir '{produto[0]}' (ID {id_prod})? (s/N): ")
    # Verifica se usuário digitou 's' (maiúsculo ou minúsculo)
    if conf.lower() == "s":
        # Executa comando DELETE para remover o produto
        cursor.execute("DELETE FROM tb_produto WHERE id = %s", (id_prod,))
        # Confirma a exclusão
        conn.commit()
        print("Produto excluído com sucesso!")
    else:
        print("Operação cancelada.")
    cursor.close()
    conn.close()

# Função para buscar produtos por nome (ou parte do nome)
def buscar():
    # Exibe cabeçalho
    print("\n--- BUSCAR PRODUTO ---")
    # Solicita o termo de busca
    termo = input("Digite nome ou parte do nome: ")
    # Conecta ao banco
    conn = conectar_bd()
    if not conn:
        return
    # Cria cursor
    cursor = conn.cursor()
    # Comando SQL com LIKE para busca parcial
    # %s é o placeholder para o termo
    sql = "SELECT id, nome, preco, quantidade, categoria FROM tb_produto WHERE nome LIKE %s ORDER BY id"
    # Executa com %termo% (qualquer posição que contenha o termo)
    cursor.execute(sql, (f"%{termo}%",))
    # Recupera todos os resultados
    rows = cursor.fetchall()
    # Verifica se encontrou algo
    if not rows:
        print("Nenhum produto encontrado.")
    else:
        # Exibe resultados formatados (mesmo formato do listar)
        print(f"\n{'ID':<5} {'NOME':<25} {'PREÇO':<10} {'QTD':<6} {'CATEGORIA':<20}")
        print("-" * 70)
        for r in rows:
            print(f"{r[0]:<5} {r[1]:<25} {r[2]:<10.2f} {r[3]:<6} {r[4]:<20}")
    cursor.close()
    conn.close()

# Função principal que exibe o menu e gerencia as opções
def menu():
    # Loop infinito (só sai quando escolhe opção 0)
    while True:
        # Exibe título do sistema
        print("\n===== SISTEMA DE SUPERMERCADO =====")
        # Exibe opções disponíveis
        print("1 - Adicionar produto")
        print("2 - Listar produtos")
        print("3 - Atualizar produto")
        print("4 - Excluir produto")
        print("5 - Buscar produto")
        print("0 - Sair")
        # Solicita a opção do usuário
        op = input("Escolha uma opção: ")
        if op == "1":
            adicionar()  
        elif op == "2":
            listar()     
        elif op == "3":
            atualizar()  
        elif op == "4":
            excluir()   
        elif op == "5":
            buscar()     
        elif op == "0":
            print("Encerrando sistema...")
            break
        else:
            print("Opção inválida. Tente novamente.")  # Opção não reconhecida

# Verifica se este script está sendo executado diretamente (não importado)
if __name__ == "__main__":
    criar_banco()   # Cria o banco de dados se não existir
    criar_tabela()  # Cria a tabela de produtos se não existir
    menu()          # Inicia o sistema com o menu interativo
