import java.io.*;
import java.net.*;
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

                        String username = JOptionPane.showInputDialog(null, "Enter username", "Login",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (username == null) {
                            return;
                        }
                        pw.write(username);
                        pw.println();
                        pw.flush();

                        success = br.readLine();
                        System.out.println("success: " + success);
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
                    System.out.println(empty);
                    while (true) {
                        String success = "";
                        String username = JOptionPane.showInputDialog(null, "Enter your username",
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

            System.out.println("here");
            pw.write("");
            pw.println();
            pw.flush();

            String role = br.readLine();
            //System.out.println("dfghj");
            System.out.println(role);
            if (role.equals("1")) {
                while (true) {
                    String[] options = {"View all the products", "Sort the market by price", "sort the market by " +
                            "quantity", "View shopping cart", "View purchase history", "Search for product using " +
                            "search term", "View product info", "Exit"};

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
                            System.out.println(lineArray.toString());
                            display[index] = String.format("Product Number: %s, Product Name: %s, Store name: %s" +
                                            ", Price: %s", lineArray[0],
                                    lineArray[1], lineArray[2], lineArray[3]);
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
                            productNumber = productOption.substring(16, 23);
                            System.out.println(productNumber);
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
                        String next1 = (String) JOptionPane.showInputDialog(null, info + "What would you like " +
                                "to do next?", "Product info", JOptionPane.QUESTION_MESSAGE, null, next, next[0]);
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
                            System.out.println(lineArray.toString());
                            display[index] = String.format("Product Number: %s, Product Name: %s, Store name: %s" +
                                            ", Price: $%s", lineArray[0],
                                    lineArray[1], lineArray[2], lineArray[5]);
                            index++;

                        }

                        String sorted = (String) JOptionPane.showInputDialog(null, "" +
                                        "Products sorted by price: (select one you may be interested in purchasing)",
                                "Marketplace", JOptionPane.INFORMATION_MESSAGE, null, display,
                                display[0]);

                        String productNumber;

                        if (sorted == null) {
                            return;
                        } else {
                            productNumber = sorted.substring(16, 23);
                            System.out.println(productNumber);
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

                        pw.write(sorted);
                        pw.println();
                        pw.flush();



                        // TODO pick selected product and ask if they want to buy or not
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
