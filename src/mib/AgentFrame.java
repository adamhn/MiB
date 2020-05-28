/* 
 * Copyright (C) 2020 by Adam Hassan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Adam Hassan <adamhassan@pm.me>, May 2020
 */
package mib;

import mib.Helpers.Constant;
import mib.SubFrames.ResetPasswordFrame;
import mib.SubFrames.AddAlienFrame;
import mib.SubFrames.ChangeAlienFrame;
import mib.SubFrames.DeleteAlienFrame;
import mib.SubFrames.AddAgentFrame;
import mib.SubFrames.AddEquipmentFrame;
import mib.SubFrames.DeleteEquipmentFrame;
import mib.SubFrames.ChangeAgentFrame;
import mib.SubFrames.DeleteAgentFrame;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import oru.inf.InfDB;
import oru.inf.InfException;

public class AgentFrame extends javax.swing.JFrame {

    private static InfDB db;
    private static String agentID;
    private static String loginEntityType;
    private static boolean isAdmin;

    /**
     * Creates new form AgentFrame
     * @param db instance with the appropriate connection to database
     * @param agentID needed to give correct status and data for user with that ID
     * @param loginEntityType which entity (type) is currently logged in
     * @param isAdmin status to determine wether the user id admin or not.
     */
    public AgentFrame(InfDB db, String agentID, String loginEntityType, boolean isAdmin) {
        initComponents();
        AgentFrame.db = db;
        AgentFrame.agentID = agentID;
        AgentFrame.loginEntityType = loginEntityType;
        AgentFrame.isAdmin = isAdmin;

        // Rquired initializers upon the beginning of AgentFrame life cycle.
        setupInitialData();
        setupAdminPermissions(this.isAdmin);
    }

