import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Config{
    private ChessModel model;

    public GamePanel(ChessModel model){
        this.model = model;
    }

    public void paint(Graphics g) {
        int[][] chessBoard = model.getChesses();
        this.setBackground(new Color(252, 210, 123));
        g.setColor(Color.black);
        for (int i = 0; i < LINE; i++) {
            g.drawLine(X, Y+i * SIZE, X + (LINE - 1)*SIZE, Y + i*SIZE);
            g.drawLine(X+i * SIZE, Y, X + i*SIZE, Y + (LINE - 1)*SIZE);
        }

        for(int i = 0; i < chessBoard.length; i++) {
            for(int j = 0; j < chessBoard.length; j++) {
                if(chessBoard[i][j] == 2){
                    g.setColor(Color.black);
                    g.fillOval(X + i*SIZE - CHESS/2, Y + j*SIZE-CHESS/2, CHESS, CHESS);
                }else if(chessBoard[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(X + i*SIZE - CHESS/2, Y + j*SIZE-CHESS/2, CHESS, CHESS);
                }
            }
        }
    }

    public void paintChesss(int[][] chessBoard){
//        this.repaint();
        Graphics g = this.getGraphics();
        for(int i = 0; i < chessBoard.length; i++) {
            for(int j = 0; j < chessBoard.length; j++) {
                if(chessBoard[i][j] == 2){
                    g.setColor(Color.black);
                    g.fillOval(X + i*SIZE - CHESS/2, Y + j*SIZE-CHESS/2, CHESS, CHESS);
                }else if(chessBoard[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(X + i*SIZE - CHESS/2, Y + j*SIZE-CHESS/2, CHESS, CHESS);
                }
            }
        }
    }
}
