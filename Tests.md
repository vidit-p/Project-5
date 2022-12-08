TEST 1: Successful Launch
Steps:
1: Run server
2: Run client
Expected result: Initial window pops up prompting the user
to select whether they want to create an account or log in.
Test status: Passed.

TEST 2: Successful Cancel.
Steps: 
1: launch program successfully.
2: On window when prompted to create account or log in, 
select the cancel button
Expected result: program quits
Test status: Passed.

Test 3: Select Create an account
Steps:
1: After launch, use the dropdown menu to select create new account.
2: Click the "OK" button.
Expected result: New Account type window opens prompting user
to input what kind of account they want to create.
Test status: Passed.

Test 4: Choose to Create a seller account
steps:
1: After performing steps in test 3, select "Seller"
2: Press "OK"
Expected Result: New create account window opens up with a keyboard input prompting the 
user to Enter their username.
Test status: Pass

Test 5: Choose to Create a Buyer account
steps:
1: After performing steps in test 3, select "Buyer"
2: Press "OK"
Expected Result: New create account window opens up with a keyboard input prompting the
user to Enter their username.
Test status: Pass

Test 6: Entering an invalid Username for seller
Steps:
1: On enter username window after choosing seller, use keyboard to input a username which isnt in email format
Expected result: Error window opens with a message reading: Invalid Email Format
Test status: Pass

Test 7: Entering an invalid Username for Customer
Steps:
1: On enter username window after choosing Customer, use keyboard to input a username which isnt in email format
Expected result: Error window opens with a message reading: Invalid Email Format
Test status: Pass

Test 8: Creating a new Seller account
steps:
1: Complete all steps in test 4, user will be on a window prompting
to enter your username
2: Enter a valid username using the keyboard input, valid usernames must be in 
email format and not already have accounts created for them.
3: Press "OK"
sample input for step 2: newtestusername@gmail.com
Expected result: Will save this username in a new account,
and will open a new window which prompts the user for a password

Test 9: Creating a new Buyer account
steps:
1: After performing steps in test 3, select "Buyer" and click ok
2: Enter a valid username using the keyboard input, valid usernames must be in
email format and not already have accounts created for them.
3: Press "OK"
sample input for step 2: newtestusername@gmail.com
Expected result: Will save this username in a new account,
and will open a new window which prompts the user for a password

NOTE: Tests 10 and 11 are identical for creating both Buyer, and seller accounts
this means we have tested them for both cases.

Test 10: Inputting Username which is not in email format.
steps
1: Start on the window prompting to enter your username
2: Type in a username with an invalid email format
this means that it either does not have an @ , or a .
or that the @/. are the first or last character of the email.
3: Press "OK"
Expected result: A new error window should pop up with Invalid Email Format.
When ok is pressed, takes you back to re-input your username
Test result: Passed.

Test 11: Inputting a username which already exists
steps:
1: Start on the window prompting to enter your username
2: Type in a username which already has an account.
3: press "OK"
Expected result: A new error window should pop up with Username Already Exists.
When ok is pressed, takes you back to re-input your username

test 12: Inputting a new password for Seller/Buyer account
steps:
1: Start on Window where you are prompted to create your password using a keyboard input
this is after inputting an email as a username for a seller/buyer account
2: Use the keyboard input to type in a password for the account
3: Press "OK"
Expected Result: Account password is saved in the server, and a new window opens up
showing a confirmation message that the account was created, and prompting the user to
restart the application and login. When ok is pressed, the program will close and finish running.

test 13: Login with an invalid Account
Steps:
Step 1: launch the program
step 2: select from the dropdown menu: login
step 3: press "OK"
step 4: using the keyboard, enter an input that is not already an email
account which has an account
Expected result: Error Message Window opens with a message saying the Email does not exist
Test Result: passed

test 13: Login with a valid account
steps:
1: Start on initial window
2: from the dropdown menu, select login
3: press "Ok"
4: on the new window, use the keyboard entry to enter an account
that has valid credentials
5: press "Ok"
6: Enter the corresponding password for the account 
and press ok
Expected result: New window opens with a welcome message
telling the user they have successfully logged in
Test Result: Passed

Test 14: Exiting the program After a successful login
steps:
1: log in successfully
2: from the dropdown menu, select exit and press ok
Expected result: New window opens thanking the user
for using the program, when ok is pressed on this window
the program ends.
Test Result: Passed

Test 15: View all of the products as a customer
steps:
1: Successfully login using a customer account
2: Select from the dropdown menu: "View all the products"
and press ok
Expected result: New window opens up with a new
dropdown menu. This menu should contain all products and their 
basic information, and prompt the user to select a product to 
view further information about
Test result: Passed

