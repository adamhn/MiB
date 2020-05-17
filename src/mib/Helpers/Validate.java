/* 
 * Copyright (C) 2020 by Adam Hassan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Adam Hassan <adamhassan@pm.me>, May 2020
 */
package mib.Helpers;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import mib.Helpers.Constant;

public class Validate {
    
    /**
     * Checks whether a text input from text field is empty or not
     * @param textField an instance of a text field
     * @return true or false wether the text is empty or not
     */
    static public boolean isTextEmpty(JTextField textField) {
        
        if (textField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_EMPTY_TEXT_FIELD);
            textField.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Checks whether two text fields have the exact same input
     * @param passwordFieldq first instance for comparison with second text field
     * @param passwordField2 second instance for comparison with first text field
     * @return true or false whether the text input matches or not
     */
    static public boolean isPasswordMatching(JPasswordField passwordField1, JPasswordField passwordField2) {
        
        if (passwordField2.equals(passwordField2)) {
            return true;
        } else {
            return false;
        }
    }
    
    static public boolean isPasswordLengthCorrect(JPasswordField passwordField) {
        if (passwordField.getPassword().length <= 6) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, Constant.ERROR_PASSWORD_LENGTH);
            return false;
        }
    }
}
