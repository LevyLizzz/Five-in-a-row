import java.awt.*;

/**
 * Created by LevyLi on 2019/3/2.
 */
public class ChessController {
    private ChessModel model;

    public ChessController(ChessModel model){
        this.model = model;
 }

    public void setChess(int x, int y, int player) throws ArrayIndexOutOfBoundsException, ChessExistsException{
        int[][] chessBoard = model.getChess();
        if(chessBoard[x][y] > 0) throw new ChessExistsException();
        model.setChess(x, y, player);
    }

    public class ChessExistsException extends Exception{

    }

    public void start(){
        model.clear();
    }

    public void startAi(){

    }
}
