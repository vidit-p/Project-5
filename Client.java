import java.io.*;
import java.net.*;
import java.sql.Array;
import java.util.ArrayList;
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
                String exist = (String) JOptionPane.showInputDialog(null, "Login or create account",
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

                        username = JOptionPane.showInputDialog(null, "Enter username", "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (username == null) {
                            return;
                        }
                        pw.write(username);
                        pw.println();
                        pw.flush();

                        success = br.readLine();
                        if (success.equals("false")) {
                            JOptionPane.showMessageDialog(null, "ERROR: The username does not exist!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (!success.equals("true"));

                    do {
                        String passwd = JOptionPane.showInputDialog(null, "Enter password", "Password",
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
                            JOptionPane.showMessageDialog(null, "Incorrect password! Please try again",
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
                    String accountType = (String) JOptionPane.showInputDialog(null, "Type of account",
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
                        username = JOptionPane.showInputDialog(null, "Enter your username",
                                "Create account", JOptionPane.INFORMATION_MESSAGE);
                        pw.write(username);
                        pw.println();
                        pw.flush();

                        success = br.readLine();
                        if (success.equals("false")) {
                            JOptionPane.showMessageDialog(null, "Error: the username already exists",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        } else {
                            String passwd = JOptionPane.showInputDialog(null, "Enter the password for the account",
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
            if (role.equals("1")) {
                while (true) {
                    String[] options = {"View all the products", "Sort the market by price", "sort the market by " +
                            "quantity", "View shopping cart", "View purchase history",
                            "Search for product using search term", "Exit"};

                    String option = (String) JOptionPane.showInputDialog(null, "Please select what " +
                                    "you would like to do", "Menu", JOptionPane.INFORMATION_MESSAGE, null,
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
                                        "Select the product you would like to see information about",
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
                        String next1 = (String) JOptionPane.showInputDialog(null, info + " .What would you like" +
                                "to do next", "Product info", JOptionPane.QUESTION_MESSAGE, null, next, next[0]);

                        if (next1 == null) {
                            return;
                        } else if (next1.equals("Buy product")) {
                            pw.write("1");
                            pw.println();
                            pw.flush();

                            empty = br.readLine();

                            do {
                                String quantity = JOptionPane.showInputDialog(null, "Enter the quantity",
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
                                            "again", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (true);

                            String buy = br.readLine();
                            if (buy.equals("SUCCESS")) {
                                JOptionPane.showMessageDialog(null, "Product bought successfully",
                                        "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            pw.write("2");
                            pw.println();
                            pw.flush();
                            empty = br.readLine();
                            JOptionPane.showMessageDialog(null, "Product successfully added to cart", "Success",
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

                            do {
                                String quantity = JOptionPane.showInputDialog(null, "Enter the quantity",
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
                                            "again", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (true);

                            String buy = br.readLine();
                            if (buy.equals("SUCCESS")) {
                                JOptionPane.showMessageDialog(null, "Product bought successfully",
                                        "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            pw.write("2");
                            pw.println();
                            pw.flush();
                            empty = br.readLine();
                            JOptionPane.showMessageDialog(null, "Product successfully added to cart", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (option.equals("sort the market by quantity")) {
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

                            do {
                                String quantity = JOptionPane.showInputDialog(null, "Enter the quantity",
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
                                            "again", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (true);

                            String buy = br.readLine();
                            if (buy.equals("SUCCESS")) {
                                JOptionPane.showMessageDialog(null, "Product bought successfully",
                                        "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            pw.write("2");
                            pw.println();
                            pw.flush();
                            empty = br.readLine();
                            JOptionPane.showMessageDialog(null, "Product successfully added to cart", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (option.equals("View shopping cart")) {
                        pw.write("3");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();

                        if (empty.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Shopping cart is empty.", "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
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

                            JOptionPane.showInputDialog(null, "Here are all the items currently in your shopping cart.",
                                    "Shopping Cart", JOptionPane.QUESTION_MESSAGE, null, out, out[0]);

                            String next3 = (String) JOptionPane.showInputDialog(null,
                                    "What would you like to do?", "Shopping Cart", JOptionPane.QUESTION_MESSAGE,
                                    null, cartOptions, cartOptions[0]);

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

                                JOptionPane.showMessageDialog(null, "Product deleted from cart.",
                                        "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

                            } else if (next3.equals("Return to menu")) {
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

                        String searchTerm = JOptionPane.showInputDialog(null, "Enter the search term",
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
                            JOptionPane.showMessageDialog(null, "Error! No products found",
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
                                    String quantity = JOptionPane.showInputDialog(null, "Enter the quantity",
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
                                                "again", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                } while (true);

                                String buy = br.readLine();
                                if (buy.equals("SUCCESS")) {
                                    JOptionPane.showMessageDialog(null, "Product bought successfully",
                                            "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR: Not enough quantity available",
                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                pw.write("2");
                                pw.println();
                                pw.flush();
                                empty = br.readLine();
                                JOptionPane.showMessageDialog(null, "Product successfully added to cart", "Success",
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
                                    JOptionPane.INFORMATION_MESSAGE) ;
                        }
                    }
                }
            } else if (role.equals("2")) {
                while (true) {
                    String[] options = {"Add new product to the market", "Remove a product from the market",
                            "Edit existing product", "View information about your store", "Import CSV file of products",
                            "View a customer's shopping cart", "Exit"};

                    String option = (String) JOptionPane.showInputDialog(null, "What would you like to do:",
                            "Menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    if (option == null) {
                        return;
                    } else if (option.equals("Add new product to the market")) {
                        pw.write("1");
                        pw.println();
                        pw.flush();

                        String empty = br.readLine();

                        String storeName = JOptionPane.showInputDialog(null, "Enter the name of the" +
                                "store to which you would like to add the product", "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (storeName == null) {
                            return;
                        }

                        String productName = JOptionPane.showInputDialog(null, "Enter the name of the product"
                                , "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (productName == null) {
                            return;
                        }

                        String description = JOptionPane.showInputDialog(null, "Write a short description for the product"
                                , "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (description == null) {
                            return;
                        }

                        String quantity = JOptionPane.showInputDialog(null, "Enter the available quantity for the product"
                                , "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (quantity == null) {
                            return;
                        }

                        String price = JOptionPane.showInputDialog(null, "Enter the price of a single product"
                                , "Add product", JOptionPane.QUESTION_MESSAGE);
                        if (price == null) {
                            return;
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
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
