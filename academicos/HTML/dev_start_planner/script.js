/* ================================================
   DEVSTART PLANNER — script.js
   Projeto Final de Desenvolvimento de Interfaces
   ================================================

   SUMÁRIO:
   1.  Seletores
   2.  Dark / Light Mode
   3.  Meta da semana
   4.  Checklist diário
   5.  Roadmap de estudos
   6.  Projetos
   7.  Dashboard
   8.  Inicialização
   ================================================ */


/* ================================================
   1. SELETORES
   ================================================ */

// Tema
const htmlEl   = document.documentElement;
const btnTema  = document.getElementById('btn-tema');

// Meta
const formMeta      = document.getElementById('form-meta');
const inputMeta     = document.getElementById('input-meta');
const inputPrazo    = document.getElementById('input-prazo');
const metaSalva     = document.getElementById('meta-salva');
const metaTextoEl   = document.getElementById('meta-texto');
const metaPrazoEl   = document.getElementById('meta-prazo');
const btnEditarMeta = document.getElementById('btn-editar-meta');
const fillProgressoGeral = document.getElementById('fill-progresso-geral');
const pctProgressoGeral  = document.getElementById('pct-progresso-geral');

// Checklist
const listaTarefas       = document.getElementById('lista-tarefas');
const msgChecklistVazio  = document.getElementById('msg-checklist-vazio');
const progressoContagem  = document.getElementById('progresso-contagem');
const progressoPct       = document.getElementById('progresso-pct');
const fillChecklist      = document.getElementById('fill-checklist');
const barraChecklist     = document.getElementById('barra-checklist');
const inputNovaTarefa    = document.getElementById('nova-tarefa');
const selectCategoria    = document.getElementById('categoria-tarefa');
const btnAdicionarTarefa = document.getElementById('btn-adicionar-tarefa');

// Roadmap
const checkboxesRoadmap = document.querySelectorAll('#roadmap input[type="checkbox"]');

// Projetos
const formProjeto          = document.getElementById('form-projeto');
const listaProjetos        = document.getElementById('lista-projetos');
const msgProjetosVazio     = document.getElementById('msg-projetos-vazio');
const inputProjetoNome     = document.getElementById('projeto-nome');
const inputProjetoDesc     = document.getElementById('projeto-descricao');
const inputProjetoTechs    = document.getElementById('projeto-tecnologias');
const selectProjetoStatus  = document.getElementById('projeto-status');
const inputProjetoProgress = document.getElementById('projeto-progresso');

// Dashboard
const contagemTarefas  = document.getElementById('contagem-tarefas-dashboard');
const contagemTopicos  = document.getElementById('contagem-topicos-dashboard');
const contagemProjetos = document.getElementById('contagem-projetos-dashboard');

// Barras e percentuais do dashboard por área
const AREAS = {
  logica:     { fillDash: 'fill-logica',      pctDash: 'pct-logica',     fillRoadmap: 'fill-roadmap-logica',     progressoCard: 'progresso-logica' },
  poo:        { fillDash: 'fill-poo',         pctDash: 'pct-poo',        fillRoadmap: 'fill-roadmap-poo',        progressoCard: 'progresso-poo' },
  interfaces: { fillDash: 'fill-interfaces',  pctDash: 'pct-interfaces', fillRoadmap: 'fill-roadmap-interfaces', progressoCard: 'progresso-interfaces' },
  banco:      { fillDash: 'fill-banco',       pctDash: 'pct-banco',      fillRoadmap: 'fill-roadmap-banco',      progressoCard: 'progresso-banco' },
  git:        { fillDash: 'fill-git',         pctDash: 'pct-git',        fillRoadmap: 'fill-roadmap-git',        progressoCard: 'progresso-git' }
};


/* ================================================
   2. DARK / LIGHT MODE
   ================================================ */

function aplicarTema(tema) {
  htmlEl.setAttribute('data-tema', tema);
  btnTema.textContent = tema === 'dark' ? '☀️' : '🌙';
  btnTema.setAttribute('aria-label', tema === 'dark' ? 'Ativar modo claro' : 'Ativar modo escuro');
  localStorage.setItem('devstart-tema', tema);
}

