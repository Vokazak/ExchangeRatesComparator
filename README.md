# Exchange rates comparator
###A web service which provides an information about currency rate using gif.

When web service gets a request with currency code (ex: USD/RUB/EUR), 
it compares this currency's rate with default currency rate for yesterday and today.
If an exchange rate from request has become higher than yesterday (against default currency), 
then this web service will return a random gif searched by "currencyGrows" keyword. 
Otherwise, when an exchange rate falls, web service will return a random gif searched by "currency falls" keyword.

***
#### You can change a server port in src/main/resources/application.properties (default port is 8081)
#### File src/main/resources/comparator.properties provides information about:
* Default currency code (currency fom your request will compare with this currency)
* OpenExchangeRates id (your unique id by which you will be recognized by https://openexchangerates.org/ service)
* Giphy key (your unique key by which you will br recognized by https://developers.giphy.com/ service)
* "CurrencyGrows" keyword (used to search gif to express currency growing)
* "CurrencyFalls" keyword (used to search gif to express currency falling)

***
## To build project:
* "gradle build" command in console
* After that look for .jar file in "project_path"/build/libs/ExchangeRatesComparator-0.0.1-SNAPSHOT.jar
* To execute .jar file from command line: java -jar ExchangeRatesComparator-0.0.1-SNAPSHOT.jar

***
## To test project:
* Make a request through console: curl 127.0.0.1:8081/currency/USD where 127.0.0.1 is a "server.address" field in application properties file and port is a "server.port" field. 