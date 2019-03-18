package Controllers.Cheff;

import Models.FakeRepositori;
import Models.Masage;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.IOException;


public class CheffMasanger {

    Stage stage;
    @FXML
    VBox chatBox;
    @FXML
    TextField textfield;

    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize(){
        if(FakeRepositori.chaffMasanger!=null){
            for (int i = 0; i < FakeRepositori.chaffMasanger.getMasageHistory().size(); i++) {
                if(FakeRepositori.chaffMasanger.getMasageHistory().get(i).isStatus()){
                    cheffMasage(FakeRepositori.chaffMasanger.getMasageHistory().get(i).getText());
                }else{
                    admMasag(FakeRepositori.chaffMasanger.getMasageHistory().get(i).getText());
                }
            }
        }
    }

    private void admMasag(String masage){
        Text text=new Text(masage);
        text.setFill(Color.WHITE);
        text.getStyleClass().add("message");
        TextFlow tempFlow=new TextFlow();

        tempFlow.getChildren().add(text);
        tempFlow.setMaxWidth(200);
        TextFlow flow=new TextFlow(tempFlow);
        HBox hbox=new HBox(12);

        text.setFill(Color.WHITE);
        tempFlow.getStyleClass().add("tempFlow");
        flow.getStyleClass().add("textFlow");
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(flow);

        hbox.getStyleClass().add("hbox");
        Platform.runLater(() -> chatBox.getChildren().addAll(hbox));
    }

    private void cheffMasage (String masage){
        Text text=new Text(masage);
        text.setFill(Color.WHITE);
        text.getStyleClass().add("message");
        TextFlow tempFlow=new TextFlow();
        Text txtName=new Text("Адмін" + ":\n");
        txtName.getStyleClass().add("txtName");
        tempFlow.getChildren().add(txtName);
        tempFlow.getChildren().add(text);
        tempFlow.setMaxWidth(200);
        TextFlow flow=new TextFlow(tempFlow);
        HBox hbox=new HBox(12);
        text.setFill(Color.BLACK);
        tempFlow.getStyleClass().add("tempFlowFlipped");
        flow.getStyleClass().add("textFlowFlipped");
        chatBox.setAlignment(Pos.TOP_LEFT);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(flow);
        hbox.getStyleClass().add("hbox");
        Platform.runLater(() -> chatBox.getChildren().addAll(hbox));
    }


    String gsonstr;
    public void send(ActionEvent actionEvent) throws IOException {
        if (!textfield.getText().trim().equals("")){
            admMasag(textfield.getText());
            FakeRepositori.chaffMasanger.getMasageHistory().add(new Masage(textfield.getText(),false));
            FakeRepositori.chaffMasanger.setIncoming(false); // admu tre chutatu da
            FakeRepositori.chaffMasanger.setOutcoming(true); // meni tre chutatu ne
            cheffMasage(textfield.getText());
            textfield.setText("");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        gsonstr=new Gson().toJson(FakeRepositori.chaffMasanger);
                        System.out.println("dadadadadadad");
                        new FileOutputStream("C:\\Users\\home-pc\\IdeaProject\\diplom\\src\\main\\resources\\cheffadmin\\masanger.txt").write(gsonstr.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
