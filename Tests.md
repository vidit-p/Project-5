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




