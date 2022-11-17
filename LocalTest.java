import junit.framework.TestCase;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Test case class
 *
 *
 *
 * @author Vidit Patel
 * @version November 14, 2022
 */

public class LocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d. \n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.printf("Excellent = all test cases ran successfully. \n");
        } else {
            System.out.printf("Test failed: %d. \n", result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        private final PrintStream output = System.out;
        private final InputStream sysin = System.in;

        private ByteArrayInputStream testIn;
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(sysin);
            System.setOut(output);
        }

        private String getOutput() {
            return testOut.toString();
        }

        private void receiveInput(String string) {
            testIn = new ByteArrayInputStream(string.getBytes());
            System.setIn(testIn);
        }

        @org.junit.Test(timeout = 1000)
        public void testExpectedOne() {
            // Set the input
            // Separate each input with a newline (\n).
            String input = "1\n" + "purdue\n" + "purdue\n" + "3\n" + "yes\n" + "1\n" + "shoes\n" + "993005508\n" + "3\n" + "6\n";

            // Pair the input with the expected result
            String expected = "Welcome to the digital marketplace!\n" +
                    "Do you currently have an account?\n" +
                    "1 = Yes, 2 = No\n" +

                    "Please enter your email or username:\n" +

                    "Please enter your password:\n" +
                    "You are now logged in to your account!\n" +
                    "---------------------------------\n" +
                    "Product Number: 239927061 \t Product Name:shoe \t Store Name: adidas \t Price: 60.0\n" +
                    "Product Number: 993005508 \t Product Name:shoes \t Store Name: nike \t Price: 60.0\n" +
                    "Product Number: 915847427 \t Product Name:ball \t Store Name: tommy \t Price: 50.0\n" +
                    "Product Number: 307780709 \t Product Name:shirt \t Store Name: bamba \t Price: 50.0\n" +
                    "Product Number: 12334343 \t Product Name:hat \t Store Name: cat \t Price: 10.0\n" +
                    "1 = Sort by price\n" +
                    "2 = Sort by quantity\n" +
                    "3 = Continue without sorting\n" +
                    "4 = View shopping cart\n" +
                    "5 = View purchase history\n" +
                    "6 = Exit\n" +
                    "Are you interested in buying any of these products? (Yes or No)\n" +
                    "1 = search for product\n" +
                    "2 = continue without searching\n" +
                    "Enter the search term\n" +
                    "Product Number: 239927061 \t Product Name:shoe \t Store Name: adidas \t Price: 60.0\n" +
                    "Product Number: 993005508 \t Product Name:shoes \t Store Name: nike \t Price: 60.0\n" +
                    "Enter the product number of the product you would like to see the description of.\n" +
                    "Product description: size 9 shoes \t Quantity available: 23\n" +
                    "Would you like to:\n" +
                    "1 = Buy product\n" +
                    "2 = Add to shopping cart\n" +
                    "3 = Return to list of products\n" +
                    "4 = View shopping cart\n" +
                    "---------------------------------\n" +
                    "Product Number: 239927061 \t Product Name:shoe \t Store Name: adidas \t Price: 60.0\n" +
                    "Product Number: 993005508 \t Product Name:shoes \t Store Name: nike \t Price: 60.0\n" +
                    "Product Number: 915847427 \t Product Name:ball \t Store Name: tommy \t Price: 50.0\n" +
                    "Product Number: 307780709 \t Product Name:shirt \t Store Name: bamba \t Price: 50.0\n" +
                    "Product Number: 12334343 \t Product Name:hat \t Store Name: cat \t Price: 10.0\n" +
                    "1 = Sort by price\n" +
                    "2 = Sort by quantity\n" +
                    "3 = Continue without sorting\n" +
                    "4 = View shopping cart\n" +
                    "5 = View purchase history\n" +
                    "6 = Exit\n" +
                    "Thank you for using the Online Marketplace!\n";

            // Runs the program with the input values
            // Replace TestProgram with the name of the class with the main method
            receiveInput(input);
            MarketplaceLogin.main(new String[0]);

            // Retrieves the output from the program
            String stuOut = getOutput();

            // Trims the output and verifies it is correct.
            stuOut = stuOut.replace("\r\n", "\n");
            assertEquals("Error message if output is incorrect, customize as needed",
                    expected.trim(), stuOut.trim());

        }

        @Test(timeout = 1000)
        public void testExpectedTwo() {
            // Set the input
            // Separate each input with a newline (\n).
            String input = "1\n" + "purdue\n" + "purdue\n" + "3\n" + "yes\n" + "2\n" + "307780709\n" + "3\n" + "6\n";

            // Pair the input with the expected result
            String expected = "Welcome to the digital marketplace!\n" +
                    "Do you currently have an account?\n" +
                    "1 = Yes, 2 = No\n" +
                    "Please enter your email or username:\n" +
                    "Please enter your password:\n" +
                    "You are now logged in to your account!\n" +
                    "---------------------------------\n" +
                    "Product Number: 239927061 \t Product Name:shoe \t Store Name: adidas \t Price: 60.0\n" +
                    "Product Number: 993005508 \t Product Name:shoes \t Store Name: nike \t Price: 60.0\n" +
                    "Product Number: 915847427 \t Product Name:ball \t Store Name: tommy \t Price: 50.0\n" +
                    "Product Number: 307780709 \t Product Name:shirt \t Store Name: bamba \t Price: 50.0\n" +
                    "Product Number: 12334343 \t Product Name:hat \t Store Name: cat \t Price: 10.0\n" +
                    "1 = Sort by price\n" +
                    "2 = Sort by quantity\n" +
                    "3 = Continue without sorting\n" +
                    "4 = View shopping cart\n" +
                    "5 = View purchase history\n" +
                    "6 = Exit\n" +
                    "Are you interested in buying any of these products? (Yes or No)\n" +
                    "1 = search for product\n" +
                    "2 = continue without searching\n" +
                    "Enter the product number of the product you would like to see the description of.\n" +
                    "Product description: shirt size small \t Quantity available: 30\n" +
                    "Would you like to:\n" +
                    "1 = Buy product\n" +
                    "2 = Add to shopping cart\n" +
                    "3 = Return to list of products\n" +
                    "4 = View shopping cart\n" +
                    "---------------------------------\n" +
                    "Product Number: 239927061 \t Product Name:shoe \t Store Name: adidas \t Price: 60.0\n" +
                    "Product Number: 993005508 \t Product Name:shoes \t Store Name: nike \t Price: 60.0\n" +
                    "Product Number: 915847427 \t Product Name:ball \t Store Name: tommy \t Price: 50.0\n" +
                    "Product Number: 307780709 \t Product Name:shirt \t Store Name: bamba \t Price: 50.0\n" +
                    "Product Number: 12334343 \t Product Name:hat \t Store Name: cat \t Price: 10.0\n" +
                    "1 = Sort by price\n" +
                    "2 = Sort by quantity\n" +
                    "3 = Continue without sorting\n" +
                    "4 = View shopping cart\n" +
                    "5 = View purchase history\n" +
                    "6 = Exit\n" +
                    "Thank you for using the Online Marketplace!";

            // Runs the program with the input values
            // Replace TestProgram with the name of the class with the main method
            receiveInput(input);
            MarketplaceLogin.main(new String[0]);

            // Retrieves the output from the program
            String stuOut = getOutput();

            // Trims the output and verifies it is correct.
            stuOut = stuOut.replace("\r\n", "\n");
            assertEquals("Error message if output is incorrect, customize as needed",
                    expected.trim(), stuOut.trim());

        }

        @Test(timeout = 1000)
        public void testExpectedThree() {
            // Set the input
            // Separate each input with a newline (\n).
            String input = "1\n" + "purdue\n" + "purdue\n" + "1\n" + "no\n";

            // Pair the input with the expected result
            String expected = "Welcome to the digital marketplace!\n" +
                    "Do you currently have an account?\n" +
                    "1 = Yes, 2 = No\n" +
                    "Please enter your email or username:\n" +
                    "Please enter your password:\n" +
                    "You are now logged in to your account!\n" +
                    "---------------------------------\n" +
                    "Product Number: 239927061 \t Product Name:shoe \t Store Name: adidas \t Price: 60.0\n" +
                    "Product Number: 993005508 \t Product Name:shoes \t Store Name: nike \t Price: 60.0\n" +
                    "Product Number: 915847427 \t Product Name:ball \t Store Name: tommy \t Price: 50.0\n" +
                    "Product Number: 307780709 \t Product Name:shirt \t Store Name: bamba \t Price: 50.0\n" +
                    "Product Number: 12334343 \t Product Name:hat \t Store Name: cat \t Price: 10.0\n" +
                    "1 = Sort by price\n" +
                    "2 = Sort by quantity\n" +
                    "3 = Continue without sorting\n" +
                    "4 = View shopping cart\n" +
                    "5 = View purchase history\n" +
                    "6 = Exit\n" +
                    "Product Number: 12334343 \t Product Name:hat \t Store Name: cat \t Price: 10.0\n" +
                    "Product Number: 915847427 \t Product Name:ball \t Store Name: tommy \t Price: 50.0\n" +
                    "Product Number: 307780709 \t Product Name:shirt \t Store Name: bamba \t Price: 50.0\n" +
                    "Product Number: 239927061 \t Product Name:shoe \t Store Name: adidas \t Price: 60.0\n" +
                    "Product Number: 993005508 \t Product Name:shoes \t Store Name: nike \t Price: 60.0\n" +
                    "Are you interested in buying any of these products? (Yes or No)\n" +
                    "Thank you for using the Online Marketplace!";

            // Runs the program with the input values
            // Replace TestProgram with the name of the class with the main method
            receiveInput(input);
            MarketplaceLogin.main(new String[0]);

            // Retrieves the output from the program
            String stuOut = getOutput();

            // Trims the output and verifies it is correct.
            stuOut = stuOut.replace("\r\n", "\n");
            assertEquals("Error message if output is incorrect, customize as needed",
                    expected.trim(), stuOut.trim());

        }

        @Test(timeout = 1000)
        public void testExpectedFour() {
            // Set the input
            // Separate each input with a newline (\n).
            String input = "1\n" + "abc@purdue.edu\n" + "hello\n" + "4\n" + "adidas\n" + "6\n";

            // Pair the input with the expected result
            String expected = "Welcome to the digital marketplace!\n" +
                    "Do you currently have an account?\n" +
                    "1 = Yes, 2 = No\n" +
                    "Please enter your email or username:\n" +
                    "Please enter your password:\n" +
                    "You are now logged in to your account!\n" +
                    "---------------------------------\n" +
                    "What would you like to do?\n" +
                    "1 = Put new product on the market\n" +
                    "2 = Remove product from the market\n" +
                    "3 = Edit existing product\n" +
                    "4 = View information about a store\n" +
                    "5 = Import a CSV file of products\n" +
                    "6 = View Customer's shopping cart\n" +
                    "7 = Exit\n" +
                    "Which store would you like to see info about?\n" +
                    "Product name: 239927061 \t Store name: shoe \t price: 60.0 \t customer: purdue\n" +
                    "Product name: 239927061 \t Store name: shoe \t price: 60.0 \t customer: purdue\n" +
                    "Total revenue: 120.00\n" +
                    "---------------------------------\n" +
                    "What would you like to do?\n" +
                    "1 = Put new product on the market\n" +
                    "2 = Remove product from the market\n" +
                    "3 = Edit existing product\n" +
                    "4 = View information about a store\n" +
                    "5 = Import a CSV file of products\n" +
                    "6 = View Customer's shopping cart\n" +
                    "7 = Exit\n" +
                    "Thank you for using the Online Marketplace!";

            // Runs the program with the input values
            // Replace TestProgram with the name of the class with the main method
            receiveInput(input);
            MarketplaceLogin.main(new String[0]);

            // Retrieves the output from the program
            String stuOut = getOutput();

            // Trims the output and verifies it is correct.
            stuOut = stuOut.replace("\r\n", "\n");
            assertEquals("Error message if output is incorrect, customize as needed",
                    expected.trim(), stuOut.trim());

        }

    }

}


