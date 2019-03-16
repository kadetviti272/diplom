package Models;

public class Masage {

    private String text;
    private boolean status;

    public Masage() {
    }

    public Masage(String text, boolean status) {
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
