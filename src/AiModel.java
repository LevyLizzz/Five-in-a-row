//import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by LevyLi on 2019/4/5.
 */
public class AiModel implements Config{

    private int player;
    private static int[][] chesses;
    private static int searchDeep = 4;
    public static final int MAXN = 1<<28;
    public static final int MINN = -MAXN;
//    private static HashSet<Point> toJudge=new HashSet<Point>();

    public AiModel(){

    }

    public int[][] bestVal(){

    }

    public void weigh(){

    }

    class Node{
        private int value;
        protected int[][] chesses;
        private int player;
        protected Node parentNode;
        protected List<Node> childList;

        public Node(){
            initChildList();
        }

        public Node(int[][] chesses, int player){
            initChildList();
            this.chesses = chesses;
            this.player = player;
        }

        public void initChildList(){
            if(childList == null) childList = new ArrayList<Node>();
        }

        public void setValue(int val){
            this.value = val;
        }

        public int getValue(){
            return this.value;
        }

        public boolean isLeaf(){
            if(childList == null){
                return true;
            } else {
                if(childList.isEmpty()){
                    return true;
                } else {
                    return false;
                }
            }
        }

        public void addChildNode(Node node){
            initChildList();
            childList.add(node);
        }

        public List<Node> getChildList(){
            return childList;
        }

        public Node getParentNode(){
            return parentNode;
        }

        public List<Node> getAncestors() {
            List<Node> ancestors = new ArrayList<>();
            Node parentNode = this.getParentNode();
            if (parentNode == null) {
                return ancestors;
            } else {
                ancestors.add(parentNode);
                ancestors.addAll(parentNode.getAncestors());
                return ancestors;
            }
        }
    }

    public int getWeigh(Node node){
        return 0;
    }

    class MinmaxTree{
        private int DEPTH = 3;
        private int value;
        private int[][] chesses;

        public MinmaxTree(int[][] chesses, int player){
            this.chesses = chesses;
            int depth = 0;
            Node root = new Node();
            initTree(0, chesses, root, player);
        }

        public void initTree(int depth, int[][] chesses, Node node, int player){
            if(depth < DEPTH){
                for(int i = 0; i < chesses.length; i++){
                    for(int j = 0; j < chesses[i].length; j++){
                        if(chesses[i][j] == 0){
                            int[][] newChesses = new int[chesses.length][chesses[i].length];
                            // deep copy chesses
                            for(int k = 0; k < newChesses.length; j++){
                                System.arraycopy(chesses[k], 0, newChesses[k], 0, newChesses.length);
                            }

                            newChesses[i][j] = player;
                            Node childNode = new Node(newChesses, player);
                            node.addChildNode(childNode);
                            // switch player
                            int newPlayer = (player % 2) == 1 ? 2:1;
                            initTree(depth + 1, newChesses, node, newPlayer);
                        }
                    }
                }
            }

        }

        public int maxmin(Node node, int depth, boolean isMaxinminizingPlayer, int alpha, int beta){
            if(node.isLeaf()) return node.getValue();
            if(isMaxinminizingPlayer){
                int bestVal = -999999;
                List<Node> childList = node.getChildList();
                for(int i = 0; i < childList.size(); i++){
                    int value = maxmin(childList(i), depth + 1, false, alpha, beta);
                    bestVal = Math.max(bestVal, value);
                    alpha = Math.max(alpha, bestVal);
                    if(beta <= alpha) break;
                return bestVal;
                }
            }else {
                int bestVal = 999999;
                List<Node> childList = node.getChildList();
                for(int i = 0; i < childList.size(); i++){
                    int value = 
                }
            }
        }

    }
}