function inicializarTema() {
  const salvo       = localStorage.getItem('devstart-tema');
  const prefereEscuro = window.matchMedia('(prefers-color-scheme: dark)').matches;
  aplicarTema(salvo || (prefereEscuro ? 'dark' : 'light'));
}

btnTema.addEventListener('click', () => {
  const atual = htmlEl.getAttribute('data-tema');
  aplicarTema(atual === 'dark' ? 'light' : 'dark');
});


/* ================================================
   3. META DA SEMANA
   ================================================ */

function formatarData(str) {
  if (!str) return '';
  const d = new Date(str + 'T00:00:00');
  return d.toLocaleDateString('pt-BR', { weekday: 'long', day: '2-digit', month: 'long', year: 'numeric' });
}

function exibirMetaSalva(meta, prazo) {
  formMeta.hidden  = true;
  metaSalva.hidden = false;
  metaTextoEl.textContent = `🎯 ${meta}`;
  metaPrazoEl.textContent = prazo ? `📅 Prazo: ${formatarData(prazo)}` : '';
}

function mostrarFormMeta() {
  metaSalva.hidden = true;
  formMeta.hidden  = false;
  inputMeta.focus();
}

function carregarMeta() {
  const dados = localStorage.getItem('devstart-meta');
  if (!dados) return;
  const { meta, prazo } = JSON.parse(dados);
  if (meta) {
    inputMeta.value  = meta;
    inputPrazo.value = prazo || '';
    exibirMetaSalva(meta, prazo);
  }
}

formMeta.addEventListener('submit', (e) => {
  e.preventDefault();
  const meta  = inputMeta.value.trim();
  const prazo = inputPrazo.value;
  if (!meta) { inputMeta.focus(); return; }
  localStorage.setItem('devstart-meta', JSON.stringify({ meta, prazo }));
  exibirMetaSalva(meta, prazo);
});

btnEditarMeta.addEventListener('click', mostrarFormMeta);


/* ================================================
   4. CHECKLIST DIÁRIO
   ================================================ */

function carregarTarefas() {
  const dados = localStorage.getItem('devstart-tarefas');
  return dados ? JSON.parse(dados) : [];
}

function salvarTarefas(tarefas) {
  localStorage.setItem('devstart-tarefas', JSON.stringify(tarefas));
}

function atualizarProgressoChecklist() {
  const tarefas    = carregarTarefas();
  const total      = tarefas.length;
  const concluidas = tarefas.filter(t => t.concluida).length;
  const pct        = total === 0 ? 0 : Math.round((concluidas / total) * 100);

  progressoContagem.textContent = `${concluidas} / ${total}`;
  progressoPct.textContent      = `${pct}%`;
  fillChecklist.style.width     = `${pct}%`;
  barraChecklist.setAttribute('aria-valuenow', pct);

  // Atualiza barra geral no dashboard
  if (fillProgressoGeral) fillProgressoGeral.style.width = `${pct}%`;
  if (pctProgressoGeral)  pctProgressoGeral.textContent  = `${pct}%`;
}

function verificarChecklistVazio() {
  msgChecklistVazio.hidden = carregarTarefas().length > 0;
}

function criarItemTarefa(tarefa) {
  const li = document.createElement('li');
  li.dataset.id = tarefa.id;
  if (tarefa.concluida) li.classList.add('concluida');

  const checkbox = document.createElement('input');
  checkbox.type    = 'checkbox';
  checkbox.id      = `check-${tarefa.id}`;
  checkbox.checked = tarefa.concluida;
  checkbox.setAttribute('aria-label', `Marcar "${tarefa.texto}" como concluída`);

  const label = document.createElement('label');
  label.htmlFor     = `check-${tarefa.id}`;
  label.textContent = tarefa.texto;

  const badge = document.createElement('span');
  badge.className   = 'badge-categoria';
  badge.textContent = tarefa.categoria || '';
  badge.hidden      = !tarefa.categoria;

  const btnRemover = document.createElement('button');
  btnRemover.className   = 'btn-perigo btn-sm';
  btnRemover.textContent = 'Remover';
  btnRemover.setAttribute('aria-label', `Remover tarefa: ${tarefa.texto}`);

  checkbox.addEventListener('change', () => {
    tarefa.concluida = checkbox.checked;
    li.classList.toggle('concluida', tarefa.concluida);
    const lista = carregarTarefas();
    const idx   = lista.findIndex(t => t.id === tarefa.id);
    if (idx !== -1) lista[idx].concluida = tarefa.concluida;
    salvarTarefas(lista);
    atualizarProgressoChecklist();
    atualizarDashboard();
  });

  btnRemover.addEventListener('click', () => {
    const lista = carregarTarefas().filter(t => t.id !== tarefa.id);
    salvarTarefas(lista);
    li.remove();
    atualizarProgressoChecklist();
    atualizarDashboard();
    verificarChecklistVazio();
  });

  li.append(checkbox, label, badge, btnRemover);
  return li;
}

