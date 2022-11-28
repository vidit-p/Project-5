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
                            pw.write(""); // write an empty string to follow protocol
                            pw.println();
                            pw.flush();
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
                                        pw.write("true"); // write true if the account exists
                                        pw.println();
                                        pw.flush();
                                    } else {
                                        pw.write("false");// write false if the account does not exist
                                        pw.println();
                                        pw.flush();
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
                                        pw.write("true"); // writes true if the password is correct
                                    } else {
                                        pw.write("false"); // writes false if the password is incorrect
                                    }
                                    pw.println();
                                    pw.flush();
                                }
                                break;
                            } else {
                                role = br.readLine();// reads if the new account is seller
                                // account or customer account
                                // 1 for customer and 2 for seller

                                pw.write(""); // write an empty string to follow protocol
                                pw.println();
                                pw.flush();
                                boolean right = false;
                                while (right == true) {
                                    username = br.readLine(); // reads the username for new account
                                    password = "";
                                    for (String line : loginText) {
                                        String[] lineArray = line.split(",");
                                        if (lineArray[1].equals(username)) {
                                            pw.write("false"); //write false if the username already exists
                                            pw.println();
                                            pw.flush();
                                        } else {
                                            right = true;
                                            pw.write("true"); //write true if the username is valid
                                            pw.println();
                                            pw.flush();
                                        }
                                        password = br.readLine(); // read password for the new account
                                        pw.write(""); // write empty string to follow protocol
                                        pw.println();
                                        pw.flush();
                                    }
                                    pw1.println(role + "," + username + "," + password);
                                }
                            }// after creating the account, the user goes back to login page
                        }
                        if (role.equals("1")) {
                            Customer customer = new Customer (username, password);
                            String str = br.readLine(); // read an empty string to follow protocol
                            while (true) {
                                //TODO: resolve the view product issue
                                ArrayList<String> productList = customer.viewOverallMarket();
                                String out = String.join(";", productList);
                                pw.write(out); // writes an array list converted to string to the client
                                // each product will be separated by ';'. So split the string with ';' as the
                                // separator
                                pw.println();
                                pw.flush();
                                //containing all the products in the market
                                // each string contains product number, product name, store name and price
                                // all the values are separated by a coma

                                String option = br.readLine();// read what the customer wants to do
                                // 1 to sort the market by price
                                // 2 to sort the market by quantity
                                // 3 to view shopping cart
                                // 4 to view purchase history
                                // 5 to search for products using a search term
                                // 6 to view product info
                                // 7 to exit
                                if (Integer.parseInt(option) == 1) {
                                    ArrayList<String> sortedMarket = customer.sortByPrice();
                                    String output = String.join(";", sortedMarket);
                                    pw.write(output); // writes an arraylist as a string containing the products on
                                    // the market sorted by price
                                    // each product will be separated by ';'. So split the string with ';' as the
                                    // separator
                                    //each string contains everything except the seller's username
                                    //refer to sortByPrice method in Customer class
                                    pw.println();
                                    pw.flush();
                                } else if (Integer.parseInt(option) == 2) {
                                    ArrayList<String> sortedMarket = customer.sortByQuantity();
                                    String output = String.join(";", sortedMarket);
                                    pw.write(output); // writes an arraylist as a string containing the products on
                                    // the market sorted by quantity
                                    // each product will be separated by ';'. So split the string with ';' as the
                                    // separator
                                    //each string contains everything except the seller's username
                                    //refer to sortByQuantity method in Customer class
                                    pw.println();
                                    pw.flush();
                                } else if (Integer.parseInt(option) == 3) {
                                    ArrayList<String> cart = customer.readCurrentShoppingCart(customer);
                                    String output = String.join(";", cart);
                                    pw.write(output); // writes an arrayList as a stirng containing all the products in the
                                    // shopping cart
                                    // each product will be separated by ';'. So split the string with ';' as the
                                    // separator
                                    pw.println();
                                    pw.flush();
                                    String next = br.readLine(); // reads what the customer wants to do next
                                    // 1 if the cart is empty: will take the customer back to the product list
                                    // 2 if the customer wants to but the cart
                                    // 3 if the customer wants to delete a product from the cart

                                    if (Integer.parseInt(next) == 1) {
                                        continue;
                                    } else if (Integer.parseInt(next) == 2) {
                                        int buy = customer.checkout(customer);
                                        if (buy == 1) {
                                            pw.write("failure"); // writes failure if any of the product is not available
                                            // that is, has 0 quantity
                                            // buys all other available products and clears the cart
                                            pw.println();
                                            pw.flush();
                                        } else {
                                            pw.write("success"); // writes success if the shopping cart is
                                            // successfully bought
                                            pw.println();
                                            pw.flush();
                                        }
                                    } else if (Integer.parseInt(next) == 3) {
                                        pw.write("okay"); // write a useless string to follow protocol
                                        pw.println();
                                        pw.flush();
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
                                                pw.write(1); // writes 1 if the product number is incorrect
                                                pw.println();
                                                pw.flush();
                                                continue;
                                            } else {
                                                pw.write(2); //writes 2 if the product is successfully deleted
                                                pw.println();
                                                pw.flush();
                                            }
                                            customer.writeCurrentShoppingCart(currentCart, customer);
                                        } while (!flag);
                                    }
                                } else if (Integer.parseInt(option) == 4) {
                                    ArrayList<String> history = customer.viewHistory();
                                    String output = String.join(";", history);
                                    pw.write(output); // writes an arraylist as a string to the client containing the purchase history
                                    // each string contains the product bought by the customer
                                    // each product will be separated by ';'. So split the string with ';' as the
                                    // separator
                                    // returns empty arrayList if no products are bought till now
                                    pw.println();
                                    pw.flush();
                                } else if (Integer.parseInt(option) == 5) {
                                    pw.write("okay");// write empty string to follow protocol
                                    pw.println();
                                    pw.flush();
                                    String searchTerm = br.readLine();// reads the search term
                                    ArrayList<String> search = customer.searchUsingTerms(searchTerm);
                                    String output = String.join(";", search);
                                    pw.write(output); // writes an arrayList containing all the products related to search term
                                    // returns an empty arrayList if there are no matches
                                    // each product will be separated by ';'. So split the string with ';' as the
                                    // separator
                                } else if (Integer.parseInt(option) == 6) {
                                    pw.write("okay"); // write empty string to follow protocol
                                    pw.println();
                                    pw.flush();
                                    String productNumber = br.readLine(); // reads product number whose information is needed
                                    String info = customer.viewProductInfo(Integer.parseInt(productNumber));
                                    pw.write(info); // writes the string containing the product to the client
                                    pw.println();
                                    pw.flush();

                                    String next = br.readLine(); // reads what the user wants to do next
                                    // 1. if the user wants to buy product
                                    // 2. if the user wants to add to shopping cart

                                    if (Integer.parseInt(next) == 1) {
                                        pw.write("okay");
                                        pw.println();
                                        pw.flush();
                                        String quantity = br.readLine(); // reads quantity from the server
                                        int buy = customer.buyProduct(Integer.parseInt(productNumber), Integer.parseInt(quantity));
                                        if (buy == 0) {
                                            pw.write("SUCCESS"); // writes success if the product was bought successfully
                                            pw.println();
                                            pw.flush();
                                        } else if (buy == 1) {
                                            pw.write("ERROR"); // writes error if the quantity is greater than the available quantity
                                            pw.println();
                                            pw.flush();
                                        }
                                    } else if (Integer.parseInt(next) == 2) {
                                        customer.addToCart(Integer.parseInt(productNumber), customer);
                                        pw.write("SUCCESS"); // writes success if the product was successfully added to cart
                                        pw.println();
                                        pw.flush();
                                    }
                                } else if (Integer.parseInt(option) == 7) {
                                    break;
                                }
                            }
                        } else {
                            while (true) {
                                Seller seller = new Seller(username, password);
                                String option = br.readLine(); // reads what the seller wants to do
                                // 1. If the seller wants to add a new product

                                if (Integer.parseInt(option) == 1) {
                                    pw.println(""); //writes an empty string to follow protocol

                                    String product = br.readLine(); // reads the product details from the user
                                    // the string should be in the following format: storeName,productName,sellerName,
                                    // description,quantity,price

                                    String[] productArray = product.split(",");
                                    int add = seller.addProduct(productArray[0], productArray[1], productArray[2], productArray[3],
                                            Integer.parseInt(productArray[4]), Double.parseDouble(productArray[5]));

                                    if (add == 1) {
                                        pw.println("ERROR"); // writes error if the store is not owned by the seller
                                    } else if (add == 2) {
                                        pw.println("SUCCESS"); // writes success if the product is successfully added
                                    }
                                }
                            }

                        }





                    } catch (SocketException e) {
                        break;
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
