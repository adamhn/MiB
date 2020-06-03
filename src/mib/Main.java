/* 
 * Copyright (C) 2020 by Adam Hassan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Adam Hassan <adamhassan@pm.me>, May 2020
 */
package mib;

import javax.swing.JOptionPane;
import mib.Helpers.Constant;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Application starting point
 * @author Adam Hassan <adamhassan@pm.me>
 */
public class Main {

    private static InfDB db;
    
    public static void main(String[] args) {
        
        connectToDatabase();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame(db).setVisible(true);
            }
        });
    }
    
    /**
     * Handling database connection 
     */
    static private void connectToDatabase(){
        try {
            String currentUser = System.getProperty("user.dir");
            String searchPath = currentUser + ("/dist/MIBDB.FDB");
            
            db = new InfDB(searchPath);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }
}
