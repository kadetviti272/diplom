package Controllers.User;

import Models.FakeRepositori;
import Models.People;
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


import java.util.Map;

public class UserMassenger {

    @FXML
    VBox chatBox;
    @FXML
    TextField lineText;

    @FXML
    private void initialize(){
        if(FakeRepositori.autorizadPeopl!=null && FakeRepositori.autorizadPeopl.getMassenger().getMasageHistoru()!=null){
            for (Map.Entry<Boolean,String> i : FakeRepositori.autorizadPeopl.getMassenger().getMasageHistoru().entrySet()) {
                if(i.getKey()){
                    wisiblMaymasage(i.getValue());
                }else if(i.getKey()){
                    wisiblAbminMasage(i.getValue());
                }
            }
        }
        chatBox.getStyleClass().add("chatBox");
    }

    public void sendMasage(ActionEvent actionEvent) {
        if(!lineText.getText().trim().equals("")){
            wisiblMaymasage(lineText.getText());
        }
        System.out.println("++"+lineText.getText());
    }



    private void wisiblMaymasage(String masage){

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

    private void wisiblAbminMasage(String masage){
        Text text=new Text(masage);
        text.setFill(Color.WHITE);
        text.getStyleClass().add("message");
        TextFlow tempFlow=new TextFlow();
        Text txtName=new Text("Адмнін "+":\n");
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
}