    /**
     * Sets initial data for labels in jFrame
     * Displays admin tag on id labels for users with admin status
     */
    private void setupInitialData() {
        this.setTitle("MiB - Agent");
        
        initWorkplaceChefTextField();
        setAgentsInArea();
        setAreaComboBox(areaComboBox);
        setAreaComboBox(searchedAreaComboBox);
        
        try {
            nameLabel.setText(db.fetchSingle("SELECT NAMN FROM AGENT WHERE AGENT_ID =" + agentID));
            phoneLabel.setText(db.fetchSingle("SELECT TELEFON FROM AGENT WHERE AGENT_ID =" + agentID));
            areaLabel.setText(db.fetchSingle("SELECT BENAMNING FROM OMRADE WHERE OMRADES_ID = (SELECT OMRADE FROM AGENT WHERE AGENT_ID = " + agentID + ")"));
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }
    
    /**
     * Sets area initial drop down list from db
     * Areas are set on multiple drop downs therefore separating in a method
     * @param valdComboBox is the drop down list for outputting the areas
     */
    @SuppressWarnings("unchecked")
    private void setAreaComboBox(JComboBox valdComboBox) {
        try {
            ArrayList<String> areaIDS = db.fetchColumn("SELECT BENAMNING FROM OMRADE");
            DefaultComboBoxModel areas = new DefaultComboBoxModel();
            for (String area : areaIDS) {
                areas.addElement(area);
            }

            valdComboBox.setModel(areas);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }
    
    /**
     * Gets all agents for the purpose of choosing a new work place chef
     */
    @SuppressWarnings("unchecked")
    private void setAgentsInArea(){
        DefaultComboBoxModel agentsInArea = new DefaultComboBoxModel();

        try {
            ArrayList<HashMap<String, String>> agenternaIDatabasen = db.fetchRows("SELECT * FROM AGENT ");
            
            for (HashMap agenterna : agenternaIDatabasen){
                agentsInArea.addElement("AgentID: " + agenterna.get("AGENT_ID") + " | Namn: " + agenterna.get("NAMN"));
            }
            
            chooseChefComboBox.setModel(agentsInArea);
        } catch (InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Hiding different areas/abilities depending on user permissions
     * @param isAdmin whether the current user is an admin or not
     */
    private void setupAdminPermissions(boolean isAdmin) {
        if (!isAdmin) {
            idLabel.setText("Agent ID: " + agentID);
            
            adminTabbedPanel.remove(addChangeDeleteAgent);
            deleteEquipment.setVisible(false);
            deleteAlienButton.setVisible(false);
            setChefPanel.setVisible(false);
            setAreaChefPanel.setVisible(false);
        } else {
            idLabel.setText("Agent: " + agentID + " - Administratör");
        }
    }
        
    /**
     * Gets current work place chef when frame initiates
     */
    @SuppressWarnings("unchecked")
    private void initWorkplaceChefTextField(){

        try {
            String getAgentID = db.fetchSingle("SELECT AGENT_ID FROM KONTORSCHEF");
           
            HashMap<String,String> hämtaOC = db.fetchRow("SELECT * FROM AGENT WHERE AGENT_ID = "+getAgentID);
            currentChefTextField.setText("Namn: "+ hämtaOC.get("NAMN")+" | Agent ID: "+ hämtaOC.get("AGENT_ID") );
        } catch (InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }
    
    /**
     * Called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        titleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelForName = new javax.swing.JLabel();
        labelForPhone = new javax.swing.JLabel();
        labelForArea = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        areaLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        adminTabbedPanel = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        searchPlaceLabel = new javax.swing.JLabel();
        areaComboBox = new javax.swing.JComboBox<>();
        placeComboBox = new javax.swing.JComboBox<>();
        searchAliensInPlaceButton = new javax.swing.JButton();
        seperator1 = new javax.swing.JSeparator();
        searchPlaceLabel1 = new javax.swing.JLabel();
        raceComboBox = new javax.swing.JComboBox<>();
        searchAliensByRaceButton = new javax.swing.JButton();
        fromDateSpinner = new javax.swing.JSpinner();
        seperator2 = new javax.swing.JSeparator();
        fromDateLabel = new javax.swing.JLabel();
        toDateLabel = new javax.swing.JLabel();
        toDateSpinner = new javax.swing.JSpinner();
        searchAliensByDateButton = new javax.swing.JButton();
        searchPlaceLabel2 = new javax.swing.JLabel();
        seperator4 = new javax.swing.JSeparator();
        searchPlaceLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        searchAlienNameTextField = new javax.swing.JTextField();
        searchSpecificButton = new javax.swing.JButton();
        addChangeDeleteAlien = new javax.swing.JPanel();
        addAlienButton = new javax.swing.JButton();
        changeAlienButton = new javax.swing.JButton();
        deleteAlienButton = new javax.swing.JButton();
        addChangeDeleteAgent = new javax.swing.JPanel();
        addAgentButton = new javax.swing.JButton();
        changeAgentButton = new javax.swing.JButton();
        deleteAgentButton = new javax.swing.JButton();
        chefAreaPanel = new javax.swing.JPanel();
        areaChefMainLabel = new javax.swing.JLabel();
        searchedAreaComboBox = new javax.swing.JComboBox<>();
        titleForAreaChefName = new javax.swing.JLabel();
        areaChefNameLabel = new javax.swing.JLabel();
        areaChefLabel = new javax.swing.JLabel();
        titleForAreaChefId = new javax.swing.JLabel();
        areaChefAgentIdLabel = new javax.swing.JLabel();
        areaChefPhoneLabel = new javax.swing.JLabel();
        titleForAreaChefPhone = new javax.swing.JLabel();
        setChefPanel = new javax.swing.JPanel();
        labelForCurrentChef = new javax.swing.JLabel();
        currentChefTextField = new javax.swing.JTextField();
        labelForChooseChefComboBox = new javax.swing.JLabel();
        chooseChefComboBox = new javax.swing.JComboBox<>();
        setChefPlace = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        setAreaChefPanel = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        labelForAreaChef = new javax.swing.JLabel();
        setChefAreaComboBox = new javax.swing.JComboBox<>();
        setNewAreaChef = new javax.swing.JButton();
        equipmentPanel = new javax.swing.JPanel();
        addEquipment = new javax.swing.JButton();
        deleteEquipment = new javax.swing.JButton();
        resultLabel = new javax.swing.JLabel();
        changePassword = new javax.swing.JButton();
        resultScrollPanel = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agent Portal");

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleLabel.setText("MiB - Sektor Skandinavien");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 0, true));

        labelForName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelForName.setForeground(new java.awt.Color(255, 255, 255));
        labelForName.setLabelFor(nameLabel);
        labelForName.setText("Namn: ");

        labelForPhone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelForPhone.setForeground(new java.awt.Color(255, 255, 255));
        labelForPhone.setLabelFor(phoneLabel);
        labelForPhone.setText("Telefon:");

        labelForArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelForArea.setForeground(new java.awt.Color(255, 255, 255));
        labelForArea.setLabelFor(areaLabel);
        labelForArea.setText("Område:");

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameLabel.setText("Agent O");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phoneLabel.setForeground(new java.awt.Color(255, 255, 255));
        phoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        phoneLabel.setText("555-223233");

        areaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        areaLabel.setForeground(new java.awt.Color(255, 255, 255));
        areaLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        areaLabel.setText("Svealand ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelForName, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelForArea, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(areaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelForPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelForName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelForPhone)
                    .addComponent(phoneLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelForArea)
                    .addComponent(areaLabel))
                .addContainerGap())
        );

        idLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        idLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        idLabel.setText("Alien: ");
        idLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        adminTabbedPanel.setToolTipText("");
        adminTabbedPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        searchPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        searchPlaceLabel.setLabelFor(searchAliensInPlaceButton);
        searchPlaceLabel.setText("Hitta aliens på angiven plats:");

        areaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        areaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAreaComboBoxActionPerformed(evt);
            }
        });

        placeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "." }));
        placeComboBox.setEnabled(false);

        searchAliensInPlaceButton.setText("Sök");
        searchAliensInPlaceButton.setContentAreaFilled(false);
        searchAliensInPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAliensInPlaceButtonActionPerformed(evt);
            }
        });

        searchPlaceLabel1.setLabelFor(searchAliensInPlaceButton);
        searchPlaceLabel1.setText("Hitta aliens av en viss ras:");

        raceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boglodite", "Squid", "Worm" }));

        searchAliensByRaceButton.setText("Sök");
        searchAliensByRaceButton.setContentAreaFilled(false);
        searchAliensByRaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAliensByRaceButtonActionPerformed(evt);
            }
        });

        fromDateSpinner.setModel(new javax.swing.SpinnerDateModel());

        fromDateLabel.setLabelFor(fromDateSpinner);
        fromDateLabel.setText("Från:");

        toDateLabel.setLabelFor(toDateSpinner);
        toDateLabel.setText("Till:");

        toDateSpinner.setModel(new javax.swing.SpinnerDateModel());

        searchAliensByDateButton.setText("Sök");
        searchAliensByDateButton.setContentAreaFilled(false);
        searchAliensByDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchAliensByDateButtonActionPerformed(evt);
            }
        });

        searchPlaceLabel2.setLabelFor(searchAliensInPlaceButton);
        searchPlaceLabel2.setText("Hitta aliens mellan en tidsperiod:");

        searchPlaceLabel3.setLabelFor(searchAliensInPlaceButton);
        searchPlaceLabel3.setText("Hitta en specifik alien:");

        jLabel1.setText("Namn:");

        searchAlienNameTextField.setToolTipText("Ange alien namn");

        searchSpecificButton.setText("Sök Alien");
        searchSpecificButton.setContentAreaFilled(false);
        searchSpecificButton.setFocusPainted(false);
        searchSpecificButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSpecificButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(fromDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toDateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchAliensByDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchPlaceLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(searchPanelLayout.createSequentialGroup()
                                        .addComponent(areaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(placeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(searchAliensInPlaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(searchPanelLayout.createSequentialGroup()
                                        .addComponent(raceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(searchAliensByRaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(seperator1)
                            .addComponent(seperator2)
                            .addComponent(seperator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchPlaceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchPlaceLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(searchPlaceLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchAlienNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchSpecificButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPlaceLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchAlienNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchSpecificButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seperator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPlaceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(areaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(placeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchAliensInPlaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(seperator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPlaceLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(raceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchAliensByRaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seperator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPlaceLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromDateSpinner)
                    .addComponent(toDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toDateSpinner)
                    .addComponent(searchAliensByDateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );

        adminTabbedPanel.addTab("Sök", searchPanel);

        addAlienButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addAlienButton.setText("Lägg till Alien");
        addAlienButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        addAlienButton.setContentAreaFilled(false);
        addAlienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAlienButtonActionPerformed(evt);
            }
        });

        changeAlienButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        changeAlienButton.setText("Ändra Alien");
        changeAlienButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        changeAlienButton.setContentAreaFilled(false);
        changeAlienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeAlienButtonActionPerformed(evt);
            }
        });

        deleteAlienButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteAlienButton.setText("Ta bort Alien");
        deleteAlienButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));
        deleteAlienButton.setContentAreaFilled(false);
        deleteAlienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAlienButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addChangeDeleteAlienLayout = new javax.swing.GroupLayout(addChangeDeleteAlien);
        addChangeDeleteAlien.setLayout(addChangeDeleteAlienLayout);
        addChangeDeleteAlienLayout.setHorizontalGroup(
            addChangeDeleteAlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addChangeDeleteAlienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addChangeDeleteAlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addAlienButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changeAlienButton, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(deleteAlienButton, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addContainerGap())
        );
        addChangeDeleteAlienLayout.setVerticalGroup(
            addChangeDeleteAlienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addChangeDeleteAlienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addAlienButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeAlienButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteAlienButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        adminTabbedPanel.addTab("Hantera Aliens", addChangeDeleteAlien);

        addAgentButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addAgentButton.setText("Lägg till Agent");
        addAgentButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        addAgentButton.setContentAreaFilled(false);
        addAgentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAgentButtonActionPerformed(evt);
            }
        });

        changeAgentButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        changeAgentButton.setText("Ändra Agent");
        changeAgentButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        changeAgentButton.setContentAreaFilled(false);
        changeAgentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeAgentButtonActionPerformed(evt);
            }
        });

        deleteAgentButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteAgentButton.setText("Ta bort Agent");
        deleteAgentButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));
        deleteAgentButton.setContentAreaFilled(false);
        deleteAgentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAgentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addChangeDeleteAgentLayout = new javax.swing.GroupLayout(addChangeDeleteAgent);
        addChangeDeleteAgent.setLayout(addChangeDeleteAgentLayout);
        addChangeDeleteAgentLayout.setHorizontalGroup(
            addChangeDeleteAgentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addChangeDeleteAgentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addChangeDeleteAgentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addAgentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changeAgentButton, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(deleteAgentButton, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addContainerGap())
        );
        addChangeDeleteAgentLayout.setVerticalGroup(
            addChangeDeleteAgentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addChangeDeleteAgentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addAgentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeAgentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteAgentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        adminTabbedPanel.addTab("Hantera Agenter", addChangeDeleteAgent);

        areaChefMainLabel.setText("Visa områdeschef för angivit områdeskontor:");

        searchedAreaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchedAreaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchedAreaChefComboBoxActionPerformed(evt);
            }
        });

        titleForAreaChefName.setLabelFor(areaChefNameLabel);
        titleForAreaChefName.setText("Namn:");

        areaChefNameLabel.setText("---");

        areaChefLabel.setLabelFor(searchedAreaComboBox);
        areaChefLabel.setText("Områdeskontor:");

        titleForAreaChefId.setLabelFor(areaChefAgentIdLabel);
        titleForAreaChefId.setText("Agent ID:");

        areaChefAgentIdLabel.setText("---");

        areaChefPhoneLabel.setText("---");

        titleForAreaChefPhone.setLabelFor(areaChefPhoneLabel);
        titleForAreaChefPhone.setText("Telefon");

        labelForCurrentChef.setText("Nuvårande kontorschef:");

        labelForChooseChefComboBox.setText("Välj ny kontorschef:");

        chooseChefComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseChefComboBoxsearchAreaComboBoxActionPerformed(evt);
            }
        });

        setChefPlace.setText("Sätt kontorschef");
        setChefPlace.setContentAreaFilled(false);
        setChefPlace.setFocusPainted(false);
        setChefPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setChefPlacesearchSpecificButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout setChefPanelLayout = new javax.swing.GroupLayout(setChefPanel);
        setChefPanel.setLayout(setChefPanelLayout);
        setChefPanelLayout.setHorizontalGroup(
            setChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setChefPanelLayout.createSequentialGroup()
                .addGroup(setChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelForCurrentChef, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentChefTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(setChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelForChooseChefComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(setChefPanelLayout.createSequentialGroup()
                        .addComponent(chooseChefComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setChefPlace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jSeparator2)
        );
        setChefPanelLayout.setVerticalGroup(
            setChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setChefPanelLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(setChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelForChooseChefComboBox)
                    .addComponent(labelForCurrentChef))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(setChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentChefTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseChefComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setChefPlace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );

        labelForAreaChef.setText("Välj ny områdeschef:");

        setChefAreaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setChefAreaComboBoxsearchAreaComboBoxActionPerformed(evt);
            }
        });

        setNewAreaChef.setText("Sätt ny områdeschef");
        setNewAreaChef.setContentAreaFilled(false);
        setNewAreaChef.setFocusPainted(false);
        setNewAreaChef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setNewAreaChefsearchSpecificButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout setAreaChefPanelLayout = new javax.swing.GroupLayout(setAreaChefPanel);
        setAreaChefPanel.setLayout(setAreaChefPanelLayout);
        setAreaChefPanelLayout.setHorizontalGroup(
            setAreaChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
            .addGroup(setAreaChefPanelLayout.createSequentialGroup()
                .addGroup(setAreaChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelForAreaChef, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(setChefAreaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(setNewAreaChef)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        setAreaChefPanelLayout.setVerticalGroup(
            setAreaChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setAreaChefPanelLayout.createSequentialGroup()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelForAreaChef)
                .addGap(8, 8, 8)
                .addGroup(setAreaChefPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setChefAreaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setNewAreaChef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout chefAreaPanelLayout = new javax.swing.GroupLayout(chefAreaPanel);
        chefAreaPanel.setLayout(chefAreaPanelLayout);
        chefAreaPanelLayout.setHorizontalGroup(
            chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chefAreaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(areaChefMainLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(chefAreaPanelLayout.createSequentialGroup()
                        .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(chefAreaPanelLayout.createSequentialGroup()
                                .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(titleForAreaChefPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(titleForAreaChefId, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                    .addComponent(titleForAreaChefName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(chefAreaPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(areaChefNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(areaChefAgentIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(chefAreaPanelLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(areaChefPhoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(chefAreaPanelLayout.createSequentialGroup()
                                .addComponent(areaChefLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchedAreaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 257, Short.MAX_VALUE))
                    .addComponent(setChefPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(setAreaChefPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        chefAreaPanelLayout.setVerticalGroup(
            chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chefAreaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaChefMainLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(areaChefLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchedAreaComboBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleForAreaChefName)
                    .addComponent(areaChefNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleForAreaChefId)
                    .addComponent(areaChefAgentIdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chefAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleForAreaChefPhone)
                    .addComponent(areaChefPhoneLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(setChefPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setAreaChefPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        adminTabbedPanel.addTab("Chef/Område", chefAreaPanel);

        addEquipment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addEquipment.setText("Lägg till Utrustning");
        addEquipment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        addEquipment.setContentAreaFilled(false);
        addEquipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEquipmentActionPerformed(evt);
            }
        });

        deleteEquipment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteEquipment.setText("Ta bort utrustning");
        deleteEquipment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2));
        deleteEquipment.setContentAreaFilled(false);
        deleteEquipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEquipmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout equipmentPanelLayout = new javax.swing.GroupLayout(equipmentPanel);
        equipmentPanel.setLayout(equipmentPanelLayout);
        equipmentPanelLayout.setHorizontalGroup(
            equipmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(equipmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEquipment, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(deleteEquipment, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addContainerGap())
        );
        equipmentPanelLayout.setVerticalGroup(
            equipmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(equipmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteEquipment, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );

        adminTabbedPanel.addTab("Utrustning", equipmentPanel);

        resultLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        resultLabel.setLabelFor(resultTextArea);
        resultLabel.setText("Resultat");

        changePassword.setText("Byt lösenord");
        changePassword.setContentAreaFilled(false);
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });

        resultScrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        resultScrollPanel.setFocusable(false);
        resultScrollPanel.setOpaque(false);
        resultScrollPanel.setVerifyInputWhenFocusTarget(false);

        resultTextArea.setEditable(false);
        resultTextArea.setColumns(20);
        resultTextArea.setLineWrap(true);
        resultTextArea.setRows(1);
        resultTextArea.setTabSize(1);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setAutoscrolls(false);
        resultTextArea.setHighlighter(null);
        resultTextArea.setMinimumSize(new java.awt.Dimension(207, 405));
        resultTextArea.setVerifyInputWhenFocusTarget(false);
        resultScrollPanel.setViewportView(resultTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(resultScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(adminTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(83, 83, 83)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(adminTabbedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        adminTabbedPanel.getAccessibleContext().setAccessibleName("Sök");

        setSize(new java.awt.Dimension(724, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Searches for an alien that matches input text string
     * Returns an alien as a result with formatted string
     * @param evt 
     */
    @SuppressWarnings("unchecked")
    private void searchSpecificButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSpecificButtonActionPerformed
        String resultString = "";
        try {
            ArrayList<String> aliensList = new ArrayList<String>();
            aliensList = db.fetchColumn("SELECT ALIEN_ID FROM ALIEN WHERE NAMN = \'" + searchAlienNameTextField.getText() + "\'");
            if (aliensList == null) {
                resultString = "Ingen alien hittad!";
            } else if (aliensList.size() == 1) {
                int alienID = Integer.parseInt(db.fetchSingle("SELECT ALIEN_ID FROM ALIEN WHERE NAMN = \'" + searchAlienNameTextField.getText() + "\'"));
                resultString = searchAllAlienInformation(alienID);
            } else {
                resultString = "Var god specificera din sökning.";
                ArrayList<HashMap<String, String>> aliens = db.fetchRows("SELECT * FROM ALIEN WHERE NAMN = \'" + searchAlienNameTextField.getText() + "\'");

                DefaultComboBoxModel aliensForComboBox = new DefaultComboBoxModel();
                for (HashMap alien : aliens) {
                    String race = "";
                    String extra = "";
                    if (db.fetchSingle("SELECT ALIEN_ID FROM BOGLODITE WHERE ALIEN_ID = " + alien.get("ALIEN_ID")) != null) {
                        race = "Boglodite";
                        extra = " | Antal boogies: " + db.fetchSingle("SELECT ANTAL_BOOGIES FROM BOGLODITE WHERE ALIEN_ID = " + alien.get("ALIEN_ID"));
                    } else if (db.fetchSingle("SELECT ALIEN_ID FROM SQUID WHERE ALIEN_ID = " + alien.get("ALIEN_ID")) != null) {
                        race = "Squid";
                        extra = " | Antal armar: " + db.fetchSingle("SELECT ANTAL_ARMAR FROM SQUID WHERE ALIEN_ID = " + alien.get("ALIEN_ID"));
                    } else if (db.fetchSingle("SELECT ALIEN_ID FROM WORM WHERE ALIEN_ID = " + alien.get("ALIEN_ID")) != null) {
                        race = "Worm";
                    }
                    String alienAlternative = "ID: " + alien.get("ALIEN_ID") + " | Ras: " + race + extra + " | Plats: " + db.fetchSingle("SELECT BENAMNING FROM PLATS WHERE PLATS_ID = " + alien.get("PLATS"));
                    aliensForComboBox.addElement(alienAlternative);
                }
            }
            resultTextArea.setText(resultString);
            resultTextArea.setCaretPosition(0);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_searchSpecificButtonActionPerformed

    /**
     * Searches aliens between a time interval
     * Returns result of found aliens within specified date
     * @param evt 
     */
    private void searchAliensByDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAliensByDateButtonActionPerformed
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate = dateFormat.format(fromDateSpinner.getValue());
            String toDate = dateFormat.format(toDateSpinner.getValue());

            String result = "";
            ArrayList<String> alienBetweenDate = db.fetchColumn("SELECT NAMN FROM ALIEN WHERE REGISTRERINGSDATUM BETWEEN \'" + fromDate + "\' AND \'" + toDate + "\'");
            System.out.println("SELECT NAMN FROM ALIEN WHERE REGISTRERINGSDATUM BETWEEN \'" + fromDate + "\' AND \'" + toDate + "\'");
            for (String alien : alienBetweenDate) {
                result += alien + "\n";
            }
            resultTextArea.setText(result);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_searchAliensByDateButtonActionPerformed

    /**
     * Searching aliens with specific race Returning aliens found with chosen
     * race
     *
     * @param evt
     */
    private void searchAliensByRaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAliensByRaceButtonActionPerformed

        try {
            String searchRace = raceComboBox.getSelectedItem().toString().toUpperCase();
            String result = "";
            ArrayList<String> raceAliens = db.fetchColumn("SELECT NAMN FROM ALIEN WHERE ALIEN_ID IN (SELECT ALIEN_ID FROM " + searchRace + ")");

            for (String raceAlien : raceAliens) {
                result += raceAlien + "\n";
            }
            resultTextArea.setText(result);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_searchAliensByRaceButtonActionPerformed

    /**
     * Searching aliens within chosen place from selectable drop down list.
     * Returning all found aliens : or empty message if no aliens is found.
     *
     * @param evt
     */
    private void searchAliensInPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAliensInPlaceButtonActionPerformed
        resultTextArea.setText("");
        String resultText = "";
        String searchPlace = placeComboBox.getSelectedItem().toString();

        ArrayList<String> alienInPlace = new ArrayList<String>();

        try {
            if (searchPlace.equals("ALLA")) {
                searchPlace = areaComboBox.getSelectedItem().toString();
                alienInPlace = db.fetchColumn("SELECT NAMN FROM ALIEN WHERE PLATS IN (SELECT PLATS_ID FROM PLATS WHERE FINNS_I = (SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING = \'" + searchPlace + "\'))");

            } else {
                alienInPlace = db.fetchColumn("SELECT NAMN FROM ALIEN WHERE PLATS = (SELECT PLATS_ID FROM PLATS WHERE BENAMNING = \'" + searchPlace + "\')");
            }

            if (alienInPlace == null || alienInPlace.contains(null)) {
                resultText = "Inga aliens hittade på valda platsen.";
            } else {
                for (String alien : alienInPlace) {
                    resultText += alien + "\n";
                }
            }
            resultTextArea.setText(resultText);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_searchAliensInPlaceButtonActionPerformed

    /**
     * Retrieve chosen area from drop down list and initialize places in place
     * drop down.
     *
     * @param evt
     */
    @SuppressWarnings("unchecked")
    private void searchAreaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchAreaComboBoxActionPerformed
        String area = areaComboBox.getSelectedItem().toString();

        try {
            ArrayList<String> placeInAreaIds = db.fetchColumn("SELECT BENAMNING FROM PLATS WHERE FINNS_I = (SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING = \'" + area + "\')");
            DefaultComboBoxModel areas = new DefaultComboBoxModel();

            for (String areaElement : placeInAreaIds) {
                areas.addElement(areaElement);
            }

            areas.addElement("ALLA");
            placeComboBox.setEnabled(true);
            placeComboBox.setModel(areas);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_searchAreaComboBoxActionPerformed

    /**
     * Retrieve chef data for a specific area chosen from area drop down list.
     * Sets corresponding data to info labels i jFrame.
     *
     * @param evt
     */
    @SuppressWarnings("unchecked")
    private void searchedAreaChefComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchedAreaChefComboBoxActionPerformed
        String searchedArea = searchedAreaComboBox.getSelectedItem().toString();
        DefaultComboBoxModel agentsInArea = new DefaultComboBoxModel();

        try {
            HashMap<String, String> areaChef = db.fetchRow("SELECT * FROM AGENT WHERE AGENT_ID = (SELECT AGENT_ID FROM OMRADESCHEF WHERE OMRADE = (SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING = \'" + searchedArea + "\'))");

            if (areaChef == null) {
                areaChefNameLabel.setText("-");
                areaChefAgentIdLabel.setText("-");
                areaChefPhoneLabel.setText("-");
            } else {
                areaChefNameLabel.setText(areaChef.get("NAMN"));
                areaChefAgentIdLabel.setText(areaChef.get("AGENT_ID"));
                areaChefPhoneLabel.setText(areaChef.get("TELEFON"));
            }
            
            ArrayList<HashMap<String, String>> areaAgents = db.fetchRows("SELECT * FROM AGENT WHERE OMRADE = (SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING = '" + searchedAreaComboBox.getSelectedItem().toString() + "')");
            
            if (areaAgents == null){
                agentsInArea.addElement("-");
            }
            else{
                for (HashMap agenterna : areaAgents){
                    agentsInArea.addElement("AgentID: " + agenterna.get("AGENT_ID") + " | Namn: " + agenterna.get("NAMN"));
                }
            }
            
            setChefAreaComboBox.setModel(agentsInArea);
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_searchedAreaChefComboBoxActionPerformed

    /**
     * Create and display a ResetPassword jFrame upon button click event
     * @param evt 
     */
    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
       new ResetPasswordFrame(db, agentID, loginEntityType).setVisible(true);
    }//GEN-LAST:event_changePasswordActionPerformed

    private void addAlienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAlienButtonActionPerformed
       new AddAlienFrame(db).setVisible(true); 
    }//GEN-LAST:event_addAlienButtonActionPerformed

    private void changeAlienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeAlienButtonActionPerformed
       new ChangeAlienFrame(db).setVisible(true);
    }//GEN-LAST:event_changeAlienButtonActionPerformed

    private void deleteAlienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAlienButtonActionPerformed
        new DeleteAlienFrame(db).setVisible(true);
    }//GEN-LAST:event_deleteAlienButtonActionPerformed

    private void deleteAgentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAgentButtonActionPerformed
        new DeleteAgentFrame(db).setVisible(true);
    }//GEN-LAST:event_deleteAgentButtonActionPerformed

    private void addAgentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAgentButtonActionPerformed
        new AddAgentFrame(db).setVisible(true);
    }//GEN-LAST:event_addAgentButtonActionPerformed

    private void changeAgentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeAgentButtonActionPerformed
        new ChangeAgentFrame(db).setVisible(true);
    }//GEN-LAST:event_changeAgentButtonActionPerformed

    private void chooseChefComboBoxsearchAreaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseChefComboBoxsearchAreaComboBoxActionPerformed
    }//GEN-LAST:event_chooseChefComboBoxsearchAreaComboBoxActionPerformed

    /**
     * Retrieves current work place chef in object form and switches to new work place chef.
     * @param evt 
     */
    private void setChefPlacesearchSpecificButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setChefPlacesearchSpecificButtonActionPerformed
        try {
            String[] agentSearch = chooseChefComboBox.getSelectedItem().toString().split(" ");
            int agentID = Integer.parseInt(agentSearch[1]);
            db.update("UPDATE KONTORSCHEF SET AGENT_ID = " + agentID + " WHERE KONTORSBETECKNING = 'Örebrokontoret'");
            initWorkplaceChefTextField();
        }
        catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_setChefPlacesearchSpecificButtonActionPerformed

    private void setChefAreaComboBoxsearchAreaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setChefAreaComboBoxsearchAreaComboBoxActionPerformed
    }//GEN-LAST:event_setChefAreaComboBoxsearchAreaComboBoxActionPerformed

    /**
     * Sets new area chef depends on which area is chosen in area box
     * and which agents is available within the area
     */
    @SuppressWarnings("unchecked")
    private void setNewAreaChefsearchSpecificButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setNewAreaChefsearchSpecificButtonActionPerformed
        try{
            String[] agentSök = setChefAreaComboBox.getSelectedItem().toString().split(" ");
            int agentID = Integer.parseInt(agentSök[1]);
            
            String getAreasID = db.fetchSingle("SELECT OMRADES_ID FROM OMRADE WHERE BENAMNING = '" + searchedAreaComboBox.getSelectedItem().toString() + "'");
            String query = db.fetchSingle("SELECT AGENT_ID FROM OMRADESCHEF WHERE OMRADE = " + getAreasID);
            if (query == null){
                db.insert("INSERT INTO OMRADESCHEF VALUES ( " + agentID + " , " + getAreasID + ")");
            } else {
                db.update("UPDATE OMRADESCHEF SET AGENT_ID = " + agentID + " WHERE OMRADE = " + getAreasID);
            }
            
            searchedAreaChefComboBoxActionPerformed(evt);
        } catch(InfException exception){
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
    }//GEN-LAST:event_setNewAreaChefsearchSpecificButtonActionPerformed

    private void addEquipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEquipmentActionPerformed
        new AddEquipmentFrame(db).setVisible(true);
    }//GEN-LAST:event_addEquipmentActionPerformed

