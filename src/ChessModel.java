import java.util.ArrayList;


public class ChessModel implements Config{
    private int[][] chesses = new int[LINE][LINE];
    private ArrayList<Listener> lisArr = new ArrayList<Listener>();
    private ArrayList<int[]> steps = new ArrayList<int[]>();


    public ChessModel(){
        for(int i = 0; i < LINE; i++){
            for(int j = 0; j < LINE; j++) {
                chesses[i][j] = 0;
            }
        }
    }


    public void register(Listener listner){
        lisArr.add(listner);
    }

    public void setChess(int row, int column, int player) {
        this.chesses[row][column] = player;
        int[] step = {row, column};
        this.steps.add(step);
        chessBoardChanged();
        if(checkGameOver(row, column)) gameOver(player);
    }

    public void setToAi(int row, int column, int player) {
        setChess(row, column, player);

        int aiPlayer = (player == 1) ? 2 : 1;
        AiModel ai = new AiModel(aiPlayer, chesses);
        int[] chesse = ai.getChess();
        setChess(chesse[0], chesse[1], aiPlayer);
    }

    public void clear(){
        for(int i = 0; i < LINE; i++){
            for(int j = 0; j < LINE; j++){
                chesses[i][j] = 0;
            }
        }
        steps.clear();
        chessBoardChanged();
    }

    private boolean checkGameOver(int row, int column){
        boolean over = false;
        if(checkColumn(row, column) || checkOblique(row, column) || checkRow(row, column)) over = true;
        return over;
    }

    private boolean checkRow(int x, int y) {
        int count = 0;
        for (int i = x + 1; i<chesses.length; i++){
            if(chesses[i][y] == chesses[x][y]){
                count++;
            } else break;
        }

        for (int i=x; i>=0; i--){
            if(chesses[i][y]==chesses[x][y]){
                count++;
            } else break;
        }

        return  (count >= 5);
    }

    private boolean checkColumn(int x, int y) {
        int count=0;
        for (int i=y+1;i<chesses.length;i++) {//go down
            if (chesses[x][i]==chesses[x][y]) {
                count++;
            }
            else
                break;
        }
        for (int i=y;i>=0;i--) {//go up
            if (chesses[x][i]==chesses[x][y]) {
                count++;
            }
            else
                break;
        }
        return (count >= 5);
    }

    private boolean checkOblique(int x, int y) {
        int count=1;
        for (int i=1;x+i<chesses.length&&y-i>=0;i++) {
            if (chesses[x+i][y-i]==chesses[x][y]) {
                count++;
            }
            else break;
        }
        for (int i=1;x-i>=0&&y+i<chesses.length;i++) {
            if (chesses[x-i][y+i]==chesses[x][y]) {
                count++;
            }
            else break;
        }
        if (count >= 5) return true;

        count = 1;
        for(int i=1;x+i<chesses.length&&y+i<chesses.length;i++) {
            if (chesses[x+i][y+i]==chesses[x][y]) {
                count++;
            }
            else
                break;
        }
        for (int i=1;x-i>=0&&y-i>=0;i++) {
            if (chesses[x-i][y-i]==chesses[x][y]) {
                count++;
            }
            else
                break;
        }

        return  (count >= 5);
    }

    private void chessBoardChanged(){
        for(Listener listener : lisArr){
            listener.showChange();
        }
    }

    private void gameOver(int player){
        for(Listener listener : lisArr){
            listener.gameOver(player);
        }
    }

    public void rollBack() {
        int length = steps.size();
        int[] step = steps.remove(length - 1);
        chesses[step[0]][step[1]] = 0;
        chessBoardChanged();
    }

    public int [] [] getChesses(){
        return this.chesses;
    }
}
