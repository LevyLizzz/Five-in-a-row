import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Config{
    private int[][] chesses;
    public void paint(Graphics g) {
        this.setBackground(new Color(252, 210, 123));
        g.setColor(Color.black);
        for (int i = 0; i < LINE; i++) {
            g.drawLine(X, Y+i * SIZE, X + (LINE - 1)*SIZE, Y + i*SIZE);
            g.drawLine(X+i * SIZE, Y, X + i*SIZE, Y + (LINE - 1)*SIZE);
        }
        if(chesses != null){
            for(int i = 0; i < chesses.length; i++) {
                for(int j = 0; j < chesses.length; j++) {
                    if(chesses[i][j] == 2){
                        g.setColor(Color.black);
                        g.fillOval(X + i*SIZE - CHESS/2, Y + j*SIZE-CHESS/2, CHESS, CHESS);
                    }else if(chesses[i][j] == 1) {
                        g.setColor(Color.WHITE);
                        g.fillOval(X + i*SIZE - CHESS/2, Y + j*SIZE-CHESS/2, CHESS, CHESS);
                    }
                }
            }
        }
    }

    public void paintChesss(int[][] chessBoard){
        this.chesses = chessBoard;

        this.repaint();


    }

}
