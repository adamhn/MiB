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
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author Adam Hassan <adamhassan@pm.me>
 */
public class ChangeAlienFrame extends javax.swing.JFrame {

    private static InfDB db;
    
    /**
     * Creates new form ChangeAlienFrame
     */
    public ChangeAlienFrame(InfDB db) {
        initComponents();
        
        ChangeAlienFrame.db = db;
        
        // Rquired initializers upon the beginning of ChangeAlienFrame life cycle.
        setupInitialData();
    }
    
    private void setupInitialData() {
        this.setTitle("MiB - Ändra Alien");
    }
    
    /**
     * Used to reset all fields and drop downs to default state
     */
    @SuppressWarnings("unchecked") 
    private void resetFields() {
        DefaultComboBoxModel emptyDropdown = new DefaultComboBoxModel();
        
        emptyDropdown.addElement("-");
        updateNameTextField.setText("");
        updateAlienIDTextField.setText("");
        updatePasswordTextField.setText("");
        updatePhoneTextField.setText("");
        updateDateTextField.setText("");
        updateExtraRaceLabel.setVisible(false);
        updateExtraRaceTextField.setVisible(false);
        updateAreaComboBox.setModel(emptyDropdown);
        updatePlaceComboBox.setModel(emptyDropdown);
        updateRaceComboBox.setModel(emptyDropdown);
        updateResponsibleAgentComboBox.setModel(emptyDropdown);
    }
 
    /**
     * Fills out race combo box with information about which races the alien belongs to
     * And to which race the alien can change to
     * If 'armar' or 'boogies' is relevant to that race then that field gets set to visible
     * @param race
     * @param alienID 
     */
    @SuppressWarnings("unchecked")
    private void setRaceComboBox(String race, int alienID){
        DefaultComboBoxModel raceComboBox = new DefaultComboBoxModel();
        raceComboBox.addElement("Worm");
        raceComboBox.addElement("Boglodite");
        raceComboBox.addElement("Squid");
        updateRaceComboBox.setModel(raceComboBox);
        updateRaceComboBox.setSelectedItem(race);
        
        try {
            switch (race) {
                case "Boglodite":
                    updateExtraRaceLabel.setText("Antal boogies:");
                    updateExtraRaceLabel.setVisible(true);
                    updateExtraRaceTextField.setText(db.fetchSingle("SELECT ANTAL_BOOGIES FROM BOGLODITE WHERE ALIEN_ID =" + alienID));
                    updateExtraRaceTextField.setVisible(true);
                    break;
                case "Squid":
                    updateExtraRaceLabel.setText("Antal armar:");
                    updateExtraRaceLabel.setVisible(true);
                    updateExtraRaceTextField.setText(db.fetchSingle("SELECT ANTAL_ARMAR FROM SQUID WHERE ALIEN_ID =" + alienID));
                    updateExtraRaceTextField.setVisible(true);
                    break;
                default: 
                    updateExtraRaceLabel.setVisible(false);
                    updateExtraRaceTextField.setVisible(false);
                    break;
            }
        } catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }
    
    /**
     * Fills area and place combo boxes And sets current area & place as chosen 
     * @param placeName
     * @param locatedIn 
     * @param areaName
     */
    @SuppressWarnings("unchecked")
    private void setAreaAndPlaceComboBox(String placeName, int locatedIn, String areaName){
        DefaultComboBoxModel areaComboBox = new DefaultComboBoxModel();
        DefaultComboBoxModel placeBomboBox = new DefaultComboBoxModel();
        
        try {
            // Sets area
            ArrayList<String> omraden = db.fetchColumn("SELECT BENAMNING FROM OMRADE");
            for(String omradet : omraden ){
                areaComboBox.addElement(omradet);
            }

            updateAreaComboBox.setModel(areaComboBox);
            updateAreaComboBox.setSelectedItem(areaName);
            
            // Sets place
            ArrayList<String> platser = db.fetchColumn("SELECT BENAMNING FROM PLATS WHERE FINNS_I = " + locatedIn);
            for(String platsen : platser ){
                 placeBomboBox.addElement(platsen);
            }

            updatePlaceComboBox.setModel(placeBomboBox);
            updatePlaceComboBox.setSelectedItem(placeName);
        } catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
        
    }
        
