package Models;

public class Massenger {
    private String text = "";
    private boolean incoming;
    private boolean outcoming;

    public Massenger(String text, boolean incoming, boolean outcomin){
        this.incoming = incoming;
        this.outcoming = outcomin;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}
