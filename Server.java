import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(4243);
                Socket socket = serverSocket.accept();
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                File f = new File("database.txt");
                File f1 = new File("accounts.txt");
                FileReader fr1 = new FileReader(f1);
                BufferedReader bfr1 = new BufferedReader(fr1);

                FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr);
                String text = bfr.readLine();
                ArrayList<String> databaseText = new ArrayList<String>();
                ArrayList<String> loginText = new ArrayList<String>();

                FileOutputStream fos = new FileOutputStream("accounts.txt");
                PrintWriter pw1 = new PrintWriter(fos);

                while (text != null) {
                    databaseText.add(text);
                    text = bfr.readLine();
                }
                text = bfr1.readLine();
                while (text != null) {
                    loginText.add(text);
                    text = bfr1.readLine();
                }
                boolean exist = false;
                boolean correct = false;
                String role = "";

                String username = "";
                String password = "";

                while (true) {
                    try {
                        while (true) {
                            String account = br.readLine();// input if the account exists or not
                            pw.println("okay"); // write an empty string to follow protocol
                            String user = "";
                            if (account.equals("1")) {
                                while (exist == false) {
                                    username = br.readLine(); // read username
                                    for (String line : loginText) {
                                        String[] lineArray = line.split(",");
                                        if (lineArray[1].equals(user)) {
                                            role = lineArray[0];
                                            exist = true;
                                        }
                                    }
                                    if (exist == true) {
                                        pw.println(true); // write true if the account exists
                                    } else {
                                        pw.println(false);// write false if the account does not exist
                                    }
                                }

                                while (correct == false) {
                                    password = br.readLine(); // read password
                                    for (String line : loginText) {
                                        String[] lineArray = line.split(",");
                                        if (lineArray[1].equals(user) && lineArray[2].equals(password)) {
                                            correct = true;
                                        }
                                    }

                                    if (correct == true) {
                                        pw.println(true); // writes true if the password is correct
                                    } else {
                                        pw.println(false); // writes false if the password is incorrect
                                    }
                                }
                                break;
                            } else {
                                role = br.readLine();// reads if the new account is seller
                                // account or customer account
                                // 1 for customer and 2 for seller

                                pw.println("okay"); // write an empty string to follow protocol
                                boolean right = false;
                                while (right == true) {
                                    username = br.readLine(); // reads the username for new account
                                    password = "";
                                    for (String line : loginText) {
                                        String[] lineArray = line.split(",");
                                        if (lineArray[1].equals(username)) {
                                            pw.println(false); //write false if the username already exists
                                        } else {
                                            right = true;
                                            pw.println(true); //write true if the username is valid
                                        }
                                        password = br.readLine(); // read password for the new account
                                        pw.println(""); // write empty string to follow protocol
                                    }
                                    pw1.println(role + "," + username + "," + password);
                                }
                            }// after creating the account, the user goes back to login page
                        }
                        if (role.equals("1")) {
                            Customer customer = new Customer (username, password);
                            String str = br.readLine(); // read an empty string to follow protocol
                            while (true) {
                                ArrayList<String> productList = customer.viewOverallMarket();
                                pw.println(productList); // writes an array list to the client
                                //containing all the products in the market
                                // each string contains product number, product name, store name and price
                                // all the values are separated by a coma

                                String option = br.readLine();// read what the customer wants to do
                                // 1 to sort the market by price
                                // 2 to sort the market by quantity
                                // 3 to view shopping cart
                                if (Integer.parseInt(option) == 1) {
                                    ArrayList<String> sortedMarket = customer.sortByPrice();
                                    pw.println(sortedMarket); // writes an arraylist containing the products on
                                    // the market sorted by price
                                    //each string contains everything except the seller's username
                                    //refer to sortByPrice method in Customer class
                                } else if (Integer.parseInt(option) == 2) {
                                    ArrayList<String> sortedMarket = customer.sortByQuantity();
                                    pw.println(sortedMarket); // writes an arraylist containing the products on
                                    // the market sorted by quantity
                                    //each string contains everything except the seller's username
                                    //refer to sortByQuantity method in Customer class
                                } else if (Integer.parseInt(option) == 3) {
                                    ArrayList<String> cart = customer.readCurrentShoppingCart(customer);
                                    pw.println(cart); // writes an arrayList containing all the products in the
                                    // shopping cart
                                    String next = br.readLine(); // reads what the customer wants to do next
                                    // 1 if the cart is empty: will take the customer back to the product list
                                    // 2 if the customer wants to but the cart
                                    // 3 if the customer wants to delete a product from the cart

                                    if (Integer.parseInt(next) == 1) {
                                        continue;
                                    } else if (Integer.parseInt(next) == 2) {
                                        customer.checkout(customer);
                                        customer.clearCart(customer);
                                        pw.println("success"); // writes success if the shopping cart is
                                        // successfully bought
                                    } else if (Integer.parseInt(next) == 3) {
                                        pw.println("okay"); // write a useless string to follow protocol
                                        boolean flag = false;
                                        do {
                                            String productNumber = br.readLine(); // reads the product number of the
                                            // product to be deleted
                                            ArrayList<String> currentCart = customer.readCurrentShoppingCart(customer);
                                            int index = -1;

                                            for (String product : currentCart) {
                                                index++;
                                                String[] productArray = product.split(",");
                                                if (Integer.parseInt(productArray[0]) == Integer.parseInt(productNumber)) {
                                                    currentCart.remove(index);
                                                    flag = true;
                                                    break;
                                                }
                                            }
                                            if (flag == false) {
                                                pw.println(1); // writes 1 if the product number is incorrect
                                                continue;
                                            } else {
                                                pw.println(2); //writes 2 if the product is successfully deleted
                                            }
                                            customer.writeCurrentShoppingCart(currentCart, customer);
                                        } while (!flag);
                                    }
                                }
                            }
                        }





                    } catch (SocketException e) {
                        continue;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SocketException e) {
                continue;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
