# BeerFest API

### Scope
This api demonstrates a simple function of a tap beer vending machine, where the beer flows out on opening and stops when closed.
The business logic is very basic when the tap is opened the volume of liquid is received is calculated as per the time the tap was left opened.
Once the tap is closed, we get the price of the serving, total amount of sales and the total amount of volume sold

### Instructions
- You must have Java 17 installed on your machine
- Once you have this repo cloned or downloaded, navigate to project directory
- Open command line and type <code>gradlew build bootrun</code> or from terminal, run <code>./gradlew build bootrun</code
- You should see the server URL(runs in http://localhost:8080 by default)

### API Reference
Using Postman or any similar applications reach the follwing endpoints:
- <code>http://localhost:8080</code> Welcome message
- <code>http://localhost:8080/open</code> Open tap
- <code>http://localhost:8080/close</code> Close tap & Account details

### Architecture
I used Spring RESTful capabilities for this exercise to demonstrate this result can be achieved in least possible code. No complicated functionality or codebase is implemented since we aim for least code maintanence as possible and least resource usage

