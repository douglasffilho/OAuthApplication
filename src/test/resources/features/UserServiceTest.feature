# language: pt
Funcionalidade: Criar um usuario
  Testar a criacao de um usuario na base de dados

  Cenario: Cria o usuario e valida os dados na base
    Dado a criacao do usuario com as seguintes informacoes
      | nome  | email                    | password | phone       | role      |
      | Teste | douglasf.teste@gmail.com | admin    | 81996729491 | ROLE_TEST |
    Entao verificar se o usuario esta na base de dados com os mesmo dados