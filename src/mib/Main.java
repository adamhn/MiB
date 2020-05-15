package mib;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

public class Main {

    private static InfDB db;
    
    public static void main(String[] args) {
        
        // Setup connection to database
        connectDatabase();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame(db).setVisible(true); // Make Login JFrame visible
            }
        });
    }
    
    static private void connectDatabase(){
        try {
            db = new InfDB("C:\\db\\MIBDB.FDB");
        } catch (InfException exception) {
            System.out.println("Error occurd connecting to database.");
        }
    }
}
