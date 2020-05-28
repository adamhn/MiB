/* 
 * Copyright (C) 2020 by Adam Hassan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Adam Hassan <adamhassan@pm.me>, May 2020
 */
package mib.Helpers;

import javax.swing.JComboBox;
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
    
    /**
     * Checks whether the input length of password is correct or not
     * @param passwordField input password text
     * @return true or false
     */
    static public boolean isPasswordLengthCorrect(JPasswordField passwordField) {
        if (passwordField.getPassword().length <= 6) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, Constant.ERROR_PASSWORD_LENGTH);
            return false;
        }
    }
    
    /**
     * Checks whether the input length of password is correct or not
     * 
     * @param textField input password text
     * @return true or false
     */
    static public boolean isNormalPasswordLengthCorrect(JTextField textField) {
        if (new String(textField.getText()).length() <= 6){
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, Constant.ERROR_PASSWORD_LENGTH);
            textField.requestFocus();
            return false;
        }
    }
    
    
    /**
     * Checks if chosen combo box and a text field is filled/empty or not
     * @param textField
     * @param comboBox
     * @return 
     */
    public static boolean isTextFieldAndComboFilled(JTextField textField, JComboBox comboBox){
        if (textField.getText().isEmpty() || comboBox.getSelectedItem().toString().equals("-")){
            JOptionPane.showMessageDialog(null, "Inget är valt i listan!");
            comboBox.requestFocus();
            return false;
        }
        else{
            return true;
        }
    }
    
        // Kollar formatet på ett textfält för att se om det överensstämmer med ett datum.
    /**
     * Checks the format for current written date, if it matches the format 
     * we want
     * @param dateField
     * @return 
     */
    static public boolean checkDateFormat(JTextField dateField){
        String[] date = dateField.getText().toString().split("-");
        boolean allNumbers = true;
        for (String datumet : date){
            try {
                System.out.println(datumet);
                int testInt = Integer.parseInt(datumet);
            }
            catch( NumberFormatException exception){
                allNumbers = false;
                System.out.println("Inte nummer, " + exception);
            }
        } if (allNumbers && date.length == 3 && date[0].length() == 4 && date[1].length() == 2 && date[2].length() == 2){
            return true;
        } else {
            JOptionPane.showMessageDialog(null, Constant.VALIDATE_DATE_FORMAT);
            dateField.requestFocus();
            return false;
        }
    }
    
    /**
     * Checks the length on text field, so that we don't save a long name
     * @param textField
     * @return 
     */
    static public boolean isNameLengthCorrect(JTextField textField){
        if (new String(textField.getText()).length() <= 20){
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, Constant.VALIDATE_NAME_LENGTH);
            textField.requestFocus();
            return false;
        }
    }
    
    /**
     * Checks whether the phone length is correct or not
     * @param textField
     * @return 
     */
    static public boolean isPhoneLengthCorrect(JTextField textField){
        if (textField.getText().toString().length() <= 30){
            return true;
        } else {
            JOptionPane.showMessageDialog(null, Constant.VALIDATE_PHONE_NUMBER);
            return false;
        }
    }
    
    /**
     * Checks whether the input data is integers or not
     * @param textField
     * @return 
     */
    static public boolean isTextIntegers(JTextField textField){
        try {
            Integer.parseInt(textField.getText());
            return true;
        } catch(NumberFormatException exception){
            JOptionPane.showMessageDialog(null, Constant.VALIDATE_NO_INTEGERS);
            textField.requestFocus();
            return false;
        }
    }
    
    /**
     * Checks wether combo box is empty or not
     * @param comboBox
     * @return 
     */
    static public boolean isComboBoxEmpty(JComboBox comboBox){
        if (comboBox.getSelectedItem().toString().equals("-")){
            JOptionPane.showMessageDialog(null, Constant.VALIDATE_EMPTY_BOX);
            comboBox.requestFocus();
            return false;
        } else{
            return true;
        }
    }
    
    /**
     * checks wether something is chosen in combo box
     * and if there is data in specified text field
     * @param textField
     * @param comboBox
     * @return 
     */
    public static boolean isIDFilled(JTextField textField, JComboBox comboBox){
        if (textField.getText().isEmpty() || comboBox.getSelectedItem().toString().equals("---")){
            JOptionPane.showMessageDialog(null, Constant.VALIDATE_EMPTY_BOX);
            comboBox.requestFocus();
            return false;
        } else{
            return true;
        }
        
    }
}