    private void deleteEquipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEquipmentActionPerformed
       new DeleteEquipmentFrame(db).setVisible(true); 
    }//GEN-LAST:event_deleteEquipmentActionPerformed

    /**
     * Searches information from db for a specific alien
     * @param iD to get the identifier for an alien
     * @return formatted info of alien as string
     */
    @SuppressWarnings("unchecked")
    private String searchAllAlienInformation(int iD) {
        String resultString = "";
        String race = "";
        String extra = "";
        try {

            if (db.fetchSingle("SELECT ALIEN_ID FROM BOGLODITE WHERE ALIEN_ID = " + iD) != null) {
                race = "Boglodite";
                extra = "Antal boogies:\n  " + db.fetchSingle("SELECT ANTAL_BOOGIES FROM BOGLODITE WHERE ALIEN_ID = " + iD);
            } else if (db.fetchSingle("SELECT ALIEN_ID FROM SQUID WHERE ALIEN_ID = " + iD) != null) {
                race = "Squid";
                extra = "Antal armar:\n  " + db.fetchSingle("SELECT ANTAL_ARMAR FROM SQUID WHERE ALIEN_ID = " + iD);
            } else if (db.fetchSingle("SELECT ALIEN_ID FROM WORM WHERE ALIEN_ID = " + iD) != null) {
                race = "Worm";
            }

            HashMap<String, String> aliens = db.fetchRow("SELECT * FROM ALIEN WHERE ALIEN_ID = " + iD);
            resultString = "Namn:\n  " + aliens.get("NAMN")
                    + "\nAlienID:\n  " + aliens.get("ALIEN_ID")
                    + "\nRegistreringsdatum:\n  " + aliens.get("REGISTRERINGSDATUM")
                    + "\nTelefon:\n  " + aliens.get("TELEFON")
                    + "\n----------"
                    + "\nRas:\n  " + race
                    + "\n" + extra
                    + "\n----------"
                    + "\nOmråde:\n  " + db.fetchSingle("SELECT BENAMNING FROM OMRADE WHERE OMRADES_ID = (SELECT FINNS_I FROM PLATS WHERE PLATS_ID = " + Integer.parseInt(aliens.get("PLATS")) + ")")
                    + "\nPlats:\n  " + db.fetchSingle("SELECT BENAMNING FROM PLATS WHERE PLATS_ID = " + Integer.parseInt(aliens.get("PLATS")))
                    + "\n----------"
                    + "\nAnsvarig Agent:\n  " + db.fetchSingle("SELECT NAMN FROM AGENT WHERE AGENT_ID = " + Integer.parseInt(aliens.get("ANSVARIG_AGENT")));
        } catch (InfException exception) {
            JOptionPane.showMessageDialog(null, Constant.ERROR_DATABASE);
            System.out.println(exception.getMessage());
        }
        return resultString;
    }

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
            java.util.logging.Logger.getLogger(AgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgentFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgentFrame(db, agentID, loginEntityType, isAdmin).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAgentButton;
    private javax.swing.JButton addAlienButton;
    private javax.swing.JPanel addChangeDeleteAgent;
    private javax.swing.JPanel addChangeDeleteAlien;
    private javax.swing.JButton addEquipment;
    private javax.swing.JTabbedPane adminTabbedPanel;
    private javax.swing.JLabel areaChefAgentIdLabel;
    private javax.swing.JLabel areaChefLabel;
    private javax.swing.JLabel areaChefMainLabel;
    private javax.swing.JLabel areaChefNameLabel;
    private javax.swing.JLabel areaChefPhoneLabel;
    private javax.swing.JComboBox<String> areaComboBox;
    private javax.swing.JLabel areaLabel;
    private javax.swing.JButton changeAgentButton;
    private javax.swing.JButton changeAlienButton;
    private javax.swing.JButton changePassword;
    private javax.swing.JPanel chefAreaPanel;
    private javax.swing.JComboBox<String> chooseChefComboBox;
    private javax.swing.JTextField currentChefTextField;
    private javax.swing.JButton deleteAgentButton;
    private javax.swing.JButton deleteAlienButton;
    private javax.swing.JButton deleteEquipment;
    private javax.swing.JPanel equipmentPanel;
    private javax.swing.JLabel fromDateLabel;
    private javax.swing.JSpinner fromDateSpinner;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelForArea;
    private javax.swing.JLabel labelForAreaChef;
    private javax.swing.JLabel labelForChooseChefComboBox;
    private javax.swing.JLabel labelForCurrentChef;
    private javax.swing.JLabel labelForName;
    private javax.swing.JLabel labelForPhone;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JComboBox<String> placeComboBox;
    private javax.swing.JComboBox<String> raceComboBox;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JScrollPane resultScrollPanel;
    private javax.swing.JTextArea resultTextArea;
    private javax.swing.JTextField searchAlienNameTextField;
    private javax.swing.JButton searchAliensByDateButton;
    private javax.swing.JButton searchAliensByRaceButton;
    private javax.swing.JButton searchAliensInPlaceButton;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JLabel searchPlaceLabel;
    private javax.swing.JLabel searchPlaceLabel1;
    private javax.swing.JLabel searchPlaceLabel2;
    private javax.swing.JLabel searchPlaceLabel3;
    private javax.swing.JButton searchSpecificButton;
    private javax.swing.JComboBox<String> searchedAreaComboBox;
    private javax.swing.JSeparator seperator1;
    private javax.swing.JSeparator seperator2;
    private javax.swing.JSeparator seperator4;
    private javax.swing.JPanel setAreaChefPanel;
    private javax.swing.JComboBox<String> setChefAreaComboBox;
    private javax.swing.JPanel setChefPanel;
    private javax.swing.JButton setChefPlace;
    private javax.swing.JButton setNewAreaChef;
    private javax.swing.JLabel titleForAreaChefId;
    private javax.swing.JLabel titleForAreaChefName;
    private javax.swing.JLabel titleForAreaChefPhone;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel toDateLabel;
    private javax.swing.JSpinner toDateSpinner;
    // End of variables declaration//GEN-END:variables
}