function renderizarTarefas() {
  listaTarefas.innerHTML = '';
  carregarTarefas().forEach(t => listaTarefas.appendChild(criarItemTarefa(t)));
  atualizarProgressoChecklist();
  verificarChecklistVazio();
}

function adicionarTarefa() {
  const texto     = inputNovaTarefa.value.trim();
  const categoria = selectCategoria.value;
  if (!texto) { inputNovaTarefa.focus(); return; }

  const nova = { id: Date.now().toString(), texto, categoria, concluida: false };
  const lista = carregarTarefas();
  lista.push(nova);
  salvarTarefas(lista);

  listaTarefas.appendChild(criarItemTarefa(nova));
  inputNovaTarefa.value = '';
  selectCategoria.value = '';
  inputNovaTarefa.focus();

  atualizarProgressoChecklist();
  atualizarDashboard();
  verificarChecklistVazio();
}

btnAdicionarTarefa.addEventListener('click', adicionarTarefa);
inputNovaTarefa.addEventListener('keydown', (e) => { if (e.key === 'Enter') { e.preventDefault(); adicionarTarefa(); } });


/* ================================================
   5. ROADMAP DE ESTUDOS
   ================================================ */

function carregarRoadmap() {
  const dados = localStorage.getItem('devstart-roadmap');
  return dados ? JSON.parse(dados) : {};
}

function salvarRoadmap(estado) {
  localStorage.setItem('devstart-roadmap', JSON.stringify(estado));
}

function atualizarProgressoArea(area) {
  const cbs     = document.querySelectorAll(`input[data-area="${area}"]`);
  const total   = cbs.length;
  const marcados = [...cbs].filter(cb => cb.checked).length;
  const pct     = total === 0 ? 0 : Math.round((marcados / total) * 100);

  // Texto no card do roadmap
  const cardTexto = document.getElementById(AREAS[area].progressoCard);
  if (cardTexto) {
    cardTexto.textContent = `${marcados} / ${total} concluídos`;
    cardTexto.setAttribute('aria-valuenow', pct);
  }

  // Barra no card do roadmap
  const fillRoadmap = document.getElementById(AREAS[area].fillRoadmap);
  if (fillRoadmap) fillRoadmap.style.width = `${pct}%`;

  // Barra e percentual no dashboard
  const fillDash = document.getElementById(AREAS[area].fillDash);
  const pctDash  = document.getElementById(AREAS[area].pctDash);
  if (fillDash) fillDash.style.width    = `${pct}%`;
  if (pctDash)  {
    pctDash.textContent = `${pct}%`;
    pctDash.setAttribute('aria-valuenow', pct);
  }
}

function inicializarRoadmap() {
  const estado = carregarRoadmap();

  checkboxesRoadmap.forEach(cb => {
    if (estado[cb.id]) cb.checked = true;

    cb.addEventListener('change', () => {
      const estadoAtual = carregarRoadmap();
      estadoAtual[cb.id] = cb.checked;
      salvarRoadmap(estadoAtual);
      atualizarProgressoArea(cb.dataset.area);
      atualizarDashboard();
    });
  });

  Object.keys(AREAS).forEach(area => atualizarProgressoArea(area));
}


/* ================================================
   6. PROJETOS
   ================================================ */

function carregarProjetos() {
  const dados = localStorage.getItem('devstart-projetos');
  return dados ? JSON.parse(dados) : [];
}

function salvarProjetos(projetos) {
  localStorage.setItem('devstart-projetos', JSON.stringify(projetos));
}

const STATUS_LABEL = { planejado: 'Planejado', 'em-andamento': 'Em andamento', concluido: 'Concluído' };

