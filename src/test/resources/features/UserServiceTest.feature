# language: pt
Funcionalidade: Criar usuario padrao
  Ao inicializar o sistema, um usuario padrao deve ser criado

  Cenario: Cria o usuario padrao e valida os dados na base
    Dado a criacao do usuario padrao
    Entao verificar se o usuario esta na base de dados com os seguintes dados
      | nome          | email                    | password |
      | Administrador | douglasf.filho@gmail.com | admin    |