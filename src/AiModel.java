import java.util.ArrayList;

public class AiModel implements Config, Listener, Runnable{
    private int[][] chesses = new int[LINE][LINE];
    private int player;
    private static int searchDeep = 3;
    public static final int MAXN = 1<<28;
    public static final int MINN = -MAXN;
    private Event e;
    private boolean isStart = false;
    private int[] newStep;
    private int newPlayer;

    public AiModel(int player, Event e){
        this.player = player;
        this.e = e;
        e.register(this);
    }


    public int[] getStep(int[][] chesses){

        MinmaxTree tree = new MinmaxTree(searchDeep, chesses);
        Node root = new Node();
        tree.initTree(0, chesses, root, player);

        int value = tree.maxmin(root, 0, true, MINN, MAXN);
        Node node = tree.getSelectedNode();
        int[] step = {node.x, node.y};
        return step;

    }

    @Override
    public void showChange(int[] step, int player){
        this.newPlayer = player;
        this.newStep = step;

        isStart = true;
        Thread t = new Thread(this);
        t.start();

    }
    public void gameOver(int player){

    }

    public void run(){
        chesses[newStep[0]][newStep[1]] = player;
        if(player != newPlayer && newPlayer != 0){
            int[] step = getStep(chesses);
            e.setChess(step, this.player);
        }

    }

}
