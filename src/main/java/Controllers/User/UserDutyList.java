package Controllers.User;
import Models.Duty;
import Models.GeneratorDuty;
import Models.Mans;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class UserDutyList {

    @FXML
    ComboBox<Mans> combobox;
    @FXML
    TableView<Duty> tablduty;
    @FXML
    TableColumn<Duty, Boolean> columzaveren;
    @FXML
    TableColumn<Duty,String> columdate;
    @FXML
    TableColumn<Duty,String> columname;

    @FXML
    private void initialize(){
        combobox.setItems(FXCollections.observableArrayList(Mans.January, Mans.February, Mans.March, Mans.April, Mans.May, Mans.June, Mans.July, Mans.August, Mans.September, Mans.October, Mans.November, Mans.December ));
        combobox.setOnAction(event ->  tablduty.setItems(FXCollections.observableArrayList(GeneratorDuty.getListDutiMans(combobox.getValue()))));
        columzaveren.setCellValueFactory( t -> new SimpleBooleanProperty( t.getValue().isCertified()) );
        columzaveren.setCellFactory(t->new TableCell<Duty,Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item,empty);
                setText(empty?null:item?"затверджено":"незатверджено");
            }
        });
        columname.setCellValueFactory(t-> t.getValue().getPeople().nameProperty().concat(" ").concat(t.getValue().getPeople().sonameProperty()));
        columdate.setCellValueFactory( t -> new SimpleStringProperty( GeneratorDuty.dateFormat.format(t.getValue().getData())));

    }

    public void prestable(MouseEvent mouseEvent) {

    }
}
