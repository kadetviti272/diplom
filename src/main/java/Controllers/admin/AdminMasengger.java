package Controllers.admin;
import Models.FakeRepositori;
import Models.People;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static Models.FakeRepositori.clinDb;
import static Models.FakeRepositori.wraitDb;


public class AdminMasengger {

    @FXML
    TableView<People> table;
    @FXML
    TableColumn<People,String> columPerson;
    @FXML
    Label label;
    @FXML
    Button sendButton;
    @FXML
    VBox chatBox;
    @FXML
    TextField lineText;

    private People mesegPeople;

    @FXML
    private void initialize(){
        table.setItems(FakeRepositori.fakePeople);
        columPerson.setCellValueFactory(t->t.getValue().rangProperty().concat(" ").concat(t.getValue().nameProperty().concat(" ").concat(t.getValue().sonameProperty())) );
        chatBox.getStyleClass().add("chatBox");
    }

    @FXML
    public void changePeople(MouseEvent mouseEvent) {
        if((People)table.getSelectionModel().getSelectedItem()!=null){
            Platform.runLater(() -> chatBox.getChildren().clear());
            mesegPeople = (People)table.getSelectionModel().getSelectedItem();
            label.setText(mesegPeople.getName()+" "+mesegPeople.getSoname());


            ArrayList<HBox> hBoxes = new ArrayList<>();
            for(Map.Entry<Boolean,String> map : mesegPeople.getMassenger().getMasageHistoru().entrySet() ){
                if(!map.getKey()){
                    wisiblAdminmasage(map.getValue());
                }else if (map.getKey()){
                    wisibleUserMasage(map.getValue());
                }
            }
        }
    }

    private void wisiblAdminmasage(String masage){

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

    @FXML
    public void sendMasage(ActionEvent actionEvent) {
        lineText.setText(lineText.getText().trim());
        if(!lineText.equals("") && mesegPeople!=null ){
            wisiblAdminmasage(lineText.getText());
            mesegPeople.getMassenger().getMasageHistoru().put(false,lineText.getText());
        }
        lineText.setText("");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (FakeRepositori.fclin);
                    FakeRepositori.fclin = true;
                    clinDb();
                    wraitDb();
                    FakeRepositori.fclin = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void wisibleUserMasage(String masage){
        Text text=new Text(masage);
        text.setFill(Color.WHITE);
        text.getStyleClass().add("message");
        TextFlow tempFlow=new TextFlow();
        Text txtName=new Text(mesegPeople.getName().concat(" ").concat(mesegPeople.getSoname()) + ":\n");
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
