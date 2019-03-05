package Controllers.User;

import Models.Duty;
import Models.GeneratorDuty;
import Models.Mans;
import Models.People;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class UserDutyList {

    @FXML
    ComboBox<Mans> combobox;
    @FXML
    TableView<Duty> tablduty;
    @FXML
    TableColumn<People,String> columzaveren;
    @FXML
    TableColumn<People,String> columdate;
    @FXML
    TableColumn<People,String> columname;

    @FXML
    private void initialize(){
        combobox.setItems(FXCollections.observableArrayList(Mans.January, Mans.February, Mans.March, Mans.April, Mans.May, Mans.June, Mans.July, Mans.August, Mans.September, Mans.October, Mans.November, Mans.December ));
        combobox.setOnAction(event ->  tablduty.setItems(FXCollections.observableArrayList(GeneratorDuty.getListDutiMans(combobox.getValue()))));
    }

    public void prestable(MouseEvent mouseEvent) {

    }
}
