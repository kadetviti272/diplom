package Controllers.admin;

import Models.Duty;
import Models.FakeRepositori;
import Models.GeneratorDuty;
import Models.People;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
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



    private People sendPeople;

    private RedactPerson redactPerson = new RedactPerson();
    @FXML
    private void initialize() throws IOException {
        tablePerson.setItems(FakeRepositori.fakePeople);
        nameColum.setCellValueFactory(person -> person.getValue().rangProperty());
        sonameColum.setCellValueFactory(person -> person.getValue().sonameProperty().concat(" ").concat(person.getValue().nameProperty()));
    }

    private void initLabel(){
        nameLabel.setText(sendPeople.getName());
        sonameLabel.setText(sendPeople.getSoname());
        birsdayLabel.setText("NET POLLY NADA POPRAVIT");
        posadaLabel.setText("nada dodelat");
        vzvanLabel.setText(sendPeople.getRang());
        String test = "24/09/1999 - 24/09/1999 \n\r 24/09/1999 - 24/09/1999 \n\r 24/09/1999 - 24/09/1999";
        hollidayLabel.setText(test);
    }


    public void presTable(MouseEvent mouseEvent) {
        if ((People) tablePerson.getSelectionModel().getSelectedItem() != null) {
            sendPeople = (People) tablePerson.getSelectionModel().getSelectedItem();
            // rutian nada nowue poly dly otobrajeniy;
          //  System.out.println(sendPeople.toString2());
            System.out.println( sendPeople.getName() + " "+ sendPeople.getRang());
            initLabel();
        }
    }

    public void clicaddButton(ActionEvent actionEvent) throws IOException {

    }
    
    public void clicdellbutton(ActionEvent actionEvent) {
         if((People)tablePerson.getSelectionModel().getSelectedItem()!=null) {
             GeneratorDuty.RemovePerson((People)tablePerson.getSelectionModel().getSelectedItem());
         }
    }

    public void clicapdeit(ActionEvent actionEvent) throws IOException {
        showWindow("/Wievs/admin/RedctorPanelAdmin.fxml",sendPeople);
    }

    private void showWindow (String URL,People people) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(URL));
        ((RedactPerson)FakeRepositori.arrControler[0]).setStage(primaryStage).setPeople(people);
        fxmlLoader.setController(FakeRepositori.arrControler[0]);
        Parent root = fxmlLoader.load();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(new Scene(root,700,400));
        primaryStage.showAndWait();
    }
}
