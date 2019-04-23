import java.util.ArrayList;

public class ChessController implements Listener, Config{
    private ChessView view;
    private int[][] chesses = new int[LINE][LINE];
    private Event e;
    private boolean isAi;
    private int turn;
    private MouseAct m1;
    private MouseAct m2;
    private ArrayList<int[]> steps = new ArrayList<int[]>();

    public ChessController(Event e, ChessView view, boolean isAi){
        this.e = e;
        this.view = view;
        e.register(this);
        this.isAi = isAi;
        for(int i = 0; i < LINE; i++){
            for(int j = 0; j < LINE; j++) {
                chesses[i][j] = 0;
            }
        }
        turn = 1;

        ChessModel model = new ChessModel(e);

        Player p1 = new Player(2, this);
        this.m1 = new MouseAct(p1);

        if(isAi){
            AiModel ai = new AiModel(1, e);
        }else {
            Player p2 = new Player(1, this);
            this.m2 = new MouseAct(p2);
        }

        start();
    }

    public void setChess(int x, int y, int player) throws ArrayIndexOutOfBoundsException, ChessExistsException{
        if(chesses[x][y] > 0) throw new ChessExistsException();
        int[] step = {x, y};
        chesses[x][y] = player;
        e.setChess(step, player);
    }


    public void rollBack() throws ChessRollBackException{
        int length = steps.size();

        if(length == 0) throw new ChessRollBackException();

        int[] step = steps.remove(length - 1);

        chesses[step[0]][step[1]] = 0;
        e.setChess(step, 0);

        if(isAi){
            int[] prevStep = steps.remove(length -1);
            chesses[prevStep[0]][prevStep[1]] = 0;
            e.setChess(prevStep, 0);
        }
    }


    public class ChessRollBackException extends Exception{

    }

    public class ChessExistsException extends Exception{

    }

    public void start(){
        if(isAi){
            if(turn%2 == 1){
                view.bindMouseListenr(m1);
            }else {
                view.unbindMouseListenr(m1);
                view.waitAi();
            }
        }else {
            if(turn%2 == 1){
                view.unbindMouseListenr(m2);
                view.bindMouseListenr(m1);
            }else {
                view.unbindMouseListenr(m1);
                view.bindMouseListenr(m2);
            }
        }
    }



    public void err(String msg){
        view.alert(msg);
    }

    @Override
    public void showChange(int[] step, int player){
        chesses[step[0]][step[1]] = player;
        if(player == 0){
            turn --;
        }else{
            this.steps.add(step);
            turn ++;
        }
        view.setChesses(chesses, player);
        start();
    }
    public void gameOver(int player){
        view.gameOverBy(player);
    }

}
