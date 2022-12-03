import java.io.*;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Seller class
 *
 * @author Vidit Patel
 * @version November 14, 2022
 */
public class Seller {
    private static int productNumber;
    private String email; //the customer's username/ email
    private String password; // the password to customer's account
    ArrayList<String> allProducts = new ArrayList<String>();

    public Seller(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public synchronized void setEmail(String email) {
        this.email = email;
    }

    public synchronized String getEmail() {
        return email;
    }

    public synchronized void setPassword(String password) {
        this.password = password;
    }

    public synchronized String getPassword() {
        return password;
    }

    public synchronized void viewOverallMarket() {
        File f = new File("database.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String text = bfr.readLine();
            while (text != null) {
                String[] product = text.split(",");
                System.out.printf("Product Number: %s \t Product Name:%s \t Store Name: %s \t Price: %s\n",
                        product[0], product[1], product[2], product[5]);
                text = bfr.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void initialise() {
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



    public synchronized void writeFile() {
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

    public synchronized int addProduct(String storeName, String productName, String sellerName, String description,
                                       int quantity, double price) {
        File f = new File("database.txt");
        ArrayList<Integer> productNumbers = new ArrayList<>();
        for (String line : allProducts) {
            System.out.println(line);
            String[] text = line.split(",");
            int number = Integer.parseInt(text[0]);
            productNumbers.add(number);
        }
        Random random = new Random();
        productNumber = random.nextInt(100000,999999);
        while (productNumbers.contains(productNumber)) {
            productNumber = random.nextInt();
        }
        int return1 = 0;
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);
            ArrayList<String> stores = new ArrayList<>();

            for (String line : allProducts) {
                String[] lineArray = line.split(",");
                if (lineArray[6].equalsIgnoreCase(sellerName)) {
                    if (!lineArray[2].equalsIgnoreCase(storeName)) {
                        return1 = 1;
                    } else {
                        return1 = 0;
                    }
                }
                stores.add(lineArray[2]);
            }
            if (!stores.contains(storeName)) {
                return1 = 2;
            } else if (return1 == 1) {
                return return1;
            }

            String out = productNumber + "," + productName + "," + storeName + ","
                    + description + "," + quantity + "," + price + "," + sellerName;
            pw.println(out);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return1;
    }

    //returns 1 if the product does not belong to seller's store
    //returns 2 if the product number does not exist
    //return 0 if edit is successful
    public synchronized int editProduct(int productNumber, String newName, String newDescription, int newQuantity,
                                        double newPrice) {
        boolean flag = false;
        int index = -1;
        for (String product : allProducts) {
            index++;
            String[] line = product.split(",");
            if (Integer.parseInt(line[0]) == productNumber) {
                flag = true;
                if (!line[6].equalsIgnoreCase(email)) {
                    return 1;
                }
                String edit = productNumber + "," + newName + "," + line[2] + "," + newDescription +
                        "," + newQuantity + "," + newPrice + "," + line[6];
                allProducts.set(index, edit);
            }
        }
        writeFile();
        if (flag == false) {
            return 2;
        } else {
            return 0;
        }
    }

    // returns 1 if the productNumber does not exist
    //return 2 if the product is not in the seller's store
    // return 0 if deletion is successful
    public synchronized int deleteProduct(int productNumber) {
        boolean flag = false;
        int index = -1;
        for (String product : allProducts) {
            index++;
            String[] line = product.split(",");
            if (Integer.parseInt(line[0]) == productNumber) {
                if (!line[6].equalsIgnoreCase(email)) {
                    return 2;
                }
                flag = true;
                break;
            }
        }
        if (flag == true) {
            allProducts.remove(index);
        }
        writeFile();
        if (flag == false) {
            return 1;
        } else {
            return 0;
        }
    }

    public synchronized ArrayList<String> sellerProduct() {
        String username = this.getEmail();
        ArrayList<String> out = new ArrayList<>();

        for (String line : allProducts) {
            String[] lineArray = line.split(",");
            if (lineArray[6].equals(username)) {
                out.add(line);
            }
        }
        return out;
    }

    public synchronized int ViewShoppingCart(String customer) {
        ArrayList<String> sellersCartView = new ArrayList<>();
        File file = new File(customer + "ShoppingCart.txt");
        int numberOfProducts;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                sellersCartView.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        numberOfProducts = sellersCartView.size();
        return numberOfProducts;
    }

    //returns null if the store does not exist
    //else, returns an arraylist containing the store info
    public synchronized ArrayList<String> storeInfo(String storeName) {
        ArrayList<String> info = new ArrayList<>();
        File f = new File("customer history.txt");
        double revenue = 0;
        boolean flag = false;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            while (text != null) {
                String[] textArray = text.split(",");
                if (textArray[3].equalsIgnoreCase(storeName)) {
                    flag = true;
                    info.add(text);
                    revenue += Double.parseDouble(textArray[6]);
                }
                text = br.readLine();
            }
            String out = String.format("Total revenue: %.2f", revenue);
            info.add(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == false) {
            return null;
        } else {
            return info;
        }
    }

    public synchronized int importFile(String file) throws FileNotFoundException {
        File f = new File(file);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            while (text != null) {
                lines.add(text);
                text = br.readLine();
            }
            File f1 = new File("database.txt");
            FileOutputStream fos = new FileOutputStream(f1, true);
            PrintWriter pw = new PrintWriter(fos);
            for (String line : lines) {
                String[] lineArray = line.split(",");
                if (lineArray.length != 7) {
                    return 1;
                }
                pw.println(line);
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        Seller seller = new Seller("pqr@purdue.edu", "hi");
        seller.initialise();

        //seller.addProduct("tommy", "ball", seller.email, "size 5 ball", 20, 50);
        //seller.addProduct("nike", "shoes", seller.getEmail(), "size 9 shoes",30, 60);
        //seller.addProduct("adidas", "shorts", seller.email, "medium size shorts", 15, 30);

        //seller.editProduct(239927061, "shoe", "shoes of size 8", 30, 60);
        //seller.deleteProduct(68397694);

        seller.addProduct("hello", "bat", seller.email, "size 5 bat", 30,20);
        seller.addProduct("hi", "cap", seller.getEmail(), "size 3 cap", 30,20);
        /*ArrayList<String> storeInfo = seller.storeInfo("fbekj");
        if (storeInfo == null) {
            System.out.println("the store does not exist!");
        } else {
            for (String line : storeInfo) {
                System.out.println(line);
            }
        }*/


    }
}