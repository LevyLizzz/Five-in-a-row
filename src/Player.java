
public class Player {
    protected int player;
    protected ChessController controller;

    public Player(int player, ChessController controller){
        this.player = player;
        this.controller = controller;
    }

    public void setChess(int x, int y){
        try {
            controller.setChess(x, y, this.player);
        } catch (ChessController.ChessExistsException e){
            controller.err("Chess exist!");
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println(e);
        }

    }

}