    /**
     * Fills out agent combo box for chosen area
     * It searches through witch agents are located in specified area
     * @param alienID
     * @param locatedIn 
     */
    @SuppressWarnings("unchecked")  
    private void setResponsibleAgent(int alienID, int locatedIn){
        DefaultComboBoxModel agentComboBox = new DefaultComboBoxModel();
        
        try{
            String agentName = db.fetchSingle("SELECT NAMN FROM AGENT WHERE AGENT_ID = (SELECT ANSVARIG_AGENT FROM ALIEN WHERE ALIEN_ID = " + alienID + ")");
            ArrayList<String> agentInArea = db.fetchColumn("SELECT NAMN FROM AGENT WHERE OMRADE = " + locatedIn);
            for(String agent : agentInArea ){
                agentComboBox.addElement(agent);
            }
            
            updateResponsibleAgentComboBox.setModel(agentComboBox);
            updateResponsibleAgentComboBox.setSelectedItem(agentName);
        } catch(InfException exception){
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

        mainTitleLabel = new javax.swing.JLabel();
        seperator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchNameTextField = new javax.swing.JTextField();
        searchAliens = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        chooseAlienComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        updateNameTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        updateAlienIDTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        updatePasswordTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        updatePhoneTextField = new javax.swing.JTextField();
        updateDateTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        updateAreaComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        updatePlaceComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        updateRaceComboBox = new javax.swing.JComboBox<>();
        updateExtraRaceLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        updateResponsibleAgentComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        updateExtraRaceTextField = new javax.swing.JTextField();
        updateAlien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainTitleLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        mainTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainTitleLabel.setText("MiB - Ändra Alien");
        mainTitleLabel.setAlignmentY(0.0F);

        seperator1.setAlignmentX(0.0F);
        seperator1.setAlignmentY(0.0F);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setText("Ange Namn");

        searchAliens.setText("Sök Alien");
        searchAliens.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        searchAliens.setContentAreaFilled(false);
        searchAliens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAliensActionPerformed(evt);
            }
        });

        jLabel2.setText("Välj Alien");

        chooseAlienComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        chooseAlienComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseAlienComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Steg 1: Sök Alien");

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
                                .addComponent(searchAliens, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chooseAlienComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addComponent(searchAliens, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseAlienComboBox))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Steg 2: Uppdatera Information");

        jLabel4.setText("Namn:");

        jLabel5.setText("Alien ID:");

        jLabel6.setText("Lösenord:");

        jLabel7.setText("Telefon:");

        jLabel8.setText("Registreringsdatum:");

        jLabel10.setText("Område:");

        updateAreaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel11.setText("Plats:");

        updatePlaceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel12.setText("Ras:");

        updateRaceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        updateExtraRaceLabel.setText("Antal boogies:");

        updateResponsibleAgentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        jLabel14.setText("Ansvarig Agent:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateNameTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateAlienIDTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updatePasswordTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updatePhoneTextField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateAreaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updatePlaceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(updateExtraRaceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateRaceComboBox, 0, 221, Short.MAX_VALUE)
                            .addComponent(updateExtraRaceTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateDateTextField)
                            .addComponent(updateResponsibleAgentComboBox, 0, 221, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateNameTextField)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateAlienIDTextField)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updatePasswordTextField)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updatePhoneTextField)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateAreaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatePlaceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateResponsibleAgentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateRaceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateExtraRaceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateExtraRaceTextField))
                .addGap(30, 30, 30))
        );

        updateAlien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        updateAlien.setText("Uppdatera Alien");
        updateAlien.setContentAreaFilled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateAlien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(mainTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateAlien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        setSize(new java.awt.Dimension(465, 777));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Searches for an alien with specified name from text field 
     * And sets alien information in choose alien combo box
     * @param evt 
     */
    @SuppressWarnings("unchecked")
    private void searchAliensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAliensActionPerformed

        try {
            ArrayList<HashMap<String,String>> aliens = db.fetchRows("SELECT * FROM ALIEN WHERE NAMN = '" + searchNameTextField.getText() + "'");
            DefaultComboBoxModel searchedAliensToComboBox = new DefaultComboBoxModel();
            
            if (aliens == null){
                searchedAliensToComboBox.addElement("-");
            } else {
                for(HashMap alien : aliens){
                    String race = "Boglodite";
                    String raceIdentifier = db.fetchSingle("SELECT ANTAL_BOOGIES FROM BOGLODITE WHERE ALIEN_ID = " + alien.get("ALIEN_ID"));
                    if(raceIdentifier == null){
                        race = "Squid";
                        raceIdentifier = db.fetchSingle("SELECT ANTAL_ARMAR FROM SQUID WHERE ALIEN_ID = " + alien.get("ALIEN_ID"));
                        if (raceIdentifier == null){
                            race = "Worm";
                        }
                    }

                    String everyAlien = "AlienID: " + alien.get("ALIEN_ID") + " | Plats: " +  db.fetchSingle("SELECT BENAMNING FROM PLATS WHERE PLATS_ID = " + alien.get("PLATS")) + " | Ras: " + race;
                    if (race.equals("Boglodite")){
                        everyAlien += " | Antal boogies: " + raceIdentifier;
                    }
                    else if(race.equals("Squid")){
                        everyAlien += " | Antal armar: " + raceIdentifier;            
                    }
                    searchedAliensToComboBox.addElement(everyAlien);
                }
            }
            chooseAlienComboBox.setModel(searchedAliensToComboBox);
        } catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_searchAliensActionPerformed

    /**
     * Fill all text fields and combo boxen upon choosing an Alien from Alien Combo Box
     * @param evt 
     */
    @SuppressWarnings("unchecked")
    private void chooseAlienComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseAlienComboBoxActionPerformed
        if (chooseAlienComboBox.getSelectedItem().equals("-")) {
            resetFields();
            return;
        }
        
        try {
            String[] alienSearch = chooseAlienComboBox.getSelectedItem().toString().split(" ");
            int alienID = Integer.parseInt(alienSearch[1]);
            String race = alienSearch[7];
            
            HashMap<String,String> chosenAlien = db.fetchRow("SELECT * FROM ALIEN WHERE ALIEN_ID = " + alienID);
            setRaceComboBox(race, alienID);
            
            String place = db.fetchSingle("SELECT BENAMNING FROM PLATS WHERE PLATS_ID = " + chosenAlien.get("PLATS"));
            String area = db.fetchSingle("SELECT BENAMNING FROM OMRADE WHERE OMRADES_ID = (SELECT FINNS_I FROM PLATS WHERE PLATS_ID = " + chosenAlien.get("PLATS") + ")");
            int locatedIn = Integer.parseInt(db.fetchSingle("SELECT FINNS_I FROM PLATS WHERE PLATS_ID = " + chosenAlien.get("PLATS")));

            setAreaAndPlaceComboBox(place, locatedIn, area);
            setResponsibleAgent(alienID, locatedIn);
            
            updateAlienIDTextField.setText(chosenAlien.get("ALIEN_ID"));
            updateNameTextField.setText(chosenAlien.get("NAMN"));
            updatePasswordTextField.setText(chosenAlien.get("LOSENORD"));
            updatePhoneTextField.setText(chosenAlien.get("TELEFON"));
            updateDateTextField.setText(chosenAlien.get("REGISTRERINGSDATUM"));
            
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_chooseAlienComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(ChangeAlienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeAlienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeAlienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeAlienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeAlienFrame(db).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> chooseAlienComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel mainTitleLabel;
    private javax.swing.JButton searchAliens;
    private javax.swing.JTextField searchNameTextField;
    private javax.swing.JSeparator seperator1;
    private javax.swing.JButton updateAlien;
    private javax.swing.JTextField updateAlienIDTextField;
    private javax.swing.JComboBox<String> updateAreaComboBox;
    private javax.swing.JTextField updateDateTextField;
    private javax.swing.JLabel updateExtraRaceLabel;
    private javax.swing.JTextField updateExtraRaceTextField;
    private javax.swing.JTextField updateNameTextField;
    private javax.swing.JTextField updatePasswordTextField;
    private javax.swing.JTextField updatePhoneTextField;
    private javax.swing.JComboBox<String> updatePlaceComboBox;
    private javax.swing.JComboBox<String> updateRaceComboBox;
    private javax.swing.JComboBox<String> updateResponsibleAgentComboBox;
    // End of variables declaration//GEN-END:variables
}
