
public class ChessController {
    private ChessModel model;

    public ChessController(ChessModel model){
        this.model = model;
 }

    public void setChess(int x, int y, int player) throws ArrayIndexOutOfBoundsException, ChessExistsException{
        int[][] chessBoard = model.getChesses();
        if(chessBoard[x][y] > 0) throw new ChessExistsException();
        model.setChess(x, y, player);
    }

    public void setToAi(int x, int y, int player) throws ArrayIndexOutOfBoundsException, ChessExistsException{
        int[][] chessBoard = model.getChesses();
        if(chessBoard[x][y] > 0) throw new ChessExistsException();
        model.setToAi(x, y, player);
    }

    public void setChessByAi(int x, int y, int player){
        int[][] chessBoard = model.getChesses();
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
