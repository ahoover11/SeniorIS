/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //GameGUI gui = new GameGUI();
        //gui.setVisible(true);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlayerSelectFrame frame = new PlayerSelectFrame();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
}
