# Moip

* Sobre

Projeto Java para leitura de um arquivo log, e contagem de parametros request_to e response_status.

* Como Rodar?

1. Clonar o projeto para um repositório local, ou fazer checkout do projeto em uma IDE Java (Por exemplo, Eclipse). 
  1. ```git clone https://github.com/gabrielqueiroz/Moip.git```
2. Executar a classe "Moip" do pacote "test".
3. O resultado será apresentado no console da IDE.

* Como funciona?

O primeiro passo para solucionar o problema foi elaborar uma classe para leitura do Log (ReadFile). O arquivo de logo foi incluído dentro da pasta do projeto, e um método foi criado para ler cada linha do arquivo, transpondo para uma lista de Strings, contendo assim cada webhook do log.

Com cada webhook em mãos, foi possível separar cada componente do webhook por espaços, tendo assim cada parâmetro individual. Em seguida, é realizada uma busca nos mesmos, buscando o parâmetro que contenha "request_To" e "response_Status" (Solicitados no teste). Ao encontrar estes parâmetros, os mesmos são movidos para listas locais especificas para eles. Com isso, temos duas listas contendo todos as URLS de clientes e status correspondentes.

Por final, é verificado a contagem de valores unicos, presentes em cada lita, sendo possível verificar o valor de cada URL e de cada Status. Essse valores são por final exibidos de maneira simples no console ("System.out.println").

A classe VerifyLog é responsável por verificar o log, e pode ser reutilizada para outros logs, uma vez que ela realiza a verificação com base em uma List<String>, sendo esta um conjunto de webhooks. Sendo assim, não é necessário realizar a leitura de um arquivo específico, podendo ser realizada a leitura de qualquer List<String> webhooks, sendo que esta pode ser obtida de outros métodos (Por exemplo, WebService).

Para executar o teste, basta executar a classe "Moip" presente no pacote "test"

* Resultado Final

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
