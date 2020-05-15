package mib.Helpers;



import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validate {
    
    // static method (classmethod) to controll textfield
    static public boolean isTextEmpty(JTextField textField) {
        
        if (textField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Text field is empty.");
            textField.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
