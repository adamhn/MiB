/*
 * Copyright (C) 2020 by Adam Hassan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Adam Hassan <adamhassan@pm.me>, May 2020
 */
package mib.SubFrames;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import mib.Helpers.Constant;
import mib.Helpers.Validate;
import oru.inf.InfDB;
import oru.inf.InfException;

//JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
//System.out.println(exception.getMessage());

/**
 *
 * @author Adam Hassan <adamhassan@pm.me>
 */
public class ChangeAgentFrame extends javax.swing.JFrame {

    private static InfDB db;
    
    /**
     * Creates new form ChangeAgentFrame
     */
    public ChangeAgentFrame(InfDB db) {
        initComponents();
        
        this.db = db;
        
        this.setTitle("MiB - Ändra Agent");
    }
    
    /**
     * Fills combo box with selectable agents to choose from
     * all agents with matching text string appears in combo box
     * @param searchName input search string
     */
    @SuppressWarnings("unchecked")
    private void setComboBox(String searchName){
        try {
            ArrayList<HashMap<String,String>> agent = db.fetchRows("SELECT * FROM AGENT WHERE NAMN = '" + searchName + "'");
            DefaultComboBoxModel searchedAgentToComboBox = new DefaultComboBoxModel();
            
            if (agent == null){
                searchedAgentToComboBox.addElement("---");
            } else {
                for(HashMap listAgent : agent){
                    String everyAgent = "AgentID: " + listAgent.get("AGENT_ID") + " | Telefon: " +  listAgent.get("TELEFON") + " | Område: " +  db.fetchSingle("SELECT BENAMNING FROM OMRADE WHERE OMRADES_ID = " + listAgent.get("OMRADE"));
                    searchedAgentToComboBox.addElement(everyAgent);
                }
            }
            chooseAgentComboBox.setModel(searchedAgentToComboBox);
        }
        catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }
    
    /**
     * Fills checkbox with areas from database
     */
    @SuppressWarnings("unchecked")
    private void setAreaComboBox(String omradesBenamning){
        DefaultComboBoxModel areaBox = new DefaultComboBoxModel();
        
        try{
            ArrayList<String> areas = db.fetchColumn("SELECT BENAMNING FROM OMRADE");
            for(String area : areas ){
                areaBox.addElement(area);
            }
        
            areaComboBox.setModel(areaBox);
            areaComboBox.setSelectedItem(omradesBenamning);
        } catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
        
    }   
    
