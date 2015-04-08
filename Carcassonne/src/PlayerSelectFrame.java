
import javax.swing.ImageIcon;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
 */
public class PlayerSelectFrame extends javax.swing.JFrame {
    
    private String player1Type = "Human";
    private String player2Type = "Human";
    private boolean player1BotAutoMove = false;
    private boolean player2BotAutoMove = false;
    private ImageIcon meeple1; //Image of player 1's meeple
    private ImageIcon meeple2; //Image of player 2's meeple

    /**
     * Creates new form PlayerSelect
     */
    public PlayerSelectFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabelCarcassonne = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPlayer1 = new javax.swing.JLabel();
        jRadioButtonPlayer1Bot = new javax.swing.JRadioButton();
        jRadioButtonPlayer1Human = new javax.swing.JRadioButton();
        jLabelPlayer1Name = new javax.swing.JLabel();
        jLabelPlayer2 = new javax.swing.JLabel();
        jRadioButtonPlayer2Human = new javax.swing.JRadioButton();
        jRadioButtonPlayer2Bot = new javax.swing.JRadioButton();
        jTextFieldPlayer1Name = new javax.swing.JTextField();
        jLabelPlayer2Name = new javax.swing.JLabel();
        jTextFieldPlayer2Name = new javax.swing.JTextField();
        jLabelPlayer1Picture = new javax.swing.JLabel();
        jLabelPlayer2Picture = new javax.swing.JLabel();
        jLabelBotDifficultyPlayer1 = new javax.swing.JLabel();
        jComboBoxBotDifficultyPlayer1 = new javax.swing.JComboBox();
        jLabelBotDifficultyPlayer2 = new javax.swing.JLabel();
        jComboBoxBotDifficultyPlayer2 = new javax.swing.JComboBox();
        jLabelBotAutoMovePlayer1 = new javax.swing.JLabel();
        jComboBoxBotAutoMovePlayer1 = new javax.swing.JComboBox();
        jLabelBotAutoMovePlayer2 = new javax.swing.JLabel();
        jComboBoxBotAutoMovePlayer2 = new javax.swing.JComboBox();
        jButtonStartGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Carcassonne - Player Selection");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(204, 204, 204));
        setName("framePlayerSelect"); // NOI18N
        setResizable(false);

        jLabelCarcassonne.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelCarcassonne.setText("Carcassonne");
        jLabelCarcassonne.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabelPlayer1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer1.setText("Player 1");
        jLabelPlayer1.setToolTipText("");

