package Controllers.admin;
import Models.Duty;
import Models.FakeRepositori;
import Models.Masage;
import Models.People;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    VBox chatBox;
    @FXML
    TextField lineText;
    @FXML
    JFXButton nacha;


    private People mesegPeople;
    private boolean writeAdmin=false;

    @FXML
    private void initialize(){
        table.setItems(FakeRepositori.fakePeople);
        columPerson.setCellValueFactory(t->t.getValue().rangProperty().concat(" ").concat(t.getValue().nameProperty().concat(" ").concat(t.getValue().sonameProperty())) );
        chatBox.getStyleClass().add("chatBox");

        table.setRowFactory( tv ->{
            TableRow<People> row =new TableRow<>();
            BooleanBinding critical =  Bindings.createBooleanBinding(()->{
                if(row.getItem()!=null &&  row.getItem().getMassenger().isIncoming() ){
                    System.out.println();
                    return true;
                }
                return false;
            },row.itemProperty());
            row.styleProperty().bind(Bindings.when(critical)
            .then("-fx-background-color: green;")
            .otherwise(""));
            return row;
        });

        nacha.getStyleClass().add("dontRead");
        System.out.println(nacha.getStyleClass());
      

//        if(!FakeRepositori.chaffMasanger.isIncoming()){  // ne rabotaet stil knopki admina
//            nach.setStyle("-fx-background-color: green");
//        }else{
//            System.out.println("dal");
//        }
    }


    @FXML
    public void changePeople(MouseEvent mouseEvent) {
        writeAdmin = false;
        if((People)table.getSelectionModel().getSelectedItem()!=null){
            Platform.runLater(() -> chatBox.getChildren().clear());
            mesegPeople = (People)table.getSelectionModel().getSelectedItem();
            label.setText(mesegPeople.getName()+" "+mesegPeople.getSoname());
            //table.setSelectionModel(null);
            mesegPeople.getMassenger().setIncoming(false);
            table.refresh();
            ArrayList<HBox> hBoxes = new ArrayList<>();
            for( Masage masage : mesegPeople.getMassenger().getMasageHistory() ){
                if(!masage.isStatus()){
                    wisiblAdminmasage(masage.getText());
                }else if (masage.isStatus()){
                    wisibleUserMasage(masage.getText());
                }
            }
            mesegPeople.getMassenger().setIncoming(false); // nada chitat ne
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
        if(!lineText.getText().trim().equals("")){

            if(mesegPeople!=null && !writeAdmin){
                wisiblAdminmasage(lineText.getText());
                mesegPeople.getMassenger().getMasageHistory().add(new Masage(lineText.getText(),false));
                mesegPeople.getMassenger().setOutcoming(true); //useru tre chitatu daa
            }
            if(writeAdmin){
                wisiblAdminmasage(lineText.getText());
            }
        }
        lineText.setText("");
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

    @FXML
    public void nachal(ActionEvent actionEvent) {
        writeAdmin = true;
        label.setText("Начальник");
//        nacha.getStyleClass().add("dontRead");
        System.out.println(nacha.getStyle());
//        nacha.setStyle("-fx-background-color: green;");
    }
}
