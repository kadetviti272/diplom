package Controllers.admin;

import Models.Duty;
import Models.FakeRepositori;
import Models.GeneratorDuty;
import Models.People;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.IOException;
import java.util.ArrayList;

public class AdminInfoList {

    @FXML
    TableView<People> tablePerson;
    @FXML
    TableColumn<People,String> nameColum;
    @FXML
    TableColumn<People,String> sonameColum;
    @FXML
    Label nameLabel;
    @FXML
    Label sonameLabel;
    @FXML
    Label birsdayLabel;
    @FXML
    Label posadaLabel;
    @FXML
    Label vzvanLabel;
    @FXML
    Label hollidayLabel;
    @FXML
    HBox test;
    @FXML
    Label lCall;
    @FXML
    Label hardLabel;

   // private People sendPeople;

    private RedactPerson redactPerson = new RedactPerson();
    TableView.TableViewSelectionModel<People> selectionModel;


    @FXML
    private void initialize() throws IOException {
        if(FakeRepositori.autorizadPeopl != null){
            test.setVisible(false);
            tablePerson.setRowFactory( tv ->{
                TableRow<People> row =new TableRow<>();
                BooleanBinding critical =  Bindings.createBooleanBinding(()->{
                    if(row.getItem()!=null &&  row.getItem().getId()==FakeRepositori.autorizadPeopl.getId() ){
                        return true;
                    }
                    return false;
                },row.itemProperty());
                row.styleProperty().bind(Bindings.when(critical)
                        .then("-fx-background-color: green;")
                        .otherwise(""));
                return row;
            });
            tablePerson.setItems(FakeRepositori.fakePeople.sorted(this::sortForTable));
        }else
            tablePerson.setItems(FakeRepositori.fakePeople);

        nameColum.setCellValueFactory(person -> person.getValue().rangProperty());
        sonameColum.setCellValueFactory(person -> person.getValue().sonameProperty().concat(" ").concat(person.getValue().nameProperty()));

        selectionModel = tablePerson.getSelectionModel();
        selectionModel.selectedItemProperty().addListener( event ->{
            if (selectionModel.getSelectedItem()!=null)
            initLabel();
            //sendPeople = selectionModel.getSelectedItem();
        });

    }

    private int sortForTable(People p1,People p2){
        if(p1.getId() == FakeRepositori.autorizadPeopl.getId())
            return -1;
        else return 1;
    }

    private void initLabel(){
        nameLabel.setText(selectionModel.getSelectedItem().getName());
        sonameLabel.setText(selectionModel.getSelectedItem().getSoname());
        birsdayLabel.setText(GeneratorDuty.dateFormat.format(selectionModel.getSelectedItem().getdBirsday()));
        posadaLabel.setText("nada dodelat");
        vzvanLabel.setText(selectionModel.getSelectedItem().getRang());
        String test = "";
        for (int i = 0; i <selectionModel.getSelectedItem().getListVakation().size() ; i++) {
          test+=GeneratorDuty.dateFormat.format(selectionModel.getSelectedItem().getListVakation().get(i).getFirstData())+" - "+ GeneratorDuty.dateFormat.format(selectionModel.getSelectedItem().getListVakation().get(i).getLastData())+"\r";
        }

        hollidayLabel.setText(test);
        lCall.setText(selectionModel.getSelectedItem().getCall());
        posadaLabel.setText(selectionModel.getSelectedItem().getPosition());
    }


    public void clicaddButton(ActionEvent actionEvent) throws IOException {
        showWindow("/Wievs/admin/RedctorPanelAdmin.fxml",null);
    }
    
    public void clicdellbutton(ActionEvent actionEvent) {
         if((People)tablePerson.getSelectionModel().getSelectedItem()!=null) {
             GeneratorDuty.RemovePerson((People)tablePerson.getSelectionModel().getSelectedItem());
         }
    }

    public void clicapdeit(ActionEvent actionEvent) throws IOException {
        if(selectionModel.getSelectedItem()!=null)
        showWindow("/Wievs/admin/RedctorPanelAdmin.fxml",selectionModel.getSelectedItem());
    }

    private void showWindow (String URL,People people) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(URL));
        if(people!=null){
            ((RedactPerson)FakeRepositori.arrControler[0]).setStage(primaryStage).setPeople(people);
        }else {
            ((RedactPerson)FakeRepositori.arrControler[0]).setStage(primaryStage).setPeople(new People());
        }

        fxmlLoader.setController(FakeRepositori.arrControler[0]);
        Parent root = fxmlLoader.load();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 651,453));
        primaryStage.showAndWait();
    }
}
