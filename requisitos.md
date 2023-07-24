# Requisitos do Aplicativo

## Usuário do Aplicativo
1. Novo cadastro de usuário (endereço de e-mail único)
    * Verificação de conta (verificar endereço de e-mail)
    * Imagem de perfil do usuário
    * Detalhes do usuário (nome, e-mail, cargo, biografia, **telefone**, endereço, etc.)
    * Capacidade de atualizar informações do usuário

2. Redefinir senha do usuário (sem estar logado)
    * O link de redefinição de senha deve expirar em 24 horas

3. Login do usuário (usando e-mail e senha)
    * Autenticação baseada em token (Token JWT)
    * Atualização do Token de forma transparente (Refresh Token)

4. Mecanismo de bloqueio de conta
    * Bloquear a conta após 6 tentativas de login malsucedidas

5. Acesso à aplicação com base em funções e permissões (ACL)
    * Proteger recursos do aplicativo usando funções e permissões

6. Autenticação de dois fatores (usando o número de telefone do usuário)
    * Enviar código de verificação para o telefone do usuário

7. Registrar atividades do usuário (login, mudança de conta, etc.)
	* Capacidade de relatar atividades suspeitas
    * Informações de rastreamento
    * Endereço IP
    * Dispositivo
    * Navegador
    * Data
    * Tipo

## Clientes

1. Informações do Cliente
    * Gerenciar informações do cliente (nome, endereço, etc.)
    * O cliente pode ser uma pessoa ou uma instituição
    * O cliente deve ter um status
    * O cliente terá faturas
    * Imprimir clientes em planilha

2. Pesquisar Clientes
    * Capacidade de pesquisar clientes pelo nome
    * Paginação

## Faturas

1. Gerenciar Faturas
    * Criar novas faturas
    * Adicionar faturas ao cliente
    * Imprimir faturas para envio pelo correio
    * Imprimir faturas em planilha
    * Baixar faturas como PDF