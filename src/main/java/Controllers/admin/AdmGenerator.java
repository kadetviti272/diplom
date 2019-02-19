package Controllers.admin;

import Models.GeneratorDuty;
import Models.Duty;
import Models.Mans;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.text.ParseException;
public class AdmGenerator {

    @FXML
    ComboBox<Mans> comboBox;
    @FXML
    TableView<Duty> tablenaryd;
    @FXML
    TableColumn<Duty, String> columname;
    @FXML
    TableColumn<Duty, String> columdate;
    @FXML
    TableColumn<Duty, Boolean> columzaveren;




    @FXML
    private void initialize() throws ParseException {

        comboBox.setItems(FXCollections.observableArrayList(Mans.January, Mans.February, Mans.March, Mans.April, Mans.May, Mans.June, Mans.July, Mans.August, Mans.September, Mans.October, Mans.November, Mans.December ));
        comboBox.setOnAction(event ->  tablenaryd.setItems(FXCollections.observableArrayList(GeneratorDuty.getListDutiMans(comboBox.getValue()))));

        columname.setCellValueFactory(t-> t.getValue().getPeople().nameProperty().concat(" ").concat(t.getValue().getPeople().sonameProperty()));
        columzaveren.setCellValueFactory( t -> new SimpleBooleanProperty(t.getValue().isCertified()));
        columzaveren.setCellFactory(t->new TableCell<Duty,Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item,empty);
                setText(empty?null:item?"затверджено":"незатверджено");
            }
        });

        columdate.setCellValueFactory( t -> new SimpleStringProperty( GeneratorDuty.dateFormat.format(t.getValue().getData()) ));

    }

    public void generButton(ActionEvent actionEvent) throws ParseException {
            tablenaryd.setItems(GeneratorDuty.GeneratorDutyMans(comboBox.getValue()));
    }

    public void prestable(MouseEvent mouseEvent) {

    }

    public void zamenabutton(ActionEvent actionEvent) {

    }
}
