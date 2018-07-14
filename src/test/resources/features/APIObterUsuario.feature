## language: pt
#Funcionalidade: Testar se ha usuario cadastrado com os dados a seguir.
#  O sistema deve obter o usuario atraves de seu nome e validar seu email.
#
#  Delineacao do Cenario: Obter usuario por nome e validar email
#	Dado usuario com nome "<nome>"
#	Entao verifica seu email "<email>"
#
#	Exemplos:
#	  | nome          | email                    |
#	  | Administrador | douglasf.filho@gmail.com |
#
#  Cenario: Obter lista de usuarios cadastrados no sistema
#	Dado uma requisicao GET contra a API
#	Entao obter lista de usuarios
#	E verificar se o usuario com id 1 e nome "Administrador" esta na lista