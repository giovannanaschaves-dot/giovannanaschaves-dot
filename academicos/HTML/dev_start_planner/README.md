# DevStart Planner - Organizador de Estudos para Iniciantes em Tecnologia

Plataforma web responsiva desenvolvida para auxiliar estudantes iniciantes em tecnologia e programação a organizarem seus estudos, metas e projetos. Desenvolvida como projeto final da disciplina de Desenvolvimento de Interfaces, aplicando metodologia Design Thinking, princípios de UI/UX e acessibilidade WCAG.

## 🚀 Tecnologias

* HTML5 Semântico
* CSS3 (Custom Properties, Flexbox, Grid)
* JavaScript (ES6+)
* LocalStorage API

## 📚 O que aprendi

* Estruturação semântica com elementos HTML5 (`<main>`, `<section>`, `<nav>`, `<article>`)
* Acessibilidade web com atributos ARIA, skip links e navegação por teclado
* Conformidade com diretrizes WCAG
* Criação de temas claro e escuro com CSS Custom Properties
* Layout responsivo com três breakpoints (1024px, 768px e 480px)
* Persistência de dados no navegador com LocalStorage
* Detecção de preferência de tema do sistema operacional via `prefers-color-scheme`
* Rastreamento de progresso em tempo real no checklist diário
* Atualização dinâmica de percentuais no dashboard via checkboxes do roadmap
* Renderização dinâmica de cards de projetos a partir de formulário interativo
* Aplicação prática de metodologia Design Thinking no desenvolvimento de interfaces

## 📁 Estrutura do Projeto

```
devstart-planner/
├── index.html
├── style.css
├── script.js
└── README.md
```

## 🔹 Funcionalidades

### Dashboard
* Visão geral do progresso de estudos
* Percentuais atualizados dinamicamente conforme interações do usuário
* Cards de resumo para checklist, roadmap e projetos

### Checklist Diário
* Adição e remoção de tarefas do dia
* Marcação de tarefas concluídas com atualização em tempo real
* Progresso salvo automaticamente via LocalStorage

### Roadmap de Estudos
* Organização de tópicos e habilidades a aprender
* Checkboxes que atualizam o percentual de progresso no dashboard
* Dados persistidos entre sessões

### Gerenciamento de Projetos
* Formulário para cadastro de projetos com nome, descrição e status
* Renderização dinâmica de cards de projetos
* Edição e remoção de projetos cadastrados

### Acessibilidade
* Modo claro/escuro com alternância manual e detecção automática do sistema
* Skip links para navegação eficiente por teclado
* Atributos ARIA em todos os componentes interativos
* Paleta de cores com contraste adequado em ambos os modos

## ▶️ Como executar

1. Clone ou baixe o repositório
2. Abra o arquivo `index.html` diretamente no navegador

Não é necessário servidor ou dependências externas — o projeto roda inteiramente no cliente.

## 📐 Responsividade

O layout se adapta a três breakpoints principais:

| Breakpoint | Contexto |
|------------|----------|
| 1024px | Tablets e telas menores |
| 768px | Dispositivos móveis em modo paisagem |
| 480px | Smartphones em modo retrato |

## 📌 Observações

O projeto foi desenvolvido durante a disciplina de Desenvolvimento de Interfaces, com entregas incrementais orientadas por fases: estrutura HTML, estilização CSS e interatividade JavaScript. O foco esteve na aplicação prática de boas práticas de UI/UX, acessibilidade e experiência do usuário para o público iniciante em tecnologia.
