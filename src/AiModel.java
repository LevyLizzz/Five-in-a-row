public class AiModel implements Config{

    private int player;
    private static int[][] chesses;
    private static int searchDeep = 3;
    private static Node root;
    public static final int MAXN = 1<<28;
    public static final int MINN = -MAXN;
    private MaxminTree tree;

    public AiModel(int player, int[][] chesses){
        this.player = player;
        this.chesses = chesses;
        this.tree = new MaxminTree(searchDeep, chesses);
        this.root = new Node();
        tree.initTree(0, chesses, root, player);
    }


    public int[] getChess(){
        int value = tree.maxmin(root, 0, true, MINN, MAXN);
        Node node = tree.getSelectedNode();
        int[] chesse = {node.x, node.y};
        return chesse;
    }

}
