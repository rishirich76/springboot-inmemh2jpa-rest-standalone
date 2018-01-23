# Spring-boot H2 inMEM standalone 

This is REST application which exposed as CRUD APIs.

## Steps to Run :

- Goto command prompt in Windows and run `mvn spring-boot:run` 
- This will download dependencies and start spring boot server.

## Services :
-	**Create Transaction**
	-	http://localhost:8080/transactionservice/transaction/{id}
	-	Request Method is `PUT`
	-	**{id}** is mandatory , if you want auto generated then uncomment below tag
		`@GeneratedValue` in class `Transaction.java`
		
- **Retrieve Transaction**
	- http://localhost:8080/transactionservice/transaction/{id} 
	- Request method is `GET`
- **Delete transaction**
	- http://localhost:8080/transactionservice/transaction/{id}
	- Request method is `DELETE`
	
- **Get Transaction of same type**
	- http://localhost:8080/transactionservice/types/{type}
	- Request method is `GET`
	- Results in list of Transaction IDs.
- **Total `Amount` of Transactions**
	- http://localhost:8080/transactionservice/sum/{id}
	- Request method is `GET`
	- Gives total value of `amount` for given `id` and its children.

### Request Structure 
	{
	  "amount": 10000,
	  "type": "shopping",
	  "parent_id": 10
	}

> **ProTip:** You can change log level in application.properties file to see sql logs or H2 logging.
