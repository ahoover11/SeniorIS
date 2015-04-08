import java.net.URL;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
 */
public class FinalScoreFrame extends javax.swing.JFrame {
    
    private ImageIcon meeple1; //Image of player 1's meeple
    private ImageIcon meeple2; //Image of player 2's meeple

    /**
     * Creates new form FinalScoreFrame
     */
    public FinalScoreFrame(String namePlayer1, String namePlayer2, String scorePlayer1, String scorePlayer2, String statusPlayer1, String statusPlayer2, 
                           String typePlayer1, String typePlayer2) 
    {
        initComponents();
        
        jTextFieldPlayer1Name.setText(namePlayer1);
        jTextFieldPlayer2Name.setText(namePlayer2);
        jTextFieldPlayer1Score.setText(scorePlayer1);
        jTextFieldPlayer2Score.setText(scorePlayer2);
        jTextFieldPlayer1Status.setText(statusPlayer1);
        jTextFieldPlayer2Status.setText(statusPlayer2);
        jTextFieldPlayer1Type.setText(typePlayer1);
        jTextFieldPlayer2Type.setText(typePlayer2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCarcassonne = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPlayer1 = new javax.swing.JLabel();
        jLabelPlayer1Name = new javax.swing.JLabel();
        jLabelPlayer2 = new javax.swing.JLabel();
        jLabelPlayer2Name = new javax.swing.JLabel();
        jLabelPlayer1Picture = new javax.swing.JLabel();
        jLabelPlayer2Picture = new javax.swing.JLabel();
        jLabelPlayer2Score = new javax.swing.JLabel();
        jLabelPlayer1Score = new javax.swing.JLabel();
        jTextFieldPlayer1Name = new javax.swing.JTextField();
        jTextFieldPlayer1Score = new javax.swing.JTextField();
        jTextFieldPlayer2Name = new javax.swing.JTextField();
        jTextFieldPlayer2Score = new javax.swing.JTextField();
        jTextFieldPlayer2Status = new javax.swing.JTextField();
        jTextFieldPlayer1Status = new javax.swing.JTextField();
        jLabelPlayer1Type = new javax.swing.JLabel();
        jTextFieldPlayer1Type = new javax.swing.JTextField();
        jLabelPlayer2Type = new javax.swing.JLabel();
        jTextFieldPlayer2Type = new javax.swing.JTextField();
        jButtonNewGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Carcassonne - Final Scores");
        setAlwaysOnTop(true);
        setName("frameFinalScore"); // NOI18N
        setResizable(false);

        jLabelCarcassonne.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelCarcassonne.setText("Carcassonne");
        jLabelCarcassonne.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabelPlayer1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer1.setText("Player 1");
        jLabelPlayer1.setToolTipText("");

        jLabelPlayer1Name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer1Name.setText("Name:");

        jLabelPlayer2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer2.setText("Player 2");

        jLabelPlayer2Name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer2Name.setText("Name:");
        jLabelPlayer2Name.setToolTipText("");

        jLabelPlayer1Picture.setBackground(new java.awt.Color(102, 102, 102));
        jLabelPlayer1Picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Player1Meeple.png"))); // NOI18N
        jLabelPlayer1Picture.setToolTipText("");

        jLabelPlayer2Picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Player2Meeple.png"))); // NOI18N
        jLabelPlayer2Picture.setToolTipText("");

        jLabelPlayer2Score.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer2Score.setText("Score:");

        jLabelPlayer1Score.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer1Score.setText("Score:");

        jTextFieldPlayer1Name.setEditable(false);
        jTextFieldPlayer1Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer1Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldPlayer1Score.setEditable(false);
        jTextFieldPlayer1Score.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer1Score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer1Score.setToolTipText("");

        jTextFieldPlayer2Name.setEditable(false);
        jTextFieldPlayer2Name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer2Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldPlayer2Score.setEditable(false);
        jTextFieldPlayer2Score.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer2Score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer2Score.setToolTipText("");

        jTextFieldPlayer2Status.setEditable(false);
        jTextFieldPlayer2Status.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldPlayer2Status.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer2Status.setFocusable(false);

        jTextFieldPlayer1Status.setEditable(false);
        jTextFieldPlayer1Status.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldPlayer1Status.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer1Status.setFocusable(false);

        jLabelPlayer1Type.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer1Type.setText("Player Type:");

        jTextFieldPlayer1Type.setEditable(false);
        jTextFieldPlayer1Type.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer1Type.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer1Type.setToolTipText("");

        jLabelPlayer2Type.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPlayer2Type.setText("Player Type:");

        jTextFieldPlayer2Type.setEditable(false);
        jTextFieldPlayer2Type.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldPlayer2Type.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer2Type.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelPlayer1Type)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPlayer1Type, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelPlayer1Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPlayer1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelPlayer1Score)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelPlayer1Name)
                                .addGap(2, 2, 2)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPlayer1Score)
                            .addComponent(jTextFieldPlayer1Name)))
                    .addComponent(jTextFieldPlayer1Status))
                .addGap(79, 79, 79)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelPlayer2Type)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPlayer2Type))
                    .addComponent(jTextFieldPlayer2Status)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelPlayer2Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPlayer2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPlayer2Score)
                            .addComponent(jLabelPlayer2Name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPlayer2Score)
                            .addComponent(jTextFieldPlayer2Name))))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelPlayer1)
                    .addComponent(jLabelPlayer2)
                    .addComponent(jLabelPlayer1Picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPlayer2Picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPlayer1Name)
                    .addComponent(jTextFieldPlayer1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPlayer2Name)
                    .addComponent(jTextFieldPlayer2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPlayer1Score)
                    .addComponent(jTextFieldPlayer1Score, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPlayer2Score)
                    .addComponent(jTextFieldPlayer2Score, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPlayer1Type)
                    .addComponent(jTextFieldPlayer1Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPlayer2Type)
                    .addComponent(jTextFieldPlayer2Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPlayer1Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPlayer2Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jButtonNewGame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonNewGame.setText("New Game");
        jButtonNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewGameActionPerformed(evt);
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
                        .addGap(340, 340, 340)
                        .addComponent(jButtonNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCarcassonne)
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButtonNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewGameActionPerformed
        PlayerSelectFrame frame = new PlayerSelectFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonNewGameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNewGame;
    private javax.swing.JLabel jLabelCarcassonne;
    private javax.swing.JLabel jLabelPlayer1;
    private javax.swing.JLabel jLabelPlayer1Name;
    private javax.swing.JLabel jLabelPlayer1Picture;
    private javax.swing.JLabel jLabelPlayer1Score;
    private javax.swing.JLabel jLabelPlayer1Type;
    private javax.swing.JLabel jLabelPlayer2;
    private javax.swing.JLabel jLabelPlayer2Name;
    private javax.swing.JLabel jLabelPlayer2Picture;
    private javax.swing.JLabel jLabelPlayer2Score;
    private javax.swing.JLabel jLabelPlayer2Type;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextFieldPlayer1Name;
    private javax.swing.JTextField jTextFieldPlayer1Score;
    private javax.swing.JTextField jTextFieldPlayer1Status;
    private javax.swing.JTextField jTextFieldPlayer1Type;
    private javax.swing.JTextField jTextFieldPlayer2Name;
    private javax.swing.JTextField jTextFieldPlayer2Score;
    private javax.swing.JTextField jTextFieldPlayer2Status;
    private javax.swing.JTextField jTextFieldPlayer2Type;
    // End of variables declaration//GEN-END:variables
}