import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node {
    private int player;
    protected boolean isRoot;
    protected int x;
    protected int y;
    protected Node parentNode;
    protected List<Node> childList;
    private Map<String, Integer> weighMap;

    public Node(){
        initChildList();
        this.isRoot = true;
    }

    public Node(int player, int x, int y, Node parentNode){
        this.parentNode = parentNode;
        initChildList();
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public void initWeigh(int player){
        this.weighMap = new HashMap<String, Integer>();
        if(player == 1){
            weighMap.put("11111", 99999);
            weighMap.put("01111", 9999);
            weighMap.put("11110", 9999);
            weighMap.put("1111", 4999);
            weighMap.put("10111", 4999);
            weighMap.put("11011", 4999);
            weighMap.put("11101", 4999);
            weighMap.put("0111", 999);
            weighMap.put("1110", 999);
            weighMap.put("1011", 499);
            weighMap.put("1101", 499);
            weighMap.put("10101", 499);
            weighMap.put("111", 499);
            weighMap.put("110", 99);
            weighMap.put("101", 49);
            weighMap.put("1001", 49);
            weighMap.put("10001", 49);
            weighMap.put("011", 99);
            weighMap.put("11", 49);
            weighMap.put("10", 4);
            weighMap.put("01", 4);
            weighMap.put("1", 1);
            weighMap.put("22222", -100000);
            weighMap.put("02222", -10000);
            weighMap.put("22220", -10000);
            weighMap.put("2222", -5000);
            weighMap.put("20222", -5000);
            weighMap.put("22022", -5000);
            weighMap.put("22202", -5000);
            weighMap.put("0222", -1000);
            weighMap.put("2220", -1000);
            weighMap.put("2022", -500);
            weighMap.put("2202", -500);
            weighMap.put("20202", -500);
            weighMap.put("222", -500);
            weighMap.put("220", -100);
            weighMap.put("202", -100);
            weighMap.put("2002", -100);
            weighMap.put("20002", -100);
            weighMap.put("022", -100);
            weighMap.put("22", -50);
            weighMap.put("20", -5);
            weighMap.put("02", -5);
            weighMap.put("2", -2);
        }else {
            weighMap.put("22222", 100000);
            weighMap.put("02222", 10000);
            weighMap.put("22220", 10000);
            weighMap.put("2222", 5000);
            weighMap.put("0222", 1000);
            weighMap.put("2220", 1000);
            weighMap.put("222", 500);
            weighMap.put("220", 100);
            weighMap.put("022", 100);
            weighMap.put("22", 50);
            weighMap.put("20", 5);
            weighMap.put("02", 5);
            weighMap.put("2", 1);
            weighMap.put("11111", -100000);
            weighMap.put("01111", -10000);
            weighMap.put("11110", -10000);
            weighMap.put("1111", -5000);
            weighMap.put("0111", -1000);
            weighMap.put("1110", -1000);
            weighMap.put("111", -500);
            weighMap.put("110", -100);
            weighMap.put("011", -100);
            weighMap.put("11", -50);
            weighMap.put("10", -5);
            weighMap.put("01", -5);
            weighMap.put("1", -1);
        }
    }

    public void initChildList(){
        if(childList == null) childList = new ArrayList<Node>();
    }

    // caculate the weigh
    public int getValue(int[][] inputChesses){
        List<String> chesseRows = new ArrayList<String>();
        List<Node> ancestors = getAncestors();

        int[][] newChesses = new int[inputChesses.length][inputChesses.length];
        // deep copy chesses
        for(int k = 0; k < newChesses.length; k++){
            System.arraycopy(inputChesses[k], 0, newChesses[k], 0, newChesses.length);
        }

        for(int i = 0; i < ancestors.size(); i++){
            // whether deep colne?
            Node ancestor = ancestors.get(i);
            newChesses[ancestor.x][ancestor.y] = ancestor.player;
        }
        newChesses[x][y] = player;

        // convert row chesses into a string
        // warning!! make sure chesses.length == chesses[i].length, otherwise, do another loop
        for(int i = 0; i < newChesses.length; i++){
            String horizontalRow = "";
            String verticalRow = "";
            for(int j = 0; j < newChesses[i].length; j++){
                horizontalRow += newChesses[i][j];
                verticalRow += newChesses[j][i];
            }
            chesseRows.add(horizontalRow);
            chesseRows.add(verticalRow);

        }
        // eliminate the triangle area of 4 corners, size as 1/2 * 4 * 4
        for (int  i = 4;i < 2*newChesses.length - 5;i++) {
            String obliqueRow = "";
            for (int j = 0; j < newChesses.length; j++) {
                if ((i - j) >= 0 && (i - j) < newChesses.length) {
                    obliqueRow += newChesses[j][i-j];

                }
            }
            chesseRows.add(obliqueRow);
        }
        for(int i = 5 - newChesses.length; i < newChesses.length - 4; i++){
            String obliqueRow = "";
            for(int j = 0; j < newChesses.length; j++){
                if((i + j) >= 0 && (i + j ) < newChesses.length){
                    obliqueRow += newChesses[j][i+j];
                }
            }
            chesseRows.add(obliqueRow);
        }

//        for (int  i = 0;i < 2*newChesses.length - 1;i++) {
//            String obliqueRow = "";
//            for (int j = 0; j < newChesses.length; j++) {
//                if ((i - j) >= 0 && (i - j) < newChesses.length) {
//                    obliqueRow += newChesses[j][i-j];
//
//                }
//            }
//            chesseRows.add(obliqueRow);
//        }
//        for(int i = 0 - newChesses.length - 1; i < newChesses.length; i++){
//            String obliqueRow = "";
//            for(int j = 0; j < newChesses.length; j++){
//                if((i + j) >= 0 && (i + j ) < newChesses.length){
//                    obliqueRow += newChesses[j][i+j];
//                }
//            }
//            chesseRows.add(obliqueRow);
//        }
        // fill up map of weigh
        initWeigh(this.player);

        int value = 0;

        for(String chesseRow : chesseRows){

            for(Map.Entry<String, Integer> entry : weighMap.entrySet()){
                String key = entry.getKey();
                int length = key.length();
                int count = 0;
                for(int i = 0; i < chesseRow.length() - length + 1; i++){
                    if(chesseRow.substring(i, i + length).equals(key)){
                        count++;
                    }
                }
                value += count * entry.getValue();
            }
//            System.out.println(chesseRow);
        }
        return value;
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
        if (parentNode == null || parentNode.isRoot) {
            return ancestors;
        } else {
            ancestors.add(parentNode);
            ancestors.addAll(parentNode.getAncestors());
            return ancestors;
        }
    }

    public int[] getChess(){
        int[] pos = {x ,y};
        return pos;
    }
}

