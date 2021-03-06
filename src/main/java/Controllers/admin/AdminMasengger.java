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

    TableView.TableViewSelectionModel<People> selectionModel;
 //   private People mesegPeople;
    private boolean writeAdmin=false;

    // chaf masage status false
    // user masage status true
    private int sortForTable(People p1,People p2){
        if(p1.getMassenger().isIncoming())
            return -1;
        else return 1;
    }

    @FXML
    private void initialize(){
        selectionModel=table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(event ->{
            writeAdmin = false;
            if(selectionModel.getSelectedItem()!=null){
                Platform.runLater(() -> chatBox.getChildren().clear());
                label.setText(selectionModel.getSelectedItem().getName()+" "+selectionModel.getSelectedItem().getSoname());
                selectionModel.getSelectedItem().getMassenger().setIncoming(false);
                table.refresh();
                for( Masage masage : selectionModel.getSelectedItem().getMassenger().getMasageHistory() ){
                    if(!masage.isStatus()){
                        wisiblAdminmasage(masage.getText());
                    }else if (masage.isStatus()){
                        wisibleUserMasage(masage.getText());
                    }
                }
                selectionModel.getSelectedItem().getMassenger().setIncoming(false); // nada chitat ne
            }
        });

        table.setItems(FakeRepositori.fakePeople.sorted(this::sortForTable));
        columPerson.setCellValueFactory(t->t.getValue().rangProperty().concat(" ").concat(t.getValue().nameProperty().concat(" ").concat(t.getValue().sonameProperty())) );
        chatBox.getStyleClass().add("chatBox");

        table.setRowFactory( tv ->{
            TableRow<People> row =new TableRow<>();
            BooleanBinding critical =  Bindings.createBooleanBinding(()->{
                if(row.getItem()!=null &&  row.getItem().getMassenger().isIncoming() ){
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

        if(FakeRepositori.chaffMasanger.isIncoming())
            nacha.setStyle("-fx-background-color: green;");
        else nacha.setStyle("-fx-background-color: #4059a9;");

//        if(!FakeRepositori.chaffMasanger.isIncoming()){  // ne rabotaet stil knopki admina
//            nach.setStyle("-fx-background-color: green");
//        }else{
//            System.out.println("dal");
//        }
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
            wisiblAdminmasage(lineText.getText());
            if(selectionModel.getSelectedItem()!=null && !writeAdmin){
                selectionModel.getSelectedItem().getMassenger().getMasageHistory().add(new Masage(lineText.getText(),false));
                selectionModel.getSelectedItem().getMassenger().setOutcoming(true); //useru tre chitatu daa
                selectionModel.getSelectedItem().getMassenger().setIncoming(false);
            }
            if(writeAdmin){
                FakeRepositori.chaffMasanger.getMasageHistory().add(new Masage(lineText.getText(),false)); // otprav admin
                FakeRepositori.chaffMasanger.setOutcoming(true); //chef nadda chetat
                FakeRepositori.chaffMasanger.setIncoming(false); //adminu chetat uge ne nada;
            }
        }
        lineText.setText("");
    }

    private void wisibleUserMasage(String masage){
        Text text=new Text(masage);
        text.setFill(Color.WHITE);
        text.getStyleClass().add("message");
        TextFlow tempFlow=new TextFlow();
        Text txtName;
        try {
            txtName=new Text(selectionModel.getSelectedItem().getName().concat(" ").concat(selectionModel.getSelectedItem().getSoname()) + ":\n");
        }catch (Exception e){
            txtName=new Text("Начальник" + ":\n");
        }
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
        Platform.runLater(() -> chatBox.getChildren().clear());
        writeAdmin = true;
        label.setText("Начальник");
        nacha.setStyle("-fx-background-color: #4059a9;");
//        nacha.getStyleClass().add("dontRead");
        for( Masage masage : FakeRepositori.chaffMasanger.getMasageHistory() ){
            if(!masage.isStatus()){
                wisiblAdminmasage(masage.getText());
            }else if (masage.isStatus()){
                wisibleUserMasage(masage.getText());
            }
        }
        FakeRepositori.chaffMasanger.setIncoming(false);
        System.out.println(nacha.getStyle());
    }
}
