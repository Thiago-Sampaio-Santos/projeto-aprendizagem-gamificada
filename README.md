# Projeto Aprendizagem Gamificada

Este projeto Java tem como objetivo promover a aprendizagem por meio de elementos de gamificação, desafios interativos e acompanhamento de desempenho. A arquitetura foi construída com base em diversos padrões de projeto para garantir modularidade, escalabilidade e manutenção eficiente.

---

## Arquitetura do Sistema

O sistema é dividido em módulos funcionais, cada um responsável por uma parte da lógica da aplicação. A arquitetura segue princípios de modularidade e reutilização, com aplicação dos seguintes padrões:

- **Factory**: Criação de perfis de usuário.
- **Singleton**: Gerenciamento de sessão e configuração.
- **Strategy**: Cálculo de pontuação em desafios.
- **Observer**: Notificações de eventos.
- **Composite**: Agrupamento de medalhas.
- **Decorator**: Aplicação de bônus em conquistas.
- **Adapter**: Integração simulada com ranking externo.
- **Command**: Registro e reversão de ações do usuário.
- **Facade**: Centralização da geração de relatórios.

A entrada principal do sistema é feita pela classe `App.java`, que inicializa a sessão e direciona o fluxo de execução.

---

## Estrutura do Projeto

```plaintext
projeto-aprendizagem-gamificada/
│
├── src/
│ ├── app/
│ │ ├── App.java
│ │ └── ConfiguracaoSingleton.java
│ │
│ ├── usuarios/
│ │ ├── Usuario.java
│ │ ├── Aluno.java
│ │ ├── Professor.java
│ │ ├── Visitante.java
│ │ ├── PerfilFactory.java
│ │ └── SessaoUsuarioSingleton.java
│ │
│ ├── desafios/
│ │ ├── Desafio.java
│ │ ├── Quiz.java
│ │ ├── ExercicioCodigo.java
│ │ ├── PontuacaoStrategy.java
│ │ ├── PontuacaoPorTempo.java
│ │ ├── PontuacaoPorDificuldade.java
│ │ ├── PontuacaoPorAcerto.java
│ │ ├── Observador.java
│ │ ├── Sujeito.java
│ │ └── NotificacaoObserver.java
│ │
│ ├── gamificacao/
│ │ ├── Conquista.java
│ │ ├── Medalha.java
│ │ ├── ConjuntoMedalhasComposite.java
│ │ ├── BonusDecorator.java
│ │ ├── StreakBonus.java
│ │ └── DoubleXPBonus.java
│ │
│ ├── relatorios/
│ │ ├── RelatorioFacade.java
│ │ ├── Relatorio.java
│ │ ├── RelatorioPDF.java
│ │ ├── RelatorioCSV.java
│ │ ├── RelatorioJSON.java
│ │ ├── RankingAdapter.java
│ │ └── ServicoRankingExterno.java
│ │
│ ├── historico/
│ │ ├── Acao.java
│ │ ├── ResponderQuizCommand.java
│ │ ├── DesbloquearMedalhaCommand.java
│ │ ├── RegistroInteracao.java
│ │ └── HistoricoInteracoes.java
│ │
│ └── util/
│ └── Log.java
│
├── docs/
│ ├── diagramas/
│ │ ├── diagrama-classes.puml
│ │ └── diagrama-sequencia.puml
│ └── relatorio-tecnico.pdf
│
├── README.md
└── .gitignore
```

---

## Descrição dos Módulos

- **app/**: Entrada principal do sistema e configuração global.
- **usuarios/**: Representa os perfis de usuários e gerencia sessões.
  - Padrões: *Factory*, *Singleton*
- **desafios/**: Tipos de desafios e lógica de pontuação.
  - Padrões: *Strategy*, *Observer*
- **gamificacao/**: Elementos de gamificação como medalhas e bônus.
  - Padrões: *Composite*, *Decorator*
- **relatorios/**: Geração de relatórios e integração com rankings externos.
  - Padrões: *Facade*, *Adapter*
- **historico/**: Registro de ações e interações dos usuários.
  - Padrões: *Command*
- **util/**: Utilitários diversos como logging.
- **docs/**: Documentação técnica e diagramas UML.

---

## Tecnologias Utilizadas

- Java 17+
- VS Code
- PlantUML (para diagramas)
- Git

---

## Execução

Para executar o projeto:

```bash
javac src/app/Main.java
java src/app/Main