    /**
     * Resets all text fields and combo boxes/check boxes to default state
     */
    @SuppressWarnings("unchecked")
    private void reset(){
        DefaultComboBoxModel emptyBox = new DefaultComboBoxModel();
        emptyBox.addElement("-");
        agentIDTextField.setText("");
        nameTextField.setText("");
        passwordTextField.setText("");
        phoneTextField.setText("");
        employmentDateTextField.setText("");
        adminCheckBox.setSelected(false);
        areaComboBox.setModel(emptyBox);
    }
    
    
    /**
     * Deletes an agent with specified agentID from database
     * Removes all information related to that AgentID
     * @param agentID 
     */
    @SuppressWarnings("unchecked")
    private void deleteAgent(int agentID){
        
        try{
            String testSträng = db.fetchSingle("SELECT NAMN FROM ALIEN WHERE ANSVARIG_AGENT = " + agentID);
            if (testSträng != null){
                JOptionPane.showMessageDialog(null, "Agenten är ansvarig för en eller flera alien och kan därför inte tas bort!");
            }
            else{
                db.delete("DELETE FROM INNEHAR_UTRUSTNING WHERE AGENT_ID = " + agentID);
                db.delete("DELETE FROM INNEHAR_FORDON WHERE AGENT_ID = " + agentID);
                db.delete("DELETE FROM FALTAGENT WHERE AGENT_ID = " + agentID);
                db.delete("DELETE FROM KONTORSCHEF WHERE AGENT_ID = " + agentID);
                db.delete("DELETE FROM OMRADESCHEF WHERE AGENT_ID = " + agentID);            
                db.delete("DELETE FROM AGENT WHERE AGENT_ID = " + agentID);
            }
            
            JOptionPane.showMessageDialog(null, "Agenten är borttagen.");
        }
        catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seperator1 = new javax.swing.JSeparator();
        mainTitleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchNameTextField = new javax.swing.JTextField();
        searchAgent = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        chooseAgentComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        agentIDTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        employmentDateTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        areaComboBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        passwordTextField = new javax.swing.JTextField();
        adminCheckBox = new javax.swing.JCheckBox();
        updateAlien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        seperator1.setAlignmentX(0.0F);
        seperator1.setAlignmentY(0.0F);

        mainTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        mainTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainTitleLabel.setText("MiB - Ändra Agent");
        mainTitleLabel.setAlignmentY(0.0F);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setText("Ange Namn");

        searchNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNameTextFieldActionPerformed(evt);
            }
        });

        searchAgent.setText("Sök Agent");
        searchAgent.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        searchAgent.setContentAreaFilled(false);
        searchAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAgentActionPerformed(evt);
            }
        });

        jLabel2.setText("Välj Agent");

        chooseAgentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        chooseAgentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseAgentComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Steg 1: Sök Agent");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchNameTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chooseAgentComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchNameTextField)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseAgentComboBox))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Steg 2: Uppdatera Information");

        jLabel4.setText("Namn:");

        jLabel5.setText("Agent ID:");

        jLabel6.setText("Lösenord:");

        jLabel7.setText("Telefon:");

        jLabel8.setText("Anställningsdatum:");

        jLabel10.setText("Område:");

        areaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        adminCheckBox.setText("Administratör");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agentIDTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(areaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(employmentDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(adminCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameTextField)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(agentIDTextField)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(phoneTextField)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(areaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employmentDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(adminCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        updateAlien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        updateAlien.setText("Uppdatera Agent");
        updateAlien.setContentAreaFilled(false);
        updateAlien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAlienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))))
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(updateAlien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateAlien)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(440, 607));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAgentActionPerformed
        searchNameTextFieldActionPerformed(evt);
    }//GEN-LAST:event_searchAgentActionPerformed

    /**
     * This method fills out all information about chosen agent
     * in all text fields and combo boxes/check boxes
     * @param evt 
     */
    @SuppressWarnings("unchecked") 
    private void chooseAgentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseAgentComboBoxActionPerformed
        
        if (chooseAgentComboBox.getSelectedItem().equals("-")){
            reset();
        } else {
            try {
                String[] agentSearch = chooseAgentComboBox.getSelectedItem().toString().split(" ");
                int agentID = Integer.parseInt(agentSearch[1]);

                HashMap<String,String> selectedAgent = db.fetchRow("SELECT * FROM AGENT WHERE AGENT_ID = " + agentID);

                String area = db.fetchSingle("SELECT BENAMNING FROM OMRADE WHERE OMRADES_ID = " + selectedAgent.get("OMRADE"));

                setAreaComboBox(area);

                agentIDTextField.setText(selectedAgent.get("AGENT_ID"));
                nameTextField.setText(selectedAgent.get("NAMN"));
                passwordTextField.setText(selectedAgent.get("LOSENORD"));
                phoneTextField.setText(selectedAgent.get("TELEFON"));
                employmentDateTextField.setText(selectedAgent.get("ANSTALLNINGSDATUM"));
                
                if(new String(db.fetchSingle("SELECT ADMINISTRATOR FROM AGENT WHERE AGENT_ID =" + agentID)).equals("J")){
                    adminCheckBox.setSelected(true);
                } else {
                    adminCheckBox.setSelected(false);
                }
                
            } catch(InfException exception){
                JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
                System.out.println(exception.getMessage());
            }
        }
    }//GEN-LAST:event_chooseAgentComboBoxActionPerformed

    private void searchNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNameTextFieldActionPerformed
        setComboBox(searchNameTextField.getText().toString());
    }//GEN-LAST:event_searchNameTextFieldActionPerformed

    /**
     * Saves updated information in changed fields/combo boxes by user input on an Agent.
     * 1. deletes all data related to that specific agent
     * 2. adds the new agent on same ID
     * that happens upon user pressing update button.
     * verifications on all user input
     * @param evt 
     */
    private void updateAlienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAlienActionPerformed
        if (Validate.isIDFilled(agentIDTextField, chooseAgentComboBox) && Validate.checkDateFormat(employmentDateTextField) &&
            Validate.isTextEmpty(nameTextField) && Validate.isTextEmpty(passwordTextField) &&
            Validate.isTextEmpty(phoneTextField) && Validate.isTextEmpty(employmentDateTextField) &&
            Validate.isPhoneLengthCorrect(phoneTextField) && Validate.isNameLengthCorrect(nameTextField) &&
            Validate.isNormalPasswordLengthCorrect(passwordTextField))
            {

            String[] agentSearch = chooseAgentComboBox.getSelectedItem().toString().split(" ");
            int agentID = Integer.parseInt(agentSearch[1]);

            String adminStatus = "J";
            
            if(adminCheckBox.getSelectedObjects() == null) {
                adminStatus = "N";
            }
            
            try {
                String area = db.fetchSingle("SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING = '" + areaComboBox.getSelectedItem().toString() + "'");
                
                deleteAgent(agentID);
                db.insert("INSERT INTO AGENT VALUES ( " + agentID + " , '" + nameTextField.getText().toString() + "' , '" + phoneTextField.getText().toString() + "' , '" + employmentDateTextField.getText().toString() + "' , '" + adminStatus + "' , '" + passwordTextField.getText().toString() + "' , " + area + ")");
                
                JOptionPane.showMessageDialog(null, "Ändringarna sparade!");
                ChangeAgentFrame.this.dispose();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
                System.out.println(exception.getMessage());
            }
        }
    }//GEN-LAST:event_updateAlienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChangeAgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeAgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeAgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeAgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeAgentFrame(db).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox adminCheckBox;
    private javax.swing.JTextField agentIDTextField;
    private javax.swing.JComboBox<String> areaComboBox;
    private javax.swing.JComboBox<String> chooseAgentComboBox;
    private javax.swing.JTextField employmentDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JButton searchAgent;
    private javax.swing.JTextField searchNameTextField;
    private javax.swing.JSeparator seperator1;
    private javax.swing.JButton updateAlien;
    // End of variables declaration//GEN-END:variables
}
