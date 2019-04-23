import java.util.ArrayList;


public class ChessModel implements Config, Listener{
    private int[][] chesses = new int[LINE][LINE];
    private Event e;


    public ChessModel(Event e){
        for(int i = 0; i < LINE; i++){
            for(int j = 0; j < LINE; j++) {
                chesses[i][j] = 0;
            }
        }
        this.e = e;
        e.register(this);
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

    @Override
    public void showChange(int[] step, int player){
        chesses[step[0]][step[1]] = player;
        if(player != 0){
            if(checkGameOver(step[0], step[1])) e.gameOver(player);
        }

    }
    public void gameOver(int player){

    }
}
