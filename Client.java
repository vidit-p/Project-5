import java.io.*;
import java.net.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;

public class Client {

    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 4244);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            String[] login = new String[2];
            login[0] = "Login";
            login[1] = "Create new account";

            String username = "";
            while (true) {
                String exist = (String) JOptionPane.showInputDialog(null, "Hello! Are you logging in or creating an account?",
                        "Initialize", JOptionPane.QUESTION_MESSAGE, null, login, login[0]);
                if (exist == null) {
                    return;
                } else if (exist.equals("Login")) {
                    String success = "";
                    do {
                        pw.write("1");
                        pw.println();
                        pw.flush();
                        String empty = br.readLine();

                        username = JOptionPane.showInputDialog(null, "Please enter your email:", "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (username == null) {
                            return;
                        }
                        pw.write(username);
                        pw.println();
                        pw.flush();

                        success = br.readLine();
                        if (success.equals("false")) {
                            JOptionPane.showMessageDialog(null, "ERROR: The email does not exist!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (!success.equals("true"));

                    do {
                        String passwd = JOptionPane.showInputDialog(null, "Please enter your password:", "Password",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (passwd == null) {
                            return;
                        }
                        pw.write(passwd);
                        pw.println();
                        pw.flush();

                        success = br.readLine();
                        if (success.equals("true")) {
                            JOptionPane.showMessageDialog(null, "Successfully logged in! " +
                                    "Welcome to Online Marketplace!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                            break;


                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password! Please try again.",
                                    "Incorrect password", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (success.equals("false"));

                    break;


                } else if (exist.equals("Create new account")) {
                    pw.write("2");
                    pw.println();
                    pw.flush();
                    String empty = br.readLine();
                    String[] account = new String[2];
                    account[0] = "Seller";
                    account[1] = "Buyer";
                    String accountType = (String) JOptionPane.showInputDialog(null, "Enter the type of account you wish to create.",
                            "Account type", JOptionPane.QUESTION_MESSAGE, null, account, account[0]);

                    if (accountType.equals("Seller")) {
                        pw.write("2");
                        pw.println();
                        pw.flush();
                    } else {
                        pw.write("1");
                        pw.println();
                        pw.flush();
                    }
                    empty = br.readLine();
                    while (true) {
                        String success = "";
                        boolean validName = false;
                        while (!validName) {
                            username = JOptionPane.showInputDialog(null, "Enter your username",
                                    "Create account", JOptionPane.INFORMATION_MESSAGE);
                            int checkIfAt = username.indexOf("@");
                            int checkIfDot = username.indexOf(".");
                            if (checkIfAt != 0 && checkIfAt != username.length() - 1 && checkIfDot != 0 && checkIfDot != username.length() - 1 && checkIfDot != -1 && checkIfAt != -1) {
                                validName = true;
                                pw.write(username);
                                pw.println();
                                pw.flush();
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid Email Format", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        success = br.readLine();
                        if (success.equals("false")) {
                            JOptionPane.showMessageDialog(null, "Error: there already exists an account with this email.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        } else {
                            String passwd = JOptionPane.showInputDialog(null, "Enter the password for the account:",
                                    "Create account", JOptionPane.INFORMATION_MESSAGE);
                            pw.write(passwd);
                            pw.println();
                            pw.flush();
                            //empty = br.readLine();
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Account successfully created! Restart the " +
                            "application to login", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            pw.write("");
            pw.println();
            pw.flush();

            String role = br.readLine();
            System.out.println("role: " + role);
            if (role.equals("1")) {
                while (true) {
                    String[] options = {"View all the products", "Sort the market by price", "Sort the market by " +
                            "quantity", "View shopping cart", "View purchase history",
                            "Search for product using search term", "Exit"};

                    String option = (String) JOptionPane.showInputDialog(null,
                            "What would you like to do?", "Menu", JOptionPane.INFORMATION_MESSAGE, null,
                            options, options[0]);

                    if (option == null) {
                        return;
                    }

                    if (option.equals("View all the products")) {
                        pw.write("7");
                        pw.println();
                        pw.flush();

                        String products = br.readLine();
                        String[] productArray = products.split(";");
                        String[] display = new String[productArray.length];
                        int index = 0;
                        for (String line : productArray) {
                            String[] lineArray = line.split(",");
                            display[index] = (String.format("Product Number: %s, Product Name: %s, Store name: %s" +
                                            ", Price: $%s", lineArray[0],
                                    lineArray[1], lineArray[2], lineArray[3]));
                            index++;

                        }
                        String productOption = (String) JOptionPane.showInputDialog(null, "" +
                                        "Select the product you would like to see information about:",
                                "Marketplace", JOptionPane.INFORMATION_MESSAGE, null, display,
                                display[0]);

                        String productNumber;

                        if (productOption == null) {
                            return;
                        } else {
                            productNumber = productOption.substring(16, 22);
                        }

                        pw.write("6");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();

                        String[] productOptionArray = productOption.split(",");
                        pw.write(productNumber);
                        pw.println();
                        pw.flush();

                        String info = br.readLine();
                        String[] next = new String[2];
                        next[0] = "Buy product";
                        next[1] = "Add to cart";
                        String next1 = (String) JOptionPane.showInputDialog(null, info + " .What would you like " +
                                "to do next?", "Product info", JOptionPane.QUESTION_MESSAGE, null, next, next[0]);

                        if (next1 == null) {
                            return;
                        } else if (next1.equals("Buy product")) {
                            pw.write("1");
                            pw.println();
                            pw.flush();

                            empty = br.readLine();

                            int intQuantity = -1;

                            boolean quantityBad = true;
                            do {
                                String quantity = JOptionPane.showInputDialog(null, "Enter the quantity you wish to buy:",
                                        "Quantity", JOptionPane.QUESTION_MESSAGE);
                                if (quantity == null) {
                                    return;
                                }
                                try {
                                    intQuantity = Integer.parseInt(quantity);
                                    if (intQuantity < 1) {
                                        JOptionPane.showMessageDialog(null, "Error, please enter a positive number!",
                                                "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                                        quantityBad = false;
                                    } else {
                                        pw.write(quantity);
                                        pw.println();
                                        pw.flush();
                                        quantityBad = true;
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "ERROR! Invalid input. Please try " +
                                            "again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    quantityBad = false;
                                }
                            } while (!quantityBad);

                            String buy = br.readLine();
                            if (buy.equals("SUCCESS")) {
                                JOptionPane.showMessageDialog(null, "Product bought successfully!",
                                        "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available.",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            pw.write("2");
                            pw.println();
                            pw.flush();
                            empty = br.readLine();
                            JOptionPane.showMessageDialog(null, "Product successfully added to cart.", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (option.equals("Sort the market by price")) {

                        pw.write("1");
                        pw.println();
                        pw.flush();

                        String products = br.readLine();
                        String[] productArray = products.split(";");
                        String[] display = new String[productArray.length];
                        int index = 0;
                        for (String line : productArray) {
                            String[] lineArray = line.split(",");
                            display[index] = String.format("Product Number: %s, Product Name: %s, Store name: %s" +
                                            ", Price: $%s", lineArray[0],
                                    lineArray[1], lineArray[2], lineArray[5]);
                            index++;

                        }

                        String sorted = (String) JOptionPane.showInputDialog(null, "" +
                                        "Products sorted by price: (select one you may be interested in purchasing)",
                                "Marketplace", JOptionPane.INFORMATION_MESSAGE, null, display,
                                display[0]);

                        String productNumber = null;

                        if (sorted == null) {
                            return;
                        } else {
                            productNumber = sorted.substring(16, 22);
                        }

                        pw.write("6");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();

                        String[] productOptionArray = sorted.split(",");
                        pw.write(productNumber);
                        pw.println();
                        pw.flush();

                        String info = br.readLine();
                        String[] outArray = info.split(",");
                        String out = "";
                        String[] next = new String[2];
                        next[0] = "Buy product";
                        next[1] = "Add to cart";
                        String next1 = (String) JOptionPane.showInputDialog(null, info + "What would you like " +
                                "to do next?", "Product info", JOptionPane.QUESTION_MESSAGE, null, next, next[0]);

                        if (next1 == null) {
                            return;
                        } else if (next1.equals("Buy product")) {
                            pw.write("1");
                            pw.println();
                            pw.flush();

                            empty = br.readLine();

                            int intQuantity = -1;

                            boolean quantityBad = true;
                            do {
                                String quantity = JOptionPane.showInputDialog(null, "Enter the quantity you wish to buy:",
                                        "Quantity", JOptionPane.QUESTION_MESSAGE);
                                if (quantity == null) {
                                    return;
                                }
                                try {
                                    intQuantity = Integer.parseInt(quantity);
                                    if (intQuantity < 1) {
                                        JOptionPane.showMessageDialog(null, "Error, please enter a positive number!",
                                                "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                                        quantityBad = false;
                                    } else {
                                        pw.write(quantity);
                                        pw.println();
                                        pw.flush();
                                        quantityBad = true;
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "ERROR! Invalid input. Please try " +
                                            "again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    quantityBad = false;
                                }

                            } while (!quantityBad);

                            String buy = br.readLine();
                            if (buy.equals("SUCCESS")) {
                                JOptionPane.showMessageDialog(null, "Product bought successfully!",
                                        "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available.",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            pw.write("2");
                            pw.println();
                            pw.flush();
                            empty = br.readLine();
                            JOptionPane.showMessageDialog(null, "Product successfully added to cart.", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (option.equals("Sort the market by quantity")) {
                        pw.write("2");
                        pw.println();
                        pw.flush();

                        String products = br.readLine();
                        String[] productArray = products.split(";");
                        String[] display = new String[productArray.length];
                        int index = 0;
                        for (String line : productArray) {
                            String[] lineArray = line.split(",");
                            display[index] = String.format("Product Number: %s, Product Name: %s, Store name: %s" +
                                            ", Quantity: %s, Price: %s", lineArray[0],
                                    lineArray[1], lineArray[2], lineArray[4], lineArray[5]);
                            index++;

                        }
                        String productOption = (String) JOptionPane.showInputDialog(null, "" +
                                        "Sorted by quantity in increasing order. (select a product you may be interested in purchasing)",
                                "Marketplace", JOptionPane.INFORMATION_MESSAGE, null, display,
                                display[0]);

                        String productNumber;

                        if (productOption == null) {
                            return;
                        } else {
                            productNumber = productOption.substring(16, 22);
                        }

                        pw.write("6");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();

                        String[] productOptionArray = productOption.split(",");
                        pw.write(productNumber);
                        pw.println();
                        pw.flush();

                        String info = br.readLine();
                        String[] outArray = info.split(",");
                        String out = "";
                        String[] next = new String[2];
                        next[0] = "Buy product";
                        next[1] = "Add to cart";
                        String next1 = (String) JOptionPane.showInputDialog(null, "What would you like " +
                                "to do next?", "Product info", JOptionPane.QUESTION_MESSAGE, null, next, next[0]);

                        if (next1 == null) {
                            return;
                        } else if (next1.equals("Buy product")) {
                            pw.write("1");
                            pw.println();
                            pw.flush();

                            empty = br.readLine();

                            int intQuantity = -1;

                            boolean quantityBad = true;
                            do {
                                String quantity = JOptionPane.showInputDialog(null, "Enter the quantity you wish to buy:",
                                        "Quantity", JOptionPane.QUESTION_MESSAGE);
                                if (quantity == null) {
                                    return;
                                }
                                try {
                                    intQuantity = Integer.parseInt(quantity);
                                    if (intQuantity < 1) {
                                        JOptionPane.showMessageDialog(null, "Error, please enter a positive number!",
                                                "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                                        quantityBad = false;
                                    } else {
                                        pw.write(quantity);
                                        pw.println();
                                        pw.flush();
                                        quantityBad = true;
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "ERROR! Invalid input. Please try " +
                                            "again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    quantityBad = false;
                                }

                            } while (!quantityBad);

                            String buy = br.readLine();
                            if (buy.equals("SUCCESS")) {
                                JOptionPane.showMessageDialog(null, "Product bought successfully!",
                                        "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available.",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            pw.write("2");
                            pw.println();
                            pw.flush();
                            empty = br.readLine();
                            JOptionPane.showMessageDialog(null, "Product successfully added to cart.", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (option.equals("View shopping cart")) {
                        pw.write("3");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();
                        System.out.println(empty);

                        if (empty.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Your shopping cart is empty.", "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
                            pw.write("1");
                            pw.println();
                            pw.flush();
                        } else {

                            String[] productoss = empty.split(";");

                            String[] out = new String[productoss.length];
                            int index = 0;

                            for (String line : productoss) {
                                String[] lineArray = line.split(",");
                                String display = String.format("Product Number: %s, Product Name: %s, Store Name: %s, " +
                                                "Price: $%s",
                                        lineArray[0], lineArray[1], lineArray[2], lineArray[5]);
                                out[index] = display;
                                index++;
                            }

                            String[] cartOptions = new String[3];
                            cartOptions[0] = "Purchase entire cart";
                            cartOptions[1] = "Delete a product from cart";
                            cartOptions[2] = "Return to menu";

                            JOptionPane.showInputDialog(null, "Here are all the items currently in your shopping cart:",
                                    "Shopping Cart", JOptionPane.QUESTION_MESSAGE, null, out, out[0]);

                            String next3 = (String) JOptionPane.showInputDialog(null,
                                    "What would you like to do?", "Shopping Cart", JOptionPane.QUESTION_MESSAGE,
                                    null, cartOptions, cartOptions[0]);

                            if (next3 == null) {
                                return;
                            }

                            if (next3.equals("Purchase entire cart")) {
                                pw.write("2");
                                pw.println();
                                pw.flush();

                                JOptionPane.showMessageDialog(null, "Cart bought succcessfully!",
                                        "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

                                br.readLine();

                            } else if (next3.equals("Delete a product from cart")) {
                                String numberrr = (String) JOptionPane.showInputDialog(null, "Which product would you like to delete?",
                                        "Shopping Cart", JOptionPane.QUESTION_MESSAGE, null, out, out[0]);

                                String numberr = numberrr.substring(16, 22);

                                pw.write("3");
                                pw.println();
                                pw.flush();

                                br.readLine();

                                pw.write(numberr);
                                pw.println();
                                pw.flush();

                                br.readLine();

                                JOptionPane.showMessageDialog(null, "Product deleted from cart.",
                                        "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

                            } else if (next3.equals("Return to menu")) {
                                pw.write("1");
                                pw.println();
                                pw.flush();
                                continue;
                            } else {
                                return;
                            }
                        }


                    } else if (option.equals("Search for product using search term")) {
                        pw.write("5");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();

                        String searchTerm = JOptionPane.showInputDialog(null, "Enter the search term:",
                                "Search", JOptionPane.INFORMATION_MESSAGE);
                        if (searchTerm == null) {
                            return;
                        } else {
                            pw.write(searchTerm);
                            pw.println();
                            pw.flush();
                        }
                        String product = br.readLine();

                        if (product.equals("")) {
                            JOptionPane.showMessageDialog(null, "Error! No products found.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String[] productArray = product.split(";");

                            String[] out = new String[productArray.length];
                            int index = 0;

                            for (String line : productArray) {
                                String[] lineArray = line.split(",");
                                String display = String.format("Product Number: %s, Product Name: %s, Store Name: %s, " +
                                                "Price: $%s",
                                        lineArray[0], lineArray[1], lineArray[2], lineArray[5]);
                                out[index] = display;
                                index++;
                            }

                            String productInfo = (String) JOptionPane.showInputDialog(null, "Search Results:",
                                    "Search Result", JOptionPane.INFORMATION_MESSAGE, null, out, out[0]);

                            String productNumber = null;

                            if (productInfo == null) {
                                return;
                            } else {
                                productNumber = productInfo.substring(16, 22);
                            }

                            pw.write("6");
                            pw.println();
                            pw.flush();

                            empty = br.readLine();

                            String[] productOptionArray = productInfo.split(",");
                            pw.write(productNumber);
                            pw.println();
                            pw.flush();

                            String info = br.readLine();
                            String[] outArray = info.split(",");
                            empty = "";
                            String[] next = new String[2];
                            next[0] = "Buy product";
                            next[1] = "Add to cart";
                            String next1 = (String) JOptionPane.showInputDialog(null, info + "What would you like " +
                                    "to do next?", "Product info", JOptionPane.QUESTION_MESSAGE, null, next, next[0]);

                            if (next1 == null) {
                                return;
                            } else if (next1.equals("Buy product")) {
                                pw.write("1");
                                pw.println();
                                pw.flush();

                                empty = br.readLine();

                                do {
                                    String quantity = JOptionPane.showInputDialog(null, "Enter the quantity you wish to buy:",
                                            "Quantity", JOptionPane.QUESTION_MESSAGE);
                                    if (quantity == null) {
                                        return;
                                    }
                                    try {
                                        int intQuantity = Integer.parseInt(quantity);
                                        pw.write(quantity);
                                        pw.println();
                                        pw.flush();
                                        break;
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "ERROR! Invalid input. Please try " +
                                                "again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                } while (true);

                                String buy = br.readLine();
                                if (buy.equals("SUCCESS")) {
                                    JOptionPane.showMessageDialog(null, "Product bought successfully!",
                                            "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available.",
                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                pw.write("2");
                                pw.println();
                                pw.flush();
                                empty = br.readLine();
                                JOptionPane.showMessageDialog(null, "Product successfully added to cart.", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    } else if (option.equals("View purchase history")) {
                        pw.write("4");
                        pw.println();
                        pw.flush();
                        String history = br.readLine();
                        if (history.equals("")) {
                            JOptionPane.showMessageDialog(null, "No purchases yet!",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String out = "Purchase history: \n";
                            String[] historyArray = history.split(";");
                            for (String product : historyArray) {
                                String[] productArray = product.split(",");
                                out = out + String.format("Product Number: %s, Product Name: %s, " +
                                                "Store Name: %s, Description: %s, Price: $%s\n",
                                        productArray[1], productArray[2], productArray[3], productArray[4],
                                        productArray[6]);
                            }
                            JOptionPane.showMessageDialog(null, out, "Purchase History",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (option.equals("Exit")) {
                        JOptionPane.showMessageDialog(null, "Thank you for using the Online Marketplace!",
                                "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
            } else if (role.equals("2")) {
                while (true) {
                    String[] options = {"Add new product to the market", "Remove a product from the market",
                            "Edit existing product", "View information about your store", "Import CSV file of products",
                            "View a customer's shopping cart", "Exit"};

                    String option = (String) JOptionPane.showInputDialog(null, "What would you like to do?",
                            "Menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    if (option == null) {
                        return;
                    } else if (option.equals("Add new product to the market")) {
                        pw.write("1");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();

                        String storeName = JOptionPane.showInputDialog(null, "Enter the name of the" +
                                " new product's store:", "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (storeName == null) {
                            return;
                        }

                        String productName = JOptionPane.showInputDialog(null, "Enter the name of the product:"
                                , "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (productName == null) {
                            return;
                        }

                        String description = JOptionPane.showInputDialog(null, "Write a short description for the product:"
                                , "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (description == null) {
                            return;
                        }
                        String quantity;
                        while (true) {
                            try {
                                quantity = JOptionPane.showInputDialog(null, "Enter the available quantity for the product:"
                                        , "Add product", JOptionPane.QUESTION_MESSAGE);
                                if (quantity == null) {
                                    return;
                                }
                                Integer.parseInt(quantity);
                                break;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid input! Pl" +
                                        "ease enter again.", "Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        String price;
                        while (true) {
                            try {
                                price = JOptionPane.showInputDialog(null, "Enter the price of a single product: (Do not include the '$')"
                                        , "Add product", JOptionPane.QUESTION_MESSAGE);
                                if (price == null) {
                                    return;
                                }
                                Double.parseDouble(price);
                                break;
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Invalid input! Pl" +
                                        "ease enter again", "Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        String out = storeName + "," + productName + "," + username + "," + description + "," +
                                quantity + "," + price;
                        pw.write(out);
                        pw.println();
                        pw.flush();

                        String status = br.readLine();
                        if (status.equals("ERROR")) {
                            JOptionPane.showMessageDialog(null, "ERROR! The store is not associated " +
                                    "with you as a seller", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Product successfully added to the " +
                                    "marketplace!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (option.equals("Remove a product from the market")) {
                        pw.write("2");
                        pw.println();
                        pw.flush();

                        String products = br.readLine();
                        if (products.equals("1")) {
                            JOptionPane.showMessageDialog(null, "ERROR! There are no products " +
                                    "associated with you as a seller", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String[] productArray = products.split(";");

                            String[] out = new String[productArray.length];
                            int index = 0;
                            for (String line : productArray) {
                                String[] lineArray = line.split(",");
                                out[index] = String.format("Product Number: %s, Product name: %s, Store name: %s",
                                        lineArray[0], lineArray[1], lineArray[2]);
                                index++;
                            }
                            String deleteProduct = (String) JOptionPane.showInputDialog(null ,"Which product " +
                                            "would you like to delete?", "Remove Product", JOptionPane.QUESTION_MESSAGE,
                                    null, out, out[0]);
                            if (deleteProduct == null) {
                                return;
                            } else {

                                String productNumber = deleteProduct.substring(16, 22);

                                pw.write(productNumber);
                                pw.println();
                                pw.flush();

                                String success = br.readLine();
                                JOptionPane.showMessageDialog(null, "The product was successfully " +
                                        "deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    } else if (option.equals("Edit existing product")) {
                        pw.write("3");
                        pw.println();
                        pw.flush();

                        String products = br.readLine();
                        if (products.equals("1")) {
                            JOptionPane.showMessageDialog(null, "ERROR! There are no products " +
                                    "associated with you as a seller", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String[] productArray = products.split(";");

                            String[] out = new String[productArray.length];
                            int index = 0;
                            for (String line : productArray) {
                                String[] lineArray = line.split(",");
                                out[index] = String.format("Product Number: %s, Product name: %s, Store name: %s, " +
                                                " Description: %s, Quantity: %s, Price: $%s",
                                        lineArray[0], lineArray[1], lineArray[2], lineArray[3], lineArray[4],
                                        lineArray[5]);
                                index++;
                            }
                            String editProduct = (String) JOptionPane.showInputDialog(null ,"Which product " +
                                            "would you like to edit?", "Edit Product", JOptionPane.QUESTION_MESSAGE,
                                    null, out, out[0]);

                            if (editProduct == null) {
                                return;
                            } else {
                                String[] editProductArray = editProduct.split(",");
                                String productNumber = editProduct.substring(16, 22);
                                String name = editProductArray[1].substring(15);
                                String description = editProductArray[3].substring(14);
                                String quantity = editProductArray[4].substring(11);
                                String price = editProductArray[5].substring(9);

                                String[] editOptions = {"Product Name", "Product Description", "Quantity", "Price"};
                                String edit = (String) JOptionPane.showInputDialog(null, "Which aspect of the product would you like " +
                                                "to edit?", "Edit product", JOptionPane.QUESTION_MESSAGE, null, editOptions,
                                        editOptions[0]);

                                if (edit == null) {
                                    return;
                                } else if (edit.equals("Product Name")) {
                                    name = JOptionPane.showInputDialog(null, "Enter the new " +
                                            "product name:", "Edit product name", JOptionPane.INFORMATION_MESSAGE);
                                    if (name == null) {
                                        break;
                                    }
                                } else if (edit.equals("Product Description")) {
                                    description = JOptionPane.showInputDialog(null, "Enter the new " +
                                            "description:", "Edit description", JOptionPane.INFORMATION_MESSAGE);
                                    if (description == null) {
                                        break;
                                    }
                                } else if (edit.equals("Quantity")) {
                                    while (true) {
                                        try {
                                            quantity = JOptionPane.showInputDialog(null, "Enter the new " +
                                                    "quantity:", "Edit quantity", JOptionPane.INFORMATION_MESSAGE);
                                            if (quantity == null) {
                                                break;
                                            }
                                            Integer.parseInt(quantity);
                                            break;
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Error! Invalid Input." +
                                                    "Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                } else if (edit.equals("Price")) {
                                    while (true) {
                                        try {
                                            price = JOptionPane.showInputDialog(null, "Enter the new " +
                                                    "price: (Do not include the '$')", "Edit price", JOptionPane.INFORMATION_MESSAGE);
                                            if (price == null) {
                                                break;
                                            }
                                            Double.parseDouble(price);
                                            break;
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Error! Invalid Input. " +
                                                    "Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                                String write  = productNumber + "," + name + "," + description + "," + quantity + "," + price;

                                pw.write(write);
                                pw.println();
                                pw.flush();

                                br.readLine();

                                JOptionPane.showMessageDialog(null, "Product successfully edited.", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } else if (option.equals("View information about your store")) {
                        pw.write("4");
                        pw.println();
                        pw.flush();

                        String seller = br.readLine();
                        if (seller.equals("1")) {
                            JOptionPane.showMessageDialog(null, "Error! There are no " +
                                    "stores associated with you as a seller", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String[] sellerArray = seller.split(";");

                            ArrayList<String> sellerStores = new ArrayList<>();
                            for (String line : sellerArray) {
                                String[] storeArray = line.split(",");
                                String store = storeArray[2];
                                if (!sellerStores.contains(store)) {
                                    sellerStores.add(store);
                                }
                            }

                            String[] sellerStoresArray = sellerStores.toArray(new String[sellerStores.size()]);

                            String store = (String) JOptionPane.showInputDialog(null, "Which store's infor" +
                                            "mation would you like to see?", "Store info", JOptionPane.QUESTION_MESSAGE,
                                    null, sellerStoresArray, sellerStoresArray[0]);

                            if (store == null) {
                                return;
                            } else {
                                pw.write(store);
                                pw.println();
                                pw.flush();

                                String show = br.readLine();
                                if (show.equals("1")) {
                                    JOptionPane.showMessageDialog(null, "Error! No activity has happened " +
                                            "in this store", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    String[] showArray = show.split(";");
                                    String[] out = new String[showArray.length];
                                    int index = 0;
                                    for (String text : showArray) {
                                        System.out.println(text);
                                        if (!text.contains("revenue")) {
                                            String[] textArray = text.split(",");
                                            out[index] = String.format("Product name: %s \t Store name: %s \t price: %s \t customer: %s\n", textArray[2],
                                                    textArray[3], textArray[6], textArray[0]);
                                            index++;
                                        } else {
                                            out[showArray.length - 1] = text;
                                        }
                                    }
                                    String output = "Store information:\n";
                                    for (String line : out) {
                                        output = output + "\n" + line;
                                    }
                                    JOptionPane.showMessageDialog(null, output, "Store information",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }

                    } else if (option.equals("Import CSV file of products")) {
                        pw.write("5");
                        pw.println();
                        pw.flush();

                        br.readLine();

                        String filePath = JOptionPane.showInputDialog(null, "Enter the path " +
                                        "of the CSV file you would like to import:", "Import file",
                                JOptionPane.INFORMATION_MESSAGE);

                        if (filePath == null) {
                            return;
                        } else {
                            pw.write(filePath);
                            pw.println();
                            pw.flush();

                            String success = br.readLine();
                            if (success.equals("1")) {
                                JOptionPane.showMessageDialog(null, "ERROR! Incorrect file path.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "File imported successfully.",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }

                    } else if (option.equals("View a customer's shopping cart")) {
                        pw.write("6");
                        pw.println();
                        pw.flush();

                        br.readLine();

                        String customerName = JOptionPane.showInputDialog(null, "Enter the " +
                                "customer's email whose shopping cart you would like to view:", "View " +
                                "Cart", JOptionPane.INFORMATION_MESSAGE);

                        if (customerName == null) {
                            return;
                        } else {
                            pw.write(customerName);
                            pw.println();
                            pw.flush();

                            String cart = br.readLine();
                            if (cart.equals("1")) {
                                JOptionPane.showMessageDialog(null, "Error! Nothing " +
                                        "is in this customer's cart yet!", "Error", JOptionPane.ERROR_MESSAGE) ;
                            } else {
                                String[] cartArray = cart.split(";");
                                String out = "Customer Cart:\n";
                                for (String text : cartArray) {
                                    System.out.println(text);
                                    String[] textArray = text.split(",");
                                    out = out + String.format("Product number: %s \t Product Name: %s \t " +
                                                    "Quantity available: %s \t Price: %s\n",
                                            textArray[0], textArray[1], textArray[4], textArray[5]);
                                }

                                JOptionPane.showMessageDialog(null, out, "Customer Cart", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } else if (option.equals("Exit")) {
                        JOptionPane.showMessageDialog(null, "Thank you for using the Online Marketplace!",
                                "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}