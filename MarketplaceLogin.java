import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Marketplace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ans;
        String b;
        String sellerOrCustomer;
        String username;
        String password;
        String password2;
        String line;
        String subLine;
        String realUsername = " ";

        JOptionPane.showMessageDialog(null, "Welcome to the digital marketplace!", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
        do {

            ans = JOptionPane.showConfirmDialog(null, "Do you currently have an account?", "Marketplace", JOptionPane.YES_NO_OPTION);

            if (ans == JOptionPane.YES_OPTION) {
                do {
                    username = JOptionPane.showInputDialog(null, "Please enter your email or username:", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader("accounts.txt"));
                        for (int i = 0; i < 1; i++) {
                            while ((line = bufferedReader.readLine()) != null) {
                                realUsername = line.substring(line.indexOf(' ') + 1, line.indexOf('_'));
                                if (realUsername.equals(username)) {
                                    JOptionPane.showMessageDialog(null, "Username exists.", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                                    do {
                                        password = JOptionPane.showInputDialog(null, "Please enter your password:", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                                        if (line.substring(line.indexOf('_') + 1).equals(password)) {
                                            JOptionPane.showMessageDialog(null, "You are now logged in to your account!", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                                            if (line.charAt(0) == '1') {
                                                Seller seller = new Seller(username, password);
                                                seller.initialise();

                                                String answer;
                                                String change = "1";
                                                while (true) {

                                                    do {
                                                        System.out.println("---------------------------------");
                                                        System.out.println("What would you like to do?");
                                                        System.out.println("1 = Put new product on the market");
                                                        System.out.println("2 = Remove product from the market");
                                                        System.out.println("3 = Edit existing product");
                                                        System.out.println("4 = View information about a store");
                                                        System.out.println("5 = Import a CSV file of products");
                                                        System.out.println("6 = View customer's shopping cart");
                                                        System.out.println("7 = Exit");

                                                        answer = scanner.nextLine();
                                                        String name = "";
                                                        String description = "";
                                                        String store = "";
                                                        int quantity = -1;
                                                        double price = -0.01;

                                                        if (answer.equals("1")) {
                                                            int add = -1;
                                                            do {
                                                                System.out.println("What is the name of this product?");
                                                                name = scanner.nextLine();
                                                                System.out.println("Please provide a short description of this product.");
                                                                description = scanner.nextLine();
                                                                System.out.println("What store is this product from?");
                                                                System.out.println("If the store does not exist, a new store will be " +
                                                                        "created for you and the product will be added to that store");
                                                                store = scanner.nextLine();
                                                                do {
                                                                    System.out.println("How many of this product are you selling!");
                                                                    try {
                                                                        quantity = scanner.nextInt();
                                                                        scanner.nextLine();
                                                                    } catch (Exception e) {
                                                                        System.out.println("Invalid input! Please enter a positive integer.");
                                                                    }
                                                                } while (quantity == -1);
                                                                do {
                                                                    System.out.println("How much will each individual product cost? (in dollars)");
                                                                    try {
                                                                        price = scanner.nextDouble();
                                                                        scanner.nextLine();
                                                                    } catch (Exception e) {
                                                                        System.out.println("Invalid input! Please enter a positive number.");
                                                                    }
                                                                } while (price == -0.01);
                                                                System.out.println("Creating product...");
                                                                add = seller.addProduct(store, name, username, description, quantity, price);
                                                                if (add == 2) {
                                                                    System.out.println("Product added to new store!");
                                                                } else if (add == 1) {
                                                                    System.out.println("The store is not associated with you as a seller!");
                                                                    continue;
                                                                }
                                                                System.out.println("Product created!");
                                                            } while (add == 1);
                                                        } else if (answer.equals("2")) {
                                                            int productNum = -1;
                                                            do {
                                                                seller.viewOverallMarket();
                                                                System.out.println("What is the product number of the product you wish to delete?");
                                                                try {
                                                                    productNum = Integer.parseInt(scanner.nextLine());
                                                                } catch (Exception e) {
                                                                    System.out.println("Invalid input! Please try again.");
                                                                    productNum = -1;
                                                                }
                                                                if (productNum != -1) {
                                                                    int delete = seller.deleteProduct(productNum);
                                                                    if (delete == 0) {
                                                                        System.out.println("Product successfully deleted!");
                                                                    } else if (delete == 1) {
                                                                        System.out.println("The product number does not exist! Please try again");
                                                                        productNum = -1;
                                                                    } else {
                                                                        System.out.println("The product is not in a store you own! Please try again");
                                                                        productNum = -1;
                                                                    }
                                                                }
                                                            } while (productNum == -1);


                                                        } else if (answer.equals("3")) {
                                                            int proNum = -1;
                                                            boolean hey = false;
                                                            do {
                                                                seller.viewOverallMarket();
                                                                System.out.println("Which product would you like to change? (enter product number)");
                                                                String prodNum = scanner.nextLine();
                                                                try {
                                                                    proNum = Integer.parseInt(prodNum);
                                                                } catch (Exception e) {
                                                                    System.out.println("Input does not match any product number. Try again.");
                                                                }
                                                                BufferedReader bfr = new BufferedReader(new FileReader("database.txt"));
                                                                while ((line = bfr.readLine()) != null) {

                                                                    if (prodNum.equals(line.substring(0, line.indexOf(',')))) {
                                                                        hey = true;
                                                                        subLine = line.substring(line.indexOf(',') + 1);
                                                                        name = subLine.substring(0, subLine.indexOf(','));

                                                                        subLine = subLine.substring(subLine.indexOf(',') + 1);
                                                                        store = subLine.substring(0, subLine.indexOf(','));

                                                                        subLine = subLine.substring(subLine.indexOf(',') + 1);
                                                                        description = subLine.substring(0, subLine.indexOf(','));

                                                                        subLine = subLine.substring(subLine.indexOf(',') + 1);
                                                                        quantity = Integer.parseInt(subLine.substring(0, subLine.indexOf(',')));

                                                                        subLine = subLine.substring(subLine.indexOf(',') + 1);
                                                                        price = Double.parseDouble(subLine.substring(0, subLine.indexOf(',')));

                                                                        username = subLine.substring(subLine.indexOf(',') + 1);

                                                                    }
                                                                }
                                                                if (!hey && proNum != -1) {
                                                                    System.out.println("Input does not match any product number. Try again.");
                                                                }

                                                            } while ((proNum == -1) || !hey);

                                                            do {

                                                                System.out.println("Which part of the product would you like to change?");
                                                                System.out.println("1 = Name");
                                                                System.out.println("2 = Description");
                                                                System.out.println("3 = Quantity");
                                                                System.out.println("4 = Price");
                                                                System.out.println("5 = Nothing else");
                                                                change = scanner.nextLine();
                                                                if (change.equals("1")) {
                                                                    System.out.println("What is the new product name?");
                                                                    name = scanner.nextLine();
                                                                    System.out.println(name);
                                                                } else if (change.equals("2")) {
                                                                    System.out.println("What is the new product description?");
                                                                    description = scanner.nextLine();
                                                                } else if (change.equals("3")) {
                                                                    quantity = -1;
                                                                    do {
                                                                        System.out.println("What is the new product quantity?");
                                                                        try {
                                                                            quantity = scanner.nextInt();
                                                                            scanner.nextLine();
                                                                        } catch (Exception e) {
                                                                            System.out.println("Invalid input! Try again.");
                                                                        }
                                                                    } while (quantity == -1);
                                                                } else if (change.equals("4")) {
                                                                    price = -0.01;
                                                                    do {
                                                                        System.out.println("What is the new product price?");
                                                                        try {
                                                                            price = scanner.nextDouble();
                                                                            scanner.nextLine();
                                                                        } catch (Exception e) {
                                                                            System.out.println("Invalid input! Try again.");
                                                                        }
                                                                    } while (price == -0.01);
                                                                } else if (change.equals("5")) {
                                                                    int yo = seller.editProduct(proNum, name, description, quantity, price);
                                                                    if (yo == 0) {
                                                                        System.out.println("Product successfully edited!");
                                                                    } else if (yo == 2) {
                                                                        System.out.println("Product number does not exist!");
                                                                    } else if (yo == 1) {
                                                                        System.out.println("Product does not belong to your store!");
                                                                    }
                                                                }

                                                            } while (change.equals("1") || change.equals("2") || change.equals("3") || change.equals("4"));

                                                        } else if (answer.equals("4")) {
                                                            System.out.println("Which store would you like to see info about?");
                                                            String storee = scanner.nextLine();
                                                            ArrayList<String> info = seller.storeInfo(storee);
                                                            if (info == null) {
                                                                System.out.println("The store does not exist");
                                                                continue;
                                                            } else {
                                                                for (String text : info) {
                                                                    if (!text.contains("revenue")) {
                                                                        String[] textArray = text.split(",");
                                                                        System.out.printf("Product name: %s \t Store name: %s \t price: %s \t customer: %s\n", textArray[1],
                                                                                textArray[2], textArray[6], textArray[0]);
                                                                    } else {
                                                                        System.out.println(text);
                                                                    }
                                                                }
                                                            }

                                                        } else if (answer.equalsIgnoreCase("5")) {
                                                            System.out.println("Enter the path of the CSV file you want to import");
                                                            System.out.println("The format of the CSV file should be: Product number, product name, store name, " +
                                                                    "description, quantity available, price (as a decimal), Seller email");
                                                            String file = scanner.nextLine();
                                                            try {
                                                                int imp = seller.importFile(file);
                                                                if (imp == 1) {
                                                                    System.out.println("The file is not in the correct format!");
                                                                } else {
                                                                    System.out.println("Products from the file successfully added to the database");
                                                                }
                                                            } catch (FileNotFoundException e) {
                                                                System.out.println("Incorrect File path!");
                                                            }
                                                        } else if (answer.equals("6")) {
                                                            System.out.println("Enter the customer's username");
                                                            String name1 = scanner.nextLine();
                                                            Customer customer = new Customer(name1);
                                                            ArrayList<String> cart = customer.readCurrentShoppingCart(customer);
                                                            if (cart.isEmpty()) {
                                                                System.out.println("Nothing in the shopping cart yet!");
                                                                System.out.println("Taking you back to the product list");
                                                            } else {
                                                                for (String text : cart) {
                                                                    String[] textArray = text.split(",");
                                                                    System.out.printf("Product number: %s \t Product Name: %s \t " +
                                                                                    "Quantity available: %s \t Price: %s\n",
                                                                            textArray[0], textArray[1], textArray[4], textArray[5]);
                                                                }
                                                            }
                                                        } else if (answer.equals("7")) {
                                                            System.out.println("Thank you for using the Online Marketplace!");
                                                            return;
                                                        } else {
                                                            System.out.println("Invalid input! try again.");
                                                        }
                                                    } while (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4") || answer.equals("5"));
                                                }

                                            } else if (line.charAt(0) == '2') {
                                                Customer customer = new Customer(username, password);
                                                customer.initialise();
                                                String answer = null;
                                                String info = "";
                                                while (true) {
                                                    System.out.println("---------------------------------");
                                                    customer.viewOverallMarket();
                                                    String sort = "";
                                                    do {
                                                        System.out.println("1 = Sort by price");
                                                        System.out.println("2 = Sort by quantity");
                                                        System.out.println("3 = Continue without sorting");
                                                        System.out.println("4 = View shopping cart");
                                                        System.out.println("5 = View purchase history");
                                                        System.out.println("6 = Exit");
                                                        sort = scanner.nextLine();

                                                        if (sort.equalsIgnoreCase("1")) {
                                                            ArrayList<String> sorted = customer.sortByPrice();
                                                            for (String text : sorted) {
                                                                String[] product = text.split(",");
                                                                System.out.printf("Product Number: %s \t Product Name:%s \t" +
                                                                                " Store Name: %s \t Price: %s\n",
                                                                        product[0], product[1], product[2], product[5]);
                                                            }
                                                        } else if (sort.equalsIgnoreCase("2")) {
                                                            ArrayList<String> sorted = customer.sortByQuantity();
                                                            for (String text : sorted) {
                                                                String[] product = text.split(",");
                                                                System.out.printf("Product Number: %s \t Product Name:%s \t" +
                                                                                " Store Name: %s \t Price: %s\n",
                                                                        product[0], product[1], product[2], product[5]);
                                                            }
                                                        } else if (sort.equalsIgnoreCase("3")) {
                                                            continue;
                                                        } else if (sort.equalsIgnoreCase("4")) {
                                                            ArrayList<String> cart = customer.readCurrentShoppingCart(customer);
                                                            if (cart.isEmpty()) {
                                                                System.out.println("Nothing in the shopping cart yet!");
                                                                System.out.println("Taking you back to the product list");
                                                            } else {
                                                                for (String text : cart) {
                                                                    String[] textArray = text.split(",");
                                                                    System.out.printf("Product number: %s \t Product Name: %s \t " +
                                                                                    "Quantity available: %s \t Price: %s\n",
                                                                            textArray[0], textArray[1], textArray[4], textArray[5]);
                                                                }
                                                                String choice = "0";
                                                                do {
                                                                    System.out.println("1 = Buy cart");
                                                                    System.out.println("2 = Delete a product");
                                                                    choice = scanner.nextLine();
                                                                    if (choice.equalsIgnoreCase("1")) {
                                                                        customer.checkout(customer);
                                                                        System.out.println("Cart successfully purchased");
                                                                    } else if (choice.equalsIgnoreCase("2")) {
                                                                        boolean flag = false;
                                                                        do {
                                                                            System.out.println("Enter the product number of the product you want to delete");
                                                                            String number = scanner.nextLine();
                                                                            ArrayList<String> currentCart = customer.readCurrentShoppingCart(customer);
                                                                            int index = -1;

                                                                            for (String product : currentCart) {
                                                                                index++;
                                                                                String[] productArray = product.split(",");
                                                                                if (Integer.parseInt(productArray[0]) == Integer.parseInt(number)) {
                                                                                    currentCart.remove(index);
                                                                                    flag = true;
                                                                                    break;
                                                                                }
                                                                            }
                                                                            if (!flag) {
                                                                                System.out.println("Incorrect product number!");
                                                                                System.out.println("Please enter the number again");
                                                                                continue;
                                                                            }
                                                                            customer.writeCurrentShoppingCart(currentCart, customer);

                                                                        } while (!flag);
                                                                    } else {
                                                                        System.out.println("Invalid input!");
                                                                    }
                                                                } while (choice.equalsIgnoreCase("1") && choice.equalsIgnoreCase("2"));
                                                            }
                                                            continue;
                                                        } else if (sort.equalsIgnoreCase("5")) {
                                                            ArrayList<String> history = customer.viewHistory();
                                                            if (history.isEmpty()) {
                                                                System.out.println("No products bought yet!");
                                                            } else {
                                                                for (String product : history) {
                                                                    String[] productArray = product.split(",");
                                                                    System.out.printf("Product name: %s \t Price: %s \t Store name: %s\n"
                                                                            , productArray[1], productArray[5], productArray[2]);
                                                                }
                                                                System.out.println("Taking you back to product listing page!");
                                                                continue;
                                                            }
                                                        } else if (sort.equalsIgnoreCase("6")) {
                                                            System.out.println("Thank you for using the Online Marketplace!");
                                                            return;
                                                        } else {
                                                            System.out.println("Incorect input!");
                                                        }
                                                    } while (!sort.equals("1") && !sort.equals("2") && !sort.equals("3") &&
                                                            !sort.equals("4") && !sort.equals("5") && !sort.equals("6"));
                                                    System.out.println("Are you interested in buying any of these products? (Yes or No)");
                                                    answer = scanner.nextLine();
                                                    if (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
                                                        System.out.println("Incorrect input!");
                                                        continue;
                                                    }
                                                    if (answer.equalsIgnoreCase("Yes")) {
                                                        do {
                                                            String search = "0";
                                                            String searchTerm = "";
                                                            do {
                                                                System.out.println("1 = search for product");
                                                                System.out.println("2 = continue without searching");
                                                                search = scanner.nextLine();

                                                            } while (search.equalsIgnoreCase("1") && search.equalsIgnoreCase("2"));

                                                            if (search.equalsIgnoreCase("1")) {
                                                                ArrayList<String> searchList;
                                                                do {
                                                                    System.out.println("Enter the search term");
                                                                    searchTerm = scanner.nextLine();
                                                                    searchList = customer.searchUsingTerms(searchTerm);
                                                                    if (searchList.isEmpty()) {
                                                                        System.out.println("No search results!");
                                                                        String again = "";
                                                                        do {
                                                                            System.out.println("Do you want to search again (Yes/No)");
                                                                            again = scanner.nextLine();
                                                                            if (again.equalsIgnoreCase("yes")) {
                                                                                continue;
                                                                            } else if (again.equalsIgnoreCase("no")) {
                                                                                break;
                                                                            } else {
                                                                                System.out.println("Incorrect input!");
                                                                            }
                                                                        } while (!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("no"));
                                                                        if (again.equalsIgnoreCase("no")) {
                                                                            break;
                                                                        }
                                                                    }
                                                                    for (String text : searchList) {
                                                                        String[] product = text.split(",");
                                                                        System.out.printf("Product Number: %s \t Product Name:%s \t Store Name: %s \t Price: %s\n",
                                                                                product[0], product[1], product[2], product[5]);
                                                                    }
                                                                } while (searchList.isEmpty());
                                                            }
                                                            System.out.println("Enter the product number of the product you would like to " +
                                                                    "see the description of.");
                                                            String productNumber = scanner.nextLine();
                                                            info = customer.viewProductInfo(Integer.parseInt(productNumber));
                                                            if (info.equals("")) {
                                                                System.out.println("Product number does not exist.");
                                                            } else {
                                                                System.out.println(customer.viewProductInfo(Integer.parseInt(productNumber)));
                                                                System.out.println("Would you like to:");
                                                                System.out.println("1 = Buy product");
                                                                System.out.println("2 = Add to shopping cart");
                                                                System.out.println("3 = Return to list of products");
                                                                System.out.println("4 = View shopping cart");
                                                                String response = scanner.nextLine();
                                                                if (response.equals("1")) {
                                                                    String quantity = "-1";
                                                                    int tryBuy;
                                                                    do {
                                                                        do {
                                                                            System.out.println("How many of this product do you want to buy?");
                                                                            try {
                                                                                quantity = scanner.nextLine();
                                                                            } catch (Exception e) {
                                                                                System.out.println("Invalid input! Try again.");
                                                                            }
                                                                        } while (Integer.parseInt(quantity) <= 0);
                                                                        if ((tryBuy = customer.buyProduct(Integer.parseInt(productNumber), Integer.parseInt(quantity))) == 0) {
                                                                            System.out.println("Successfully purchased " + quantity + " products!");
                                                                        } else {
                                                                            System.out.println("There are not enough available to purchase. Please enter a lower number.");
                                                                        }
                                                                    } while (tryBuy != 0);

                                                                } else if (response.equalsIgnoreCase("2")) {
                                                                    for (String text : customer.allProducts) {
                                                                        String[] textArray = text.split(",");
                                                                        if (Integer.parseInt(textArray[0]) == Integer.parseInt(productNumber)) {
                                                                            System.out.println(text);
                                                                            customer.addToCart(text, customer);
                                                                        }
                                                                    }
                                                                    System.out.println("Product was successfully added to cart!");

                                                                } else if (response.equals("3")) {
                                                                    continue;
                                                                } else if (response.equals("4")) {
                                                                    ArrayList<String> cart = customer.readCurrentShoppingCart(customer);
                                                                    for (String text : cart) {
                                                                        String[] product = text.split(",");
                                                                        System.out.printf("Product name: %s \t quantity: %s", product[0], product[4]);
                                                                    }
                                                                }
                                                            }
                                                        } while (info.equals(""));
                                                    } else if (answer.equalsIgnoreCase("No")) {
                                                        System.out.println("Thank you for using the Online Marketplace!");
                                                        return;
                                                    } else {
                                                        System.out.println("Invalid input! Try again.");
                                                    }
                                                }
                                            }
                                        } else {
                                                        JOptionPane.showMessageDialog(null, "Invalid input! Please try again.", "Marketplace", JOptionPane.INFORMATION_MESSAGE);

                                                    }
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Incorrect password! Please try again.", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    } while (!line.substring(line.indexOf('_') + 1).equals(password));
                                }
                            }
                            JOptionPane.showMessageDialog(null, "There is no current account with this username. Try again.", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                            if (!realUsername.equals(username)) {
                                break;
                            }
                        }
                    } catch (Exception e) {

                    }
                } while (!username.equals(realUsername));

            } else if (ans == JOptionPane.NO_OPTION) {
                do {
                    System.out.println("Would you like to create an account?");
                    System.out.println("1 = Yes, 2 = No");
                    b = scanner.nextLine();
                    if (b.equals("1")) {
                        do {
                            System.out.println("Please enter your email or username: (must not contain spaces or underscores)");
                            username = scanner.nextLine();
                            if (username.contains(" ") || username.contains("_")) {
                                System.out.println("No spaces or underscores are allowed!");
                            }
                        } while (username.contains(" ") || username.contains("_"));

                        do {
                            do {
                                System.out.println("Please enter a password: (must not contain spaces or underscores)");
                                password = scanner.nextLine();
                                if (password.contains(" ") || password.contains("_")) {
                                    System.out.println("No spaces or underscores are allowed!");
                                }
                            } while (password.contains(" ") || password.contains("_"));

                            System.out.println("Please reenter the password:");
                            password2 = scanner.nextLine();

                            if (!password.equals(password2)) {
                                System.out.println("Passwords do not match! Try again.");
                            }

                        } while (!password.equals(password2));

                        do {
                            System.out.println("Are you looking to be a seller or customer?");
                            System.out.println("1 = Seller, 2 = Customer");
                            sellerOrCustomer = scanner.nextLine();

                            if (!sellerOrCustomer.equals("1") && !sellerOrCustomer.equals("2")) {
                                System.out.println("Invalid input! Please try again.");
                            }
                        } while (!sellerOrCustomer.equals("1") & !sellerOrCustomer.equals("2"));

                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter("accounts.txt", true));
                            BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));

                            bw.write(sellerOrCustomer + " " + username + "_" + password + "\n");

                            bw.close();

                            System.out.println("Account successfully created!");
                            System.out.println("Please run the program again to log in.");

                        } catch (Exception e) {
                            System.out.println("Unable to create account.");
                        }

                    } else if (b.equals("2")) {

                        System.out.println("Thank you for using the Online Marketplace!");
                        return;

                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                } while (!b.equals("1"));

            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!a.equals("1") && !a.equals("2"));
    }

}
