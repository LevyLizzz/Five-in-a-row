import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ChessView implements Config{
    private JFrame jf;
    private JPanel jp;
    private GamePanel gp;
    private JLabel la;
    private Event e;

    private int[][] chesses;
    private JButton jbuRoll;

    public ChessView(Event e) {
        this.e = e;
        JFrame jf = new JFrame();
        jf.setTitle("Five In A Row Version1.0");
        jf.setSize(2 * X + LINE * SIZE + 150, 2 * Y + LINE * SIZE);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(3);
        this.jf = jf;
        JPanel jp = new JPanel();
        this.jp = jp;
        jp.setPreferredSize(new Dimension(150, 0));
        jp.setBackground(Color.black);

        JButton jbu2p = new JButton("2 Players Game");
        jbu2p.setPreferredSize(new Dimension(150, 50));

        JButton jbuAi = new JButton("Play with AI");
        jbuAi.setPreferredSize(new Dimension(150, 50));


        JLabel label = new JLabel("Current Turn: ");
        label.setForeground(Color.white);
        this.la = label;
        jp.add(jbu2p);
        jp.add(jbuAi);
        jp.add(label);
        jf.getContentPane().add(jp, BorderLayout.EAST);
        jf.setVisible(true);

        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand())
                {
                    case "2 Players Game":
                        initGameBoard(false);
                        break;
                    case "Play with AI":
                        initGameBoard(true);
                        break;

                }
            }
        };

        jbu2p.addActionListener(btnListener);
        jbuAi.addActionListener(btnListener);
    }


    private void initGameBoard(boolean isAi){
        if(gp != null){
            jf.remove(gp);
            jf.validate();
        }
        if(jbuRoll != null){
            jp.remove(jbuRoll);
            jp.validate();
        }
        GamePanel gp = new GamePanel();
        jf.getContentPane().add(gp, BorderLayout.CENTER);
        this.gp = gp;
        //intialize Event
        e.clear();
        JButton jbuRoll = new JButton("Roll back");
        jbuRoll.setPreferredSize(new Dimension(150, 50));
        this.jbuRoll = jbuRoll;
        jp.add(jbuRoll);
        jp.repaint();
        jf.getContentPane().add(jp, BorderLayout.EAST);
        jf.validate();

        ChessController controller = new ChessController(e, this, isAi);

        jbuRoll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    controller.rollBack();
                }catch (ChessController.ChessRollBackException err){
                    alert("Unable to roll back!");
                }
            }
        });
        jf.repaint();
    }


    public void bindMouseListenr(MouseAct m){
        gp.addMouseListener(m);
    }

    public void unbindMouseListenr(MouseAct m){
        gp.removeMouseListener(m);
    }


    public void alert(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }

    public void waitAi(){
        la.setText("AI is running...");
    }

    public void setChesses(int[][] chesses, int player){
        this.chesses = chesses;
        gp.paintChesss(chesses);
        String turn = (player % 2) == 0 ? "White" : "Black";
        la.setText("Current Turn: " + turn);
    }

    public void gameOverBy(int player) {
        if (player == 2) {
            JOptionPane.showMessageDialog(null, "Black Win!");
        } else {
            JOptionPane.showMessageDialog(null, "White Win!");
        }
    }

}