        buttonGroup1.add(jRadioButtonPlayer1Bot);
        jRadioButtonPlayer1Bot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonPlayer1Bot.setText("Bot");
        jRadioButtonPlayer1Bot.setFocusable(false);
        jRadioButtonPlayer1Bot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPlayer1BotActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonPlayer1Human);
        jRadioButtonPlayer1Human.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonPlayer1Human.setSelected(true);
        jRadioButtonPlayer1Human.setText("Human");
        jRadioButtonPlayer1Human.setToolTipText("");
        jRadioButtonPlayer1Human.setFocusable(false);
        jRadioButtonPlayer1Human.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPlayer1HumanActionPerformed(evt);
            }
        });

        jLabelPlayer1Name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer1Name.setText("Name:");

        jLabelPlayer2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer2.setText("Player 2");

        buttonGroup2.add(jRadioButtonPlayer2Human);
        jRadioButtonPlayer2Human.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonPlayer2Human.setSelected(true);
        jRadioButtonPlayer2Human.setText("Human");
        jRadioButtonPlayer2Human.setToolTipText("");
        jRadioButtonPlayer2Human.setFocusable(false);
        jRadioButtonPlayer2Human.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPlayer2HumanActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButtonPlayer2Bot);
        jRadioButtonPlayer2Bot.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonPlayer2Bot.setText("Bot");
        jRadioButtonPlayer2Bot.setToolTipText("");
        jRadioButtonPlayer2Bot.setFocusable(false);
        jRadioButtonPlayer2Bot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPlayer2BotActionPerformed(evt);
            }
        });

        jTextFieldPlayer1Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabelPlayer2Name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer2Name.setText("Name:");
        jLabelPlayer2Name.setToolTipText("");

        jTextFieldPlayer2Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabelPlayer1Picture.setBackground(new java.awt.Color(102, 102, 102));
        jLabelPlayer1Picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Player1Meeple.png"))); // NOI18N
        jLabelPlayer1Picture.setToolTipText("");

        jLabelPlayer2Picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Player2Meeple.png"))); // NOI18N
        jLabelPlayer2Picture.setToolTipText("");

        jLabelBotDifficultyPlayer1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelBotDifficultyPlayer1.setText("Bot Difficulty:");

        jComboBoxBotDifficultyPlayer1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxBotDifficultyPlayer1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));
        jComboBoxBotDifficultyPlayer1.setToolTipText("");
        jComboBoxBotDifficultyPlayer1.setFocusable(false);
        jComboBoxBotDifficultyPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBotDifficultyPlayer1ActionPerformed(evt);
            }
        });

        jLabelBotDifficultyPlayer2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelBotDifficultyPlayer2.setText("Bot Difficulty:");

        jComboBoxBotDifficultyPlayer2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxBotDifficultyPlayer2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));
        jComboBoxBotDifficultyPlayer2.setToolTipText("");
        jComboBoxBotDifficultyPlayer2.setFocusable(false);
        jComboBoxBotDifficultyPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBotDifficultyPlayer2ActionPerformed(evt);
            }
        });

        jLabelBotAutoMovePlayer1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelBotAutoMovePlayer1.setText("Bot Auto Move:");

        jComboBoxBotAutoMovePlayer1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxBotAutoMovePlayer1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));
        jComboBoxBotAutoMovePlayer1.setToolTipText("");
        jComboBoxBotAutoMovePlayer1.setFocusable(false);
        jComboBoxBotAutoMovePlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBotAutoMovePlayer1ActionPerformed(evt);
            }
        });

        jLabelBotAutoMovePlayer2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelBotAutoMovePlayer2.setText("Bot Auto Move:");

        jComboBoxBotAutoMovePlayer2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxBotAutoMovePlayer2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));
        jComboBoxBotAutoMovePlayer2.setToolTipText("");
        jComboBoxBotAutoMovePlayer2.setFocusable(false);
        jComboBoxBotAutoMovePlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBotAutoMovePlayer2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabelPlayer1Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabelPlayer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                .addComponent(jLabelPlayer2Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabelPlayer2)
                .addGap(151, 151, 151))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelBotAutoMovePlayer1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxBotAutoMovePlayer1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelBotDifficultyPlayer1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxBotDifficultyPlayer1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jRadioButtonPlayer1Bot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonPlayer1Human, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelPlayer1Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jRadioButtonPlayer2Human, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButtonPlayer2Bot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelBotDifficultyPlayer2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxBotDifficultyPlayer2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelPlayer2Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPlayer2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelBotAutoMovePlayer2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxBotAutoMovePlayer2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelPlayer1)
                        .addComponent(jLabelPlayer2))
                    .addComponent(jLabelPlayer1Picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPlayer2Picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonPlayer1Human)
                    .addComponent(jRadioButtonPlayer2Human))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonPlayer1Bot)
                    .addComponent(jRadioButtonPlayer2Bot))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBotDifficultyPlayer1)
                    .addComponent(jComboBoxBotDifficultyPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBotDifficultyPlayer2)
                    .addComponent(jComboBoxBotDifficultyPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBotAutoMovePlayer1)
                    .addComponent(jComboBoxBotAutoMovePlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBotAutoMovePlayer2)
                    .addComponent(jComboBoxBotAutoMovePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPlayer1Name)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelPlayer2Name)
                        .addComponent(jTextFieldPlayer2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        jButtonStartGame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonStartGame.setText("Start Game");
        jButtonStartGame.setToolTipText("");
        jButtonStartGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabelCarcassonne))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(jButtonStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCarcassonne)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartGameActionPerformed
        GameGUI frame = new GameGUI(jTextFieldPlayer1Name.getText(),jTextFieldPlayer2Name.getText(),player1Type,player2Type,player1BotAutoMove,player2BotAutoMove);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonStartGameActionPerformed

    private void jRadioButtonPlayer2HumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPlayer2HumanActionPerformed
        jComboBoxBotDifficultyPlayer2.removeAllItems();
        jComboBoxBotAutoMovePlayer2.removeAllItems();
        jComboBoxBotDifficultyPlayer2.addItem("None");
        jComboBoxBotAutoMovePlayer2.addItem("None");
        jComboBoxBotDifficultyPlayer2.setSelectedIndex(0);
        jComboBoxBotAutoMovePlayer2.setSelectedIndex(0);
        player2Type = "Human";
        player2BotAutoMove = false;
    }//GEN-LAST:event_jRadioButtonPlayer2HumanActionPerformed

    private void jRadioButtonPlayer1HumanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPlayer1HumanActionPerformed
        jComboBoxBotDifficultyPlayer1.removeAllItems();
        jComboBoxBotAutoMovePlayer1.removeAllItems();
        jComboBoxBotDifficultyPlayer1.addItem("None");
        jComboBoxBotAutoMovePlayer1.addItem("None");
        jComboBoxBotDifficultyPlayer1.setSelectedIndex(0);
        jComboBoxBotAutoMovePlayer1.setSelectedIndex(0);
        player1Type = "Human";
        player1BotAutoMove = false;
    }//GEN-LAST:event_jRadioButtonPlayer1HumanActionPerformed

    private void jRadioButtonPlayer1BotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPlayer1BotActionPerformed
        jComboBoxBotDifficultyPlayer1.removeAllItems();
        jComboBoxBotAutoMovePlayer1.removeAllItems();
        jComboBoxBotDifficultyPlayer1.addItem("Beginner");
        jComboBoxBotDifficultyPlayer1.addItem("Advanced");
        jComboBoxBotAutoMovePlayer1.addItem("On");
        jComboBoxBotAutoMovePlayer1.addItem("Off");
        jComboBoxBotDifficultyPlayer1.setSelectedIndex(0);
        jComboBoxBotAutoMovePlayer1.setSelectedIndex(0);
        player1Type = "Bot" + "-" + "Beginner";
        player1BotAutoMove = true;
    }//GEN-LAST:event_jRadioButtonPlayer1BotActionPerformed

    private void jRadioButtonPlayer2BotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPlayer2BotActionPerformed
        jComboBoxBotDifficultyPlayer2.removeAllItems();
        jComboBoxBotAutoMovePlayer2.removeAllItems();
        jComboBoxBotDifficultyPlayer2.addItem("Beginner");
        jComboBoxBotDifficultyPlayer2.addItem("Advanced");
        jComboBoxBotAutoMovePlayer2.addItem("On");
        jComboBoxBotAutoMovePlayer2.addItem("Off");
        jComboBoxBotDifficultyPlayer2.setSelectedIndex(0);
        jComboBoxBotAutoMovePlayer2.setSelectedIndex(0);
        player2Type = "Bot" + "-" + "Beginner";
        player2BotAutoMove = true;
    }//GEN-LAST:event_jRadioButtonPlayer2BotActionPerformed

    private void jComboBoxBotDifficultyPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBotDifficultyPlayer1ActionPerformed
        if(!jRadioButtonPlayer1Human.isSelected()){
            player1Type = "Bot" + "-" + (String)jComboBoxBotDifficultyPlayer1.getSelectedItem();
        }
    }//GEN-LAST:event_jComboBoxBotDifficultyPlayer1ActionPerformed

    private void jComboBoxBotDifficultyPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBotDifficultyPlayer2ActionPerformed
        if(!jRadioButtonPlayer2Human.isSelected()){
            player2Type = "Bot" + "-" + (String)jComboBoxBotDifficultyPlayer2.getSelectedItem();
        }
    }//GEN-LAST:event_jComboBoxBotDifficultyPlayer2ActionPerformed

    private void jComboBoxBotAutoMovePlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBotAutoMovePlayer1ActionPerformed
        if(!jRadioButtonPlayer1Human.isSelected()){
            String choice = (String)jComboBoxBotAutoMovePlayer1.getSelectedItem();
            if(choice != null){
                if(choice.equals("On")){
                    player1BotAutoMove = true;
                }else{
                    player1BotAutoMove = false;
                }
            }
        }
    }//GEN-LAST:event_jComboBoxBotAutoMovePlayer1ActionPerformed

    private void jComboBoxBotAutoMovePlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBotAutoMovePlayer2ActionPerformed
        if(!jRadioButtonPlayer2Human.isSelected()){
            String choice = (String)jComboBoxBotAutoMovePlayer2.getSelectedItem();
            if(choice != null){
                if(choice.equals("On")){
                    player2BotAutoMove = true;
                }else{
                    player2BotAutoMove = false;
                }
            }
        }
    }//GEN-LAST:event_jComboBoxBotAutoMovePlayer2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonStartGame;
    private javax.swing.JComboBox jComboBoxBotAutoMovePlayer1;
    private javax.swing.JComboBox jComboBoxBotAutoMovePlayer2;
    private javax.swing.JComboBox jComboBoxBotDifficultyPlayer1;
    private javax.swing.JComboBox jComboBoxBotDifficultyPlayer2;
    private javax.swing.JLabel jLabelBotAutoMovePlayer1;
    private javax.swing.JLabel jLabelBotAutoMovePlayer2;
    private javax.swing.JLabel jLabelBotDifficultyPlayer1;
    private javax.swing.JLabel jLabelBotDifficultyPlayer2;
    private javax.swing.JLabel jLabelCarcassonne;
    private javax.swing.JLabel jLabelPlayer1;
    private javax.swing.JLabel jLabelPlayer1Name;
    private javax.swing.JLabel jLabelPlayer1Picture;
    private javax.swing.JLabel jLabelPlayer2;
    private javax.swing.JLabel jLabelPlayer2Name;
    private javax.swing.JLabel jLabelPlayer2Picture;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonPlayer1Bot;
    private javax.swing.JRadioButton jRadioButtonPlayer1Human;
    private javax.swing.JRadioButton jRadioButtonPlayer2Bot;
    private javax.swing.JRadioButton jRadioButtonPlayer2Human;
    private javax.swing.JTextField jTextFieldPlayer1Name;
    private javax.swing.JTextField jTextFieldPlayer2Name;
    // End of variables declaration//GEN-END:variables
}
