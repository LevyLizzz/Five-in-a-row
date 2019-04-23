import java.util.ArrayList;

public class Event{
    public ArrayList<Listener> listeners;

    public Event(){
        ArrayList<Listener> listeners = new ArrayList<Listener>();
        this.listeners = listeners;
    }

    public void setChess(int step[], int player){
        for(Listener l: listeners){
            l.showChange(step, player);
        }
    }

    public void gameOver(int player){
        for(Listener l: listeners){
            l.gameOver(player);
        }
    }

    public void register(Listener l){
        listeners.add(l);
    }

    public void clear(){
        listeners.clear();
    }
}
