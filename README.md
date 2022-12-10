# Project-5: Digital Marketplace :shopping_cart:

## Computer requirements and installations for use:
This program is written in Java. Install Java beforehand in order to run it. The download link can be found [here](https://www.java.com/en/download/).

Download the ZIP file of this repository and extract all contents to a folder.

To compile all files, open the command line, set the path to the "src" folder, and run `javac *.java`.

In order to run our program, there are two steps. First, the user needs to run the server class, this is our backend processing which is used to handle and process user input. Once running, the server will run indefinitely. After the server has been launched, run the client class. This will start our application.

## Description of program
### Client
Our clieng class works directly with our server class in order to handle and prompt user input. The server class must be ran properly before the client class can be launched. Our client class utilizes an intricate system of printwriters and readers in order to communicate with our server, and using JoptionPanes in order to communicate with the user.
Our client side programs main goal is be able to handle user input, and show windows to the user depending on what is inputted.

Customers and seller can access all of our features within the client class, allowing sellers to add products to the store, and view data on how their store is doing. Customers have access to a variety of different features such as instant lightspeed checkout, a shopping cart, as well as their own personal data on what items are in their cart as well as their purchasing history. 

### Server
Our server class works in tandem with our client, handling the backend processing needed to run the functionalities of the program. Our server class will run indefinitely, meaning that even if the client class is closed, the server will continue running. In addition to prompting future input from the user, and handling the inputs that the user replies with, our server also stores our users data in files. These files include data on what accounts have been created, customers shopping carts, purchase histories as well as what items are currently in the market. Our data can be accessed using the client, and is inteded to give both customers and seller a more informed experience as they use our software. Our server also uses printwriters and a buffered reader in order to recieve data from the client, which is the basis for all of our servers actions.


### Seller.java
The Seller class stores information associated with the marketplace and a seller account and runs the seller's side of the program. The information stored is a product's number, the account's email address or username and password, and a list of all products in the marketplace.

Our seller classes methods are used within the server class. It has the ability to show information about a store, add a product with a randomly assigned number, edit a product, delete a product. When there is an error, a stack trace is printed.

The Seller class can be tested with pre-existing method calls for the abilities to initalize, add, edit, and delete a product, and view information about a store (it shows a message if the store does not exist).

### Customer.java
The Customer class stores information associated with the marketplace and a customer account and runs the customer's side of the program. The information stored is a list of all products in the marketplace and the account's email address or username and password. When a Customer object is created, its email address or username and password is declared. There are methods to retrieve and change the email address or username and password.

Our customer classes methods are used in the server class. It has the abilities to show all products in the marketplace, sort products by price and quantity, find and return a list of products based on the user's inputed search term, show info about a product, add a product to a user's cart, checkout, run the process of buying a product (and adding the product to the user's history), show the user's buying history and shopping cart, and clear the cart. When there is an error, a stack trace is printed.

The Customer class can be tested with the pre-existing method calls for the abilities to initalize, see products in the marketplace, search, view info about and buy a product, sort products by price or quantity, and record buying history.

### Store.java
The Store class is used by the Seller and Customer classes to store information for each store. The information stored is the name of the store, name of the its owner, a list of products, and amount of revenue. The class has methods to retrieve and change the information.

### Product.java
The Product class is used by the Seller, Customer, and Store classes to store information for each product. The information stored is the product's number, the name of the it, name of the store it is sold, its description, its price, and the quantity of it. The class has methods to retrieve and change the information.




