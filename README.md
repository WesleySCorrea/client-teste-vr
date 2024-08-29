# Client-Teste-VR

## Passos para Rodar a Aplicação

1. **Clone o Repositório**

   Primeiramente, clone o repositório utilizando um dos métodos abaixo:


**Método SSH:**

   ```bash
   git clone git@github.com:WesleySCorrea/client-teste-vr.git
   ```

**Método HTTPS:**

   ```bash
   git clone https://github.com/WesleySCorrea/client-teste-vr.git
   ```

1. **Interface- Java Swing**

Ao iniciar a interface do Java Swing, iniciaremos com uma simples tela de login, onde para acessar os demais conteudos, deve-se utilizar username e password **admin**.

<img alt="img_1.png" src="img_1.png" width="450"/>

Com login bem sucedido, entramos na página home, e ao lado esquerdo da tela, temos o menu com as informaçoes de **Clientes, Produtos e Pedidos**.

<img alt="img_2.png" src="img_2.png" width="450"/>

Ao selecinar o menu pretendido, abrimos uma segunda coluna de botões onde abriremos mais opções sobre o mesmo.

Por exemplo, clicando no botão **Clientes**, teremos mais quatro opões sobre clientes, que são:

 - Cadastrar Clientes
 - Atualizar Clientes
 - Excluir Clientes
 - Listar Clientes

<img alt="img_3.png" src="img_3.png" width="450"/>

Esse processo se repete para produtos e pedidos, como nas imagens abaixo:

<img alt="img_4.png" src="img_4.png" width="450"/> <img alt="img_5.png" src="img_5.png" width="450"/>

Para realizar o cadastro de um novo cliente, basta preencher os campos e enviar, lembrando que, o dia de vencimento deve-se estar entre 1 e 31, caso o contrário você receberá um aviso e precisará definir um novo valor.

<img alt="img_6.png" src="img_6.png" width="200"/>  Esse seria um erro, por exemplo.        <img alt="img_7.png" src="img_7.png" width="200"/>

As delas de Atualização e Deleção de clientes são parecidas, você pode buscar o cliente pelo número do ID e após isso, fazer o Update, ou Deleção do **Cliente**
 
<img alt="img_8.png" src="img_8.png" width="200"/>    Atualização e Deleção do Mesmo      <img alt="img_9.png" src="img_9.png" width="200"/>

O último botão, será responsável por buscar todos os **Clientes Ativos** que estão no banco de dados.

<img alt="img_10.png" src="img_10.png" width="200"/>

Essas mesmas funções estarão disponiveis na aba de **Produtos**

Para inicialização de um Pedido, basta passa no campo disponível, o **ID do Cliente** em que esse **Pedido** será vinculado.
Após a iniciar o pedido, estatá na tela disponivel informaçoes do **Cliente** e informaçoes do **Pedido**, e logo a baixo aparecera as opções de adicionar os produtos ao pedido.

<img alt="img_12.png" src="img_12.png" width="450"/>

Para adicionar produtos ao pedido, basta informar o **ID do Produto** e a **Quantidade** do mesmo, e apertar o botão de **ADICIONAR**.

<img alt="img_13.png" src="img_13.png" width="200"/>

Conforme você adicionar os produtos ao pedido, eles serão acrescentados a lista logo abaixo, a atualizará o valor total do pedido no cabeçalho acima.

Além disso, abrirá a opção de você poder finalizar o pedido, que seria a **CONFIRMAÇÃO** do pedido, fazendo a atualização do saldo do **Cliente** no cabeçalho de informaçoes.

<img alt="img_15.png" src="img_15.png" width="450"/>

O botão de fechar pedido, apenas da a liberdade de fechar essa janela para que possa realizar outro pedido, porem, se o pedido ainda nao foi finalizado, você conseguirá atualizar o mesmo pelo botão de **ATUALIZAR PEDIDO**

Lembrando que para você atualizar o pedido, ou até mesmo excluir o mesmo, esse pedido nao pode está finalizado.

E por ultimo, o botão de **LISTAR PEDIDOS**, nada mais é do que a listagem paginada de todos os pedidos, independente se estão fechados ou não.

<img alt="img_14.png" src="img_14.png" width="450"/>

Nessa tela, tem dois campos acima, **Cliente ID** e **Product ID**, eles fazem uma busca na lista, ou pelo **Cliente** ou pelo **Produto**, trazendo todos os pedidos que contenham esses **CLIENTES** ou **PRODUTOS**.

<img alt="img_16.png" src="img_16.png" width="200"/>"