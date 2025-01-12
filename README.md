# Assignment
The assignment is about a ticketing service. The goal is to create a service that can book tickets and make reservations on physical seats on a given service with an origin & destination for multiple passengers. In this assignment there are no prices, no products and no payments.

Note
Please show us what you are capable of. Make sure you are proud of the code you implement for this assignment. Architectural choices are more important than implementing all the features. Quality is key. Make sure each part of your code is behaving correctly and not susceptible to regression issues.  

## Some other requirements:
1.	No automatic seating algorithm, seats are explicitly given with a reservation request.
2.	Implement a REST interface without the HTTP. Meaning, your application doesn’t need to run a HTTP server and accept client connections. See Kotlin interfaces below.
3.	No external libraries are required, only libraries available with standard Java, Kotlin or Golang.
a.	For testing purposes libraries are allowed

### Note
Although the interfaces below are Kotlin code. You can convert them into Golang and implement your code in Go using the standard Golang library.
Basic Interfaces
interface Response {
   fun getStatusCode(): Int
   fun getBody(): Any
}

interface HTTPClient {
   fun post(url: String, body: Any): Response
   fun get(url: String): Response
}

# Scenarios
With this data model we would like you to implement the following scenarios.

Note: At least implement routes from Paris to London, Paris to Amsterdam and Amsterdam to Berlin. Each route should have at least 2 stops in between.

1.	Be able to make a reservation for 2 passengers with 2 first-class seats, from Paris to Amsterdam on service 5160 on April 1st 2021 with seats A11 & A12 (Carriage A, Seat 11 & 12). 
2.	Make the same booking again, but then it should fail, because seats are already taken. 
3.	Be able to make a reservation for 2 passengers where 1 passenger in second-class, one in first-class. From London to Amsterdam. London to Paris, seat H1 and N5, Paris to Amsterdam, seat A1 & T7. 
4.	Make the previous booking again, and it should fail, because seats are taken.

If there is time left. As a conductor:
1.	I want to know how many passengers are boarding at station London
2.	I want to know how many passengers are leaving at station Paris
3.	I want to know how many passengers are in the train between Calais and Paris
4.	Who is sitting on chair A11 in service 5160, on December 20th in Calais

Note: If there is anything unclear in the assignment, note your assumptions, these can be discussed later.
