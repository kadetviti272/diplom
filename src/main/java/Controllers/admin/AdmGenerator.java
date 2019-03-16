package Controllers.admin;

import Models.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    TableColumn<People,String> columChange;
    @FXML
    TableView <People> tableChange;

    TableView.TableViewSelectionModel<Duty> selectionModel;

    @FXML
    private void initialize() throws ParseException, IOException {

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

        columdate.setCellValueFactory( t -> new SimpleStringProperty( GeneratorDuty.dateFormat.format(t.getValue().getData())));

        columChange.setCellValueFactory(t-> t.getValue().nameProperty().concat(" ").concat(t.getValue().sonameProperty()));
        selectionModel = tablenaryd.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Duty>() {
            @Override
            public void changed(ObservableValue<? extends Duty> observable, Duty oldValue, Duty newValue) {
                if(newValue==null){
                    System.out.println("nuul");
                    tableChange.setItems(null);
                }
                else{
                    tableChange.setItems(FakeRepositori.fakePeople);
                    System.out.println("000");
                }
            }
        });
    }

    public void generButton(ActionEvent actionEvent) throws ParseException {
        tablenaryd.setItems(GeneratorDuty.GeneratorDutyMans(comboBox.getValue()));
    }

    public void changeDuty(ActionEvent actionEvent) {

        System.out.println(tablenaryd.getSelectionModel().getSelectedItem().getPeople().getName());
        System.out.println(tableChange.getSelectionModel().getSelectedItem().getName());
        tablenaryd.getSelectionModel().getSelectedItem().setPeople(tableChange.getSelectionModel().getSelectedItem());
        tablenaryd.refresh();
    }

}
