package Controllers.admin;

import Models.FakeRepositori;
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
import javafx.stage.Stage;

import java.io.IOException;

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
            final Stage primaryStage = new Stage();
            primaryStage.setOnCloseRequest(event -> {
                System.out.println("da zakrl");
            });
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Wievs/admin/infoperson.fxml"));
            fxmlLoader.setController(FakeRepositori.arrControler[0]);
            ((ChangeInfoPerson)FakeRepositori.arrControler[0]).setPeople(new People());
            Parent root = fxmlLoader.load();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 800 , 500));
            primaryStage.show();

    }
    
    public void clicdellbutton(ActionEvent actionEvent) {
         if((People)tablePerson.getSelectionModel().getSelectedItem()!=null) {
             People people = (People) tablePerson.getSelectionModel().getSelectedItem();
             for (int i = 0; i < people.getListDuti().size(); i++) {
                 people.getListDuti().get(i).setId(0);
             }
             FakeRepositori.fakePeople.remove(people);
         }
    }

    public void clicapdeit(ActionEvent actionEvent) {

    }


}
