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
     * Handling database connection with respect to 
     * different FDB locations locally
     */
    static private void connectToDatabase(){
        try {
            db = new InfDB("C:\\db\\MIBDB.FDB"); // Path for Windows users
        } catch (InfException winPathException) {
            try {
                System.out.println(winPathException.getMessage());
                db = new InfDB("/Documents/db/MIBDB.FDB"); // Path for Mac users
            } catch (InfException exception) {
                JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
                System.out.println(exception.getMessage());
            } 
        }
    }
}
