# payconiq
payconiq assignment

A simple web app responsible for creating, adding, updating, and getting stocks. It consists of: 

a. Spring RESTful Web MVC application called "stocks-service-server" responsible for server side. 

--> API 

An entity Stock has the following attributes: 

Long id;
String name;
Integer currentPrice;
LocalDate lastUpdate;

URL : http://localhost:8080/api/stocks

1. GET /api/stocks

/**
	 * A simple GET rest end point responsible for retrieving a collection of all the available stocks.
	 *
	 * @return a collection of entity Stock
	 */
   
2. GET /api/stocks/{id}

/**
	 * A simple GET rest end point responsible for retrieving a stock for the given id.
	 *
	 * @param id id to be retrieved for
	 * @return found Stock
	 */
   
3. POST /api/stocks

/**
	 * A simple POST rest end point responsible for adding a new {@link Stock}.
	 *
	 * @param addRequest object to be added
	 * @return added Stock
	 */
   
   Sample payload : 
   
   {
		"name": "Stock33",
		"currentPrice" : 5050  
    }
    
    id must be null and lastUpdate is optional
   
4. PUT /api/stocks

/**
	 * A simple PUT rest end point responsible for updating an existing {@link Stock}.
	 *
	 * @param updateRequest object to be updated
	 * @return updated Stock
	 */
   
   Sample payload :
   
   {
		"id" : 3,
		"name": "Stock33",
		"currentPrice" : 5050        
    }
    
    only lastUpdate is optional and rest is mandatory.


b. An Angular 2 application called "stocks-service-client" responsible for client side.

Simply displays the list of stocks on http://localhost:4200 once both the application are running.
