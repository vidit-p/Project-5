]import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Customer class
 *
 *
 * @author Vidit Patel
 * @version November 14, 2022
 */
public class Customer {
    public ArrayList<String> allProducts = new ArrayList<String>();
    private String email; //the customer's username/ email
    private String password; // the password to customer's account

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Customer(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void writeFile() {
        initialise();
        File f = new File("database.txt");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            for (String line : allProducts) {
                pw.println(line);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //call this function at the beginning of the main method to initialise the static field allProducts
    //CALLING THIS FUNCTION IS CRITICAL FOR PROPER FUNCTIONING
    //just write "<customerObject>.initialise()"; example is given in the test case below
    public void initialise() {
        File f = new File("database.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            while (text != null) {
                allProducts.add(text);
                text = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to display all the products for sale
    public ArrayList<String> viewOverallMarket() {
        initialise();
        File f = new File("database.txt");
        ArrayList<String> productList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String text = bfr.readLine();
            while (text != null) {
                String[] product = text.split(",");
                productList.add(product[0] + "," + product[1] + "," + product[2] + "," + product[5]);
                text = bfr.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }


    //Function to allow user to search for a product using terms
    //The function will return an arraylist containing the lines from the file
    // The lines then can be printed in the main function
    //returns empty arrayList if there are no matches
    //in the main method check if this function returns an empty arraylist, if it does, print an error message
    public ArrayList<String> searchUsingTerms(String searchTerm) {
        initialise();
        ArrayList<String> matchProducts = new ArrayList<String>();
        for (String product : allProducts) {
            if (product.toLowerCase().contains(searchTerm.toLowerCase())) {
                matchProducts.add(product);
            }
        }
        return matchProducts;
    }

    //Function that returns the description of a product
    //based on the productNumber parameter
    //if the product number is incorrect, it returns empty string
    public String viewProductInfo(int productNumber) {
        initialise();
        String out = "";
        for (String product : allProducts) {
            String[] productArray = product.split(",");
            if (Integer.parseInt(productArray[0]) == productNumber) {
                out = String.format("Product description: %s \t Quantity available: %s",
                        productArray[3], productArray[4]);
                return out;
            }
        }
        return out;
    }


    //Processes everything when a customer buys a product, i.e, reduces the available products if applicable
    //parameter quantity is the quantity customer wants to buy
    //returns 0 if there was no error and available quantity was changed successfully
    //returns 1 if quantity > available quantity
    //writes the new available quantity of the product in the customer history file
    public int buyProduct(int productNumber, int quantity) {
        initialise();
        File f = new File("customer history.txt");
        int index = -1;
        String productBought = "";
        boolean flag = false;
        for (String product : allProducts) {
            String[] productArray = product.split(",");
            index++;
            if (Integer.parseInt(productArray[0]) == productNumber) {
                flag = true;
                if (quantity > Integer.parseInt(productArray[4])) {
                    return 1;
                } else {

                    productArray[4] = String.valueOf(Integer.parseInt(productArray[4]) - quantity);
                    productBought = productArray[0] + "," + productArray[1] + "," + productArray[2] + "," +
                            productArray[3] + "," + productArray[4] + "," + productArray[5] + "," + productArray[6];
                    allProducts.set(index, productBought);
                }
            }
        }
        writeFile();
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);
            if (!productBought.equals("")) {
                pw.println(email + "," + productBought);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == false) {
            return 2;
        } else {
            return 0;
        }
    }


    //returns a sorted arraylist of products (the text in the file) by price
    public ArrayList<String> sortByPrice() {
        initialise();
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        ArrayList<String> sortedArrayList = new ArrayList<String>();
        for (String line : allProducts) {
            String[] text = line.split(",");
            Product product = new Product(Integer.parseInt(text[0]), text[1], text[2], text[3],
                    Integer.parseInt(text[4]), Double.parseDouble(text[5]), text[6]);
            productArrayList.add(product);
        }
        productArrayList.sort(Comparator.comparing(Product::getPrice));
        for (Product product : productArrayList) {
            String add = product.getNumber() + "," + product.getName() + "," + product.getStoreName() + ","
                    + product.getDescription() + "," + product.getQuantity() + "," + product.getPrice();
            sortedArrayList.add(add);
        }
        return sortedArrayList;
    }

    //returns a sorted arraylist of products (the text in the file) by quantity
    public ArrayList<String> sortByQuantity() {
        initialise();
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        ArrayList<String> sortedArrayList = new ArrayList<String>();
        for (String line : allProducts) {
            String[] text = line.split(",");
            Product product = new Product(Integer.parseInt(text[0]), text[1], text[2], text[3],
                    Integer.parseInt(text[4]), Double.parseDouble(text[5]), text[6]);
            productArrayList.add(product);
        }
        productArrayList.sort(Comparator.comparing(Product::getQuantity));
        for (Product product : productArrayList) {
            String add = product.getNumber() + "," + product.getName() + "," + product.getStoreName() + ","
                    + product.getDescription() + "," + product.getQuantity() + "," + product.getPrice();
            sortedArrayList.add(add);
        }
        return sortedArrayList;
    }

    //returns an arrayList containing the products customer has bought till now
    public ArrayList<String> viewHistory() {
        initialise();
        ArrayList<String> history = new ArrayList<String>();
        try {
            File f = new File("customer history.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String text = br.readLine();
            while (text != null) {
                String[] textArray = text.split(";");
                if (email.equals(textArray[0])) {
                    history.add(text);
                }
                text = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return history;
    }


    public ArrayList<String> readCurrentShoppingCart(Customer customer) {
        initialise();
        ArrayList<String> shoppingCart = new ArrayList<String>();
        try {
            File file = new File(customer.getEmail() + "ShoppingCart.txt");
            if (!file.exists()) {
                boolean x = file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine()) != null) {
                shoppingCart.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shoppingCart;
    }

    public void writeCurrentShoppingCart(ArrayList<String> shoppingCart, Customer customer) {
        initialise();
        try {
            File file = new File(customer.getEmail() + "ShoppingCart.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < shoppingCart.size(); i++) {
                writer.write(shoppingCart.get(i) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToCart(String product, Customer customer) {
        initialise();
        ArrayList<String> shoppingCart = new ArrayList<String>();
        shoppingCart = readCurrentShoppingCart(customer);
        shoppingCart.add(product);
        writeCurrentShoppingCart(shoppingCart, customer);
    }

    public void clearCart(Customer customer) throws IOException {
        initialise();
        String file = customer.getEmail() + "ShoppingCart.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.close();
    }

    public void checkout(Customer customer) {
        initialise();
        ArrayList<String> checkoutCart = readCurrentShoppingCart(customer);
        for (int i = 0; i < checkoutCart.size(); i++) {
            String splitMe = checkoutCart.get(i);
            String[] parameters = splitMe.split(",");
            int productNumber = Integer.parseInt(parameters[0]);
            buyProduct(productNumber, 1);
        }
        try {
            clearCart(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Customer customer = new Customer("xyz@purdue.edu", "hello123");
        Customer customer1 = new Customer("abc@purdue.edu", "hi123");
        customer1.initialise();
        customer.initialise();


        /*
        // test case to see if the function viewOverallMarket is working as expected or not
        customer.viewOverallMarket();

        //test case to see if the searchUsingTerm is working properly or not
        ArrayList<String> search = customer.searchUsingTerms("hi");
        for (String line : search) {
            System.out.println(line);
        }

        System.out.println(customer.viewProductInfo(2762));

        System.out.println(customer.buyProduct(1626, 1));

        //test case to check functionality of sortByPrice
        ArrayList<String> sortedList = customer.sortByPrice();
        for (String line : sortedList) {
            System.out.println(line);
        }

        //test case to check the functionality of sortByQuantity
        ArrayList<String> sortedList = customer.sortByQuantity();
        for (String line : sortedList) {
            System.out.println(line);
        }

        //test case to see if viewHistory is working properly or not
        System.out.println(customer.buyProduct(2762, 1));
        ArrayList<String> history = customer1.viewHistory();
        for (String line : history) {
            System.out.println(line);
        }*/
        ArrayList<String> sortedList = customer.sortByPrice();
        for (String line : sortedList) {
            System.out.println(line);
        }


    }

}
