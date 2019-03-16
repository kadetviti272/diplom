package Models;
import java.util.*;

public class Massenger {
    private boolean incoming;
    private boolean outcoming;


    private List<Masage> masageHistory;
    public Massenger(){
        masageHistory = new ArrayList<Masage>();
    }

    public boolean isIncoming() {
        return incoming;
    }

    public void setIncoming(boolean incoming) {
        this.incoming = incoming;
    }

    public boolean isOutcoming() {
        return outcoming;
    }

    public void setOutcoming(boolean outcoming) {
        this.outcoming = outcoming;
    }

    public List<Masage> getMasageHistory() {
        return masageHistory;
    }

    public void setMasageHistory(List<Masage> masageHistory) {
        this.masageHistory = masageHistory;
    }
}