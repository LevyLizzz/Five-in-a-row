import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChessView extends JPanel implements Listener{

    private ChessController controller;
    private ChessModel model;
    private JFrame jf;
    private GamePanel gp;
    private MouseAdapter mouse;
    private JLabel la;

    private boolean gameOn = true;
    int turn = 0;

    public ChessView(ChessModel model, ChessController controller) {
        this.controller = controller;
        this.model = model;
        this.model.register(this);

        this.mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = (e.getX() - model.X + model.CHESS / 2) / model.SIZE;
                int y = (e.getY() - model.Y + model.CHESS / 2) / model.SIZE;
                run(x, y);
            }
        };
    }

    public void initUI() {

        JFrame jf = new JFrame();
        this.jf = jf;
        jf.setTitle("Five In A Row Version1.0");
        jf.setSize(800, 700);
        jf.setLocationRelativeTo(null);


        GamePanel gp = new GamePanel(model);
        jf.add(gp, BorderLayout.CENTER);
        this.gp = gp;

        jf.setDefaultCloseOperation(3);
        this.setLayout(new FlowLayout());

        JPanel jp2 = new JPanel();
        jp2.setPreferredSize(new Dimension(150, 0));
        jp2.setBackground(Color.black);

        JButton jbuRoll = new JButton("Roll back");
        jbuRoll.setPreferredSize(new Dimension(150, 50));

        JButton jbuR = new JButton("Restart");
        jbuR.setPreferredSize(new Dimension(150, 50));

        JButton jbu2p = new JButton("2 Players Game");
        jbu2p.setPreferredSize(new Dimension(150, 50));

        JButton jbuAi = new JButton("Play with AI");
        jbuAi.setPreferredSize(new Dimension(150, 50));



        JLabel la = new JLabel("Current Turn: ");
        la.setForeground(Color.white);
        this.la = la;
        jp2.add(jbu2p);
        jp2.add(jbuAi);
        jp2.add(jbuRoll);
        jp2.add(jbuR);

        jp2.add(la);

        jf.add(jp2, BorderLayout.EAST);

        jf.setVisible(true);

        ActionListener btnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action  = e.getActionCommand();
                btnAction(action);
            }
        };

        jbu2p.addActionListener(btnListener);
        jbuRoll.addActionListener(btnListener);
        jbuR.addActionListener(btnListener);
        jbuAi.addActionListener(btnListener);
    }


    // merge into privious code
    public void btnAction(String action) {
        if (action.equals("2 Players Game") || action.equals("Restart")) {
            controller.start();
            turn = 1;
        } else if (action.equals("Play with AI")) {

        } else if (action.equals("Roll back")) {
            model.rollBack();
            turn --;
        }
        gp.removeMouseListener(mouse);
        gp.addMouseListener(mouse);
    }

    public void drawChessTable () {
        Graphics g = this.getGraphics();
        gp.paint(g);
    }

    public void run(int x, int y){
        if(gameOn){
            //2 for black
            int player = turn % 2 + 1;

            try {
                controller.setChess(x, y, player);
            } catch (ChessController.ChessExistsException e) {
                JOptionPane.showMessageDialog(null, "Chess Exists!");
                turn --;
            } catch (ArrayIndexOutOfBoundsException e) {
                turn --;
            }

            turn ++;
        }
    }

    @Override
    public void showChange() {
        int[][] chesses = model.getChess();
        gp.repaint();
//        gp.paintChesss(chesses);
        String player = (turn % 2) == 0 ? "Black" : "White";
        la.setText("Current Turn: " + player);
    }

    @Override
    public void gameOver(int player) {
        if (player == 1) {
            JOptionPane.showMessageDialog(null, "Black Win!");
            gp.removeMouseListener(mouse);
        } else {
            JOptionPane.showMessageDialog(null, "White Win!");
            gp.removeMouseListener(mouse);
        }
    }
}
