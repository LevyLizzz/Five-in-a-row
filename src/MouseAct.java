import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAct extends MouseAdapter implements Config {
    private Player p;

    public MouseAct(Player p){
        this.p = p;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int x = (e.getX() - X + CHESS / 2) / SIZE;
        int y = (e.getY() - Y + CHESS / 2) / SIZE;
        p.setChess(x, y);
    }

}