function criarCardProjeto(projeto) {
  const pct = Math.min(100, Math.max(0, parseInt(projeto.progresso) || 0));

  const techsBadges = projeto.tecnologias
    ? projeto.tecnologias.split(',').map(t => t.trim()).filter(Boolean)
        .map(t => `<span class="badge-tech">${t}</span>`).join('')
    : '';

  const article = document.createElement('article');
  article.className = 'card projeto-card';
  article.setAttribute('aria-label', `Projeto: ${projeto.nome}`);
  article.dataset.id = projeto.id;

  article.innerHTML = `
    <div class="projeto-card__topo">
      <h3>${projeto.nome}</h3>
      <span class="badge-status badge-status--${projeto.status}">${STATUS_LABEL[projeto.status] || projeto.status}</span>
    </div>
    ${projeto.descricao ? `<p class="projeto-card__desc">${projeto.descricao}</p>` : ''}
    ${techsBadges ? `<div class="techs-lista">${techsBadges}</div>` : ''}
    <div class="projeto-progresso-label">
      <span>Progresso</span><span>${pct}%</span>
    </div>
    <div class="barra-trilha barra-trilha--sm" role="progressbar" aria-valuenow="${pct}" aria-valuemin="0" aria-valuemax="100" aria-label="Progresso do projeto: ${pct}%">
      <div class="barra-fill barra-fill--gradiente" style="width:${pct}%"></div>
    </div>
    <div class="projeto-card__acoes">
      <button class="btn-perigo btn-sm" aria-label="Remover projeto ${projeto.nome}">🗑 Remover</button>
    </div>
  `;

  article.querySelector('.btn-perigo').addEventListener('click', () => {
    const lista = carregarProjetos().filter(p => p.id !== projeto.id);
    salvarProjetos(lista);
    article.remove();
    atualizarDashboard();
    verificarProjetosVazio();
  });

  return article;
}

function verificarProjetosVazio() {
  msgProjetosVazio.hidden = carregarProjetos().length > 0;
}

function renderizarProjetos() {
  listaProjetos.innerHTML = '';
  carregarProjetos().forEach(p => listaProjetos.appendChild(criarCardProjeto(p)));
  verificarProjetosVazio();
}

formProjeto.addEventListener('submit', (e) => {
  e.preventDefault();
  const nome = inputProjetoNome.value.trim();
  if (!nome) { inputProjetoNome.focus(); return; }

  const novo = {
    id:          Date.now().toString(),
    nome,
    descricao:   inputProjetoDesc.value.trim(),
    tecnologias: inputProjetoTechs.value.trim(),
    status:      selectProjetoStatus.value,
    progresso:   inputProjetoProgress.value || 0
  };

  const lista = carregarProjetos();
  lista.push(novo);
  salvarProjetos(lista);

  listaProjetos.appendChild(criarCardProjeto(novo));
  formProjeto.reset();
  inputProjetoNome.focus();

  atualizarDashboard();
  verificarProjetosVazio();
});


/* ================================================
   7. DASHBOARD
   ================================================ */

function atualizarDashboard() {
  // Tarefas
  const tarefas    = carregarTarefas();
  const concluidas = tarefas.filter(t => t.concluida).length;
  contagemTarefas.textContent = `${concluidas} / ${tarefas.length}`;

  // Progresso geral (baseado nas tarefas)
  const pctGeral = tarefas.length === 0 ? 0 : Math.round((concluidas / tarefas.length) * 100);
  if (fillProgressoGeral) fillProgressoGeral.style.width = `${pctGeral}%`;
  if (pctProgressoGeral)  pctProgressoGeral.textContent  = `${pctGeral}%`;

  // Tópicos do roadmap
  const estado   = carregarRoadmap();
  const marcados = Object.values(estado).filter(Boolean).length;
  contagemTopicos.textContent = `${marcados} / ${checkboxesRoadmap.length}`;

  // Projetos
  contagemProjetos.textContent = carregarProjetos().length;
}


/* ================================================
   8. INICIALIZAÇÃO
   ================================================ */

document.addEventListener('DOMContentLoaded', () => {
  inicializarTema();
  carregarMeta();
  renderizarTarefas();
  inicializarRoadmap();
  renderizarProjetos();
  atualizarDashboard();
});