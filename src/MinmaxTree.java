import java.util.List;

public class MinmaxTree {
    private int DEPTH;
    private int[][] chesses;
    public static final int MAXN = 1<<28;
    public static final int MINN = -MAXN;
    private Node selectedNode;

    public MinmaxTree(int d, int[][] chesses){
        this.DEPTH = d;
        this.chesses = chesses;
    }
    public void initTree(int depth, int[][] chesses, Node node, int player){

        for(int i = 0; i < chesses.length; i++){
            for(int j = 0; j < chesses[i].length; j++){
                if(chesses[i][j] == 0){
                    int[][] newChesses = new int[chesses.length][chesses[i].length];
                    // deep copy chesses
                    for(int k = 0; k < newChesses.length; k++){
                        System.arraycopy(chesses[k], 0, newChesses[k], 0, newChesses.length);
                    }

                    newChesses[i][j] = player;
                    Node childNode = new Node(player, i, j, node);

                    node.addChildNode(childNode);

                    if(depth + 1 < DEPTH) {
                        // switch Player
                        int newPlayer = (player == 1) ? 2 : 1;
                        initTree(depth + 1, newChesses, childNode, newPlayer);
                    }
                }
            }
        }


    }

    public int maxmin(Node node, int depth, boolean isMaxinminizingPlayer, int alpha, int beta){
        if(node.isLeaf()) return node.getValue(chesses);

        if(isMaxinminizingPlayer){
            int bestVal = MINN;
            List<Node> childList = node.getChildList();
            for(int i = 0; i < childList.size(); i++){
                int value = maxmin(childList.get(i), depth + 1, false, alpha, beta);

                if(depth == 0 && bestVal < value && value > alpha){
                    this.selectedNode = childList.get(i);

                }
                bestVal = Math.max(bestVal,value);
                alpha = Math.max(alpha, bestVal);
                if(beta <= alpha) break;
            }
            return bestVal;
        }else {
            int bestVal = MAXN;
            List<Node> childList = node.getChildList();
            for(int i = 0; i < childList.size(); i++){
                int value = maxmin(childList.get(i), depth + 1, true, alpha, beta);

                bestVal = Math.min(value, bestVal);
                beta = Math.min(beta, bestVal);

                if(beta <= alpha) break;
            }
            return bestVal;
        }
    }

    public Node getSelectedNode(){
        return this.selectedNode;
    }
}
