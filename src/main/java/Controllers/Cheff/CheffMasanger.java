package Controllers.Cheff;

import Models.FakeRepositori;
import Models.Masage;
import Models.Massenger;
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
        }else
            FakeRepositori.chaffMasanger = new Massenger();
    }

    private void cheffMasage (String masage){
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

    private void  admMasag (String masage){
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

    public void send(ActionEvent actionEvent) throws IOException {
        if (!textfield.getText().trim().equals("")){
            FakeRepositori.chaffMasanger.getMasageHistory().add(new Masage(textfield.getText(),true));
            FakeRepositori.chaffMasanger.setIncoming(true); // admu tre chutatu da
            FakeRepositori.chaffMasanger.setOutcoming(false); // meni tre chutatu ne
            cheffMasage(textfield.getText());
            textfield.setText("");
        }
    }
}
