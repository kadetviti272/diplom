package Controllers.User;

import Models.FakeRepositori;
import Models.Masage;
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


import java.io.IOException;
import java.util.Map;

import static Models.FakeRepositori.clinDb;
import static Models.FakeRepositori.wraitDb;

public class UserMassenger {

    @FXML
    VBox chatBox;
    @FXML
    TextField lineText;

    @FXML
    private void initialize(){
        System.out.println();
        System.out.println(FakeRepositori.autorizadPeopl.getMassenger().toString());
        if( FakeRepositori.autorizadPeopl !=null && FakeRepositori.autorizadPeopl.getMassenger().getMasageHistory()!=null ){
            for (Masage i : FakeRepositori.autorizadPeopl.getMassenger().getMasageHistory()){
                if(i.isStatus()){
                    wisiblMaymasage(i.getText());
                }else if(!i.isStatus()){
                    wisiblAbminMasage(i.getText());
                }
            }
        }
        chatBox.getStyleClass().add("chatBox");
    }


    public void sendMasage(ActionEvent actionEvent) {
        if(!lineText.getText().trim().equals("")){
            wisiblMaymasage(lineText.getText());
            FakeRepositori.autorizadPeopl.getMassenger().getMasageHistory().add(new Masage( lineText.getText() , true));
            lineText.setText("");
        }
        System.out.println("++"+lineText.getText());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (FakeRepositori.fclin);
//                    FakeRepositori.fclin = true;
//                    clinDb();
//                    wraitDb();
//                    FakeRepositori.fclin = false;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
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