Test 15: Viewing information from the marketplace window
steps:
1: Complete test 15, and start on the new window.
2: select a product from the dropdown menu and press ok
Expected Result: New window opens up with the products information
and a new dropdown menu with the option to buy the product,
add it to the cart, or go back to the menu.

Test 16: Selecting the Buy Product Option
steps:
1: Complete test 15, and start on the new window
2: Select buy from the dropdown menu and press ok
Expected Result: new window opens up with a keyboard entry
asking the user how many of the product they want to buy
Test Result: Passed.

Test 17: Buying an invalid Quantity of the product
steps:
1: Start after completing test 16, on the window prompting
the user to enter the quantity of the product they want to
purchase
2: Enter a value that is higher than the amount in stock
and press ok
Expected Result: Error window pops up with a message telling
the user that there is not enough of the product available for purchase
Test Result: Passed.

Test 18: Buying a valid Quantity of the product
steps: 
1: Start after completing test 16, on the window prompting
the user to enter the quantity of the product they want to
purchase
2: Enter a value that is less than or equal
to the amount in stock and press ok
Expected Result: New Window opens up with a message telling
the user that the product has been bought successfully.
When ok is pressed, it directs the user back to the initial menu
window after logging in
Test Result: Passed.

Test 19: Adding a product to the cart
steps:
1: Start on the window with the information on the product
and the dropdown menu with options to buy the product, add 
to the cart, or go back to the main menu.
2: Select Add to Cart and press OK
Expected Result: Product will be added to the users shopping
cart and they will be redirected to the main menu
Test Result: Passed.

Test 20: Sorting all of the products by price
steps:
1: Start on the main menu screen after logging in as customer
2: Select from the dropdown menu: Sort the market by price
and press OK
Expected Result: Directs user to a new window with a selection
of the products, sorted by price.
Test Result: Passed

Test 21: Selecting and purchasing a product/adding a product to cart from this sorted menu
steps
1: Complete test 20, and select a product from the dropdown 
and press ok
Expected Result: You will be directed to a description of the product
and be directed to the same menu as test 15, the functionalities 
of this window are identical to that one. The options from
this window will have the same functionalties as tested in
test cases 16-19
Test Result: Passed.

Test 22: Sorting the product By Quantity
steps:
1: Start on the main menu after logging in as customer
2: Select Sort the market by quantity and press OK
Expected Result: Directed to product selection menu 
sorted by quantity available from lowest to highest.

Test 23: Selecting a product from menu sorted by quantity
steps:
1: Complete test 22 and start on the menu sorted by quantity
2: Select a product and press ok
Expected Result: You will be directed to a description of 
the product and be directed to the same menu as test 15, 
the functionalities of this window are identical to that one. The options from
this window will have the same functionalties as tested in
test cases 16-19
Test Result: Passed.

Test 24: View Shopping cart
steps: 
1: start on the main menu after logging in as customer
2: Select from the dropdown: View shopping cart and press OK
Expected Result: New Window will open with a description of your
shopping cart

Test 25: View shopping cart when it is empty
steps:
1: start on the main menu after logging in as customer
2: Select from the dropdown: View shopping cart and press OK
Expected Result: New Window will open with a 
message saying the shopping cart is empty.
Test Result: Passed.

Test 26: Viewing your purchase history
steps: 
1: Start on main menu after logging in as a customer
2: Select from the dropdown, view purchase history and press
OK
Expected Result: New window opens with a log of the customers purhcase 
history. When ok is pressed, it goes back to the main menu
Test Result: Passed.

Test 27: Search for product using search term
steps:
1: Start on main menu after logging in as a customer
2: Select from the dropdown, Search for product using 
search term and press OK
Expected Result: New window opens up with keyboard entry
asking the user to input a search term
Test Result: Passed.

Test 28: Entering a Search term
steps: 
1: Complete test 27 and start on the new window
2: Enter in a search term, and press ok
(A search term is a word or character that you are looking
for a product to contain)
Expected Result: A new window will pop up with a selection
of products whos description contains the entered search term
Test Result: Passed.

Test 29: Selecting a product from this window
steps:
1: Complete test 28 and start on the menu sorted by search
term
2: Select a product and press ok
Expected Result: You will be directed to a description of
the product and be directed to the same menu as test 15,
the functionalities of this window are identical to that one. The options from
this window will have the same functionalities as tested in
test cases 16-19
Test Result: Passed.

















