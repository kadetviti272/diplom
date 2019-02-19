package Controllers.admin;

import Models.FakeRepositori;
import Models.People;
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


    @FXML
    private void initialize() throws IOException {
        tablePerson.setItems(FakeRepositori.fakePeople);
        nameColum.setCellValueFactory(person -> person.getValue().rangProperty());
        sonameColum.setCellValueFactory(person -> person.getValue().sonameProperty().concat(" ").concat(person.getValue().nameProperty()));
    }


    public void presTable(MouseEvent mouseEvent) {

    }

    public void clicaddButton(ActionEvent actionEvent) {
        
    }
    
    public void clicdellbutton(ActionEvent actionEvent) {
        
    }

    public void clicapdeit(ActionEvent actionEvent) {
    }
}
