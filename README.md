# Moip

* **Sobre**

Projeto Java para leitura de um arquivo log, e contagem de parametros request_to e response_status.

* **Como executar o programa?**

1. Clonar o projeto para um repositório local, ou fazer checkout do projeto em uma IDE Java (Por exemplo, Eclipse). 
  1. ```git clone https://github.com/gabrielqueiroz/Moip.git```
2. Executar a classe "Moip" do pacote "controller".
3. O resultado será apresentado no console da IDE.

* **Como funciona?**

O programa é dividido em tres pacotes, sendo estes: Controller, Model e Test.

Para gestão das informações relacionadas a um Webhook, foi criado um objeto **Webhook** contendo informações específicas do mesmo, tais como: level, responseBody, requestTo, responseHeaders, responseStatus.

Para leitura das informaçōes de Log, foi elaborada a classe **ReadFile**. A função **lineToString** é responsavel pela leitura de cada linha do Log, retornando uma lista de Strings.

Toda a verificaçāo e gestāo das informaçōes de um Log é de responsabilidade da classe **VerifyLog**. A função **getWebhook** recebe uma lista de Strings, contendo cada linha de um Log, retornado uma **lista de objetos Webhooks**, facilitando o uso dos dados pertecentes ao log.

Um método **isWebhook** foi elaborado para verificar se a linha a ser verifica possui todos os componentes necessarios para ser considerada um Webhook. 

Para verificação de estatísticas relacionadas aos objetos Webhook do Log, foi elaborada a função **getStatistics**. Esta possui três parâmetros, sendo eles: Lista de Webhooks a ser verificada; Atributo de um Webhook a ser relacionado (level, responseBody, requestTo, responseHeaders, status); Quantidade máxima de elementos a serem exibidos. A função avalia a lista de Webhooks basaeada no atributo requerido como parâmetro, e organiza em ordem decrescente a relação do atributo e o número de vezes que ele se repetiu no Log em uma quantidade específica de elementos, sendo que serão exibidos todos os elementos caso o parâmetro seja "0". Esta função pode verificar qualquer atributo de um Webhook e qualquer quantidade de elementos, como os tres primeiros elementos, retornando um String formatada contendo estas informações prontas para serem exbidas.

Por final, foi elaborado um método auxiliar para formataçāo de dados internos da funçāo **getStatistics**.

Para executar o programa basta executar a classe "Moip" presente no pacote "Controller". Testes unitários foram elaborados para cada situação relevante pertencente em cada classe, tais como: 
1. Teste de Exception na classe de leitura e teste de validação de leitura para o metodo "lineToString".
2. Teste de conversão de um log contendo uma ou mais linhas para uma lista de objetos Webhooks para o metodo "getWebhook".
2. Teste de valores reais, minímos (10 linhas) e máximos (dobro da capacidade) para o metodo "getStatistics", com base nos atributos solicitados no teste (requestTo e status).
3. Teste de troca de valores validos e invalidos para o metodo "swapString".

* **Possíveis Melhorias**

Um objeto específico poderia ser criado para o atributo **response_Headers** uma vez que este contém um grande número de informações específicas que podem ser analisadas individualmente. Este objeto também seria um atributo do objeto Webhook.

A forma com o qual a análise da linha é feita pode auxiliar esta implementaçāo futura, sendo que a separaçāo leva em consideraçāo uma expressāo regular (regex) que desconsidera espacos dentro de chaves ("\\s+(?![^\\[]*\\])").

* **Resultado Final**

```
 https://eagerhaystack.com - 750 
 https://surrealostrich.com.br - 734 
 https://grimpottery.net.br - 732

 404 - 1474 
 503 - 1451 
 400 - 1440 
 500 - 1428 
 200 - 1417 
 201 - 1402 
 204 - 1388
 ```
