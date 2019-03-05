package Models;

import javafx.beans.binding.MapBinding;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Massenger {
    private boolean incoming;
    private boolean outcoming;
    private Map<Boolean,String> masageHistoru;

    public Massenger(){
        masageHistoru = new HashMap<>();
    }

    public Map<Boolean, String> getMasageHistoru() {
        return masageHistoru;
    }

    public void setMasageHistoru(Map<Boolean, String> masageHistoru) {
        this.masageHistoru = masageHistoru;
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

    @Override
    public String toString() {
        return "Massenger{" +
                "incoming=" + incoming +
                ", outcoming=" + outcoming +
                ", masageHistoru=" + masageHistoru +
                '}';
    }
}
