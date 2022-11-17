import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4243);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            
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
            e.printStackTrace();
        }
    }
}
