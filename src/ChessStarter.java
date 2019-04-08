import javax.swing.*;
import java.awt.*;

/**
 * Created by LevyLi on 2019/4/2.
 */
public class ChessStarter {
    public static void main(String[] args){
        ChessModel model = new ChessModel();
        ChessController controller = new ChessController(model);
//        ChessListener listener = new ChessListener();
        ChessView view = new ChessView(model, controller);
        view.initUI();
    }
}
