package Controllers.admin;

import Models.FakeRepositori;
import Models.People;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AdminInfoList {

    @FXML
    TableView<People> tablePerson;

    @FXML
    TableColumn<People,String> nameColum;

    @FXML
    TableColumn<People,String> sonameColum;

    private People sendPeople;

    @FXML
    private void initialize() throws IOException {
        tablePerson.setItems(FakeRepositori.fakePeople);
        nameColum.setCellValueFactory(person -> person.getValue().rangProperty());
        sonameColum.setCellValueFactory(person -> person.getValue().sonameProperty().concat(" ").concat(person.getValue().nameProperty()));
    }


    public void presTable(MouseEvent mouseEvent) {
        if ((People) tablePerson.getSelectionModel().getSelectedItem() != null) {
            sendPeople = (People) tablePerson.getSelectionModel().getSelectedItem();
            // rutian nada nowue poly dly otobrajeniy;

        }
    }

    public void clicaddButton(ActionEvent actionEvent) {
        
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
