package Controllers.admin;

import Models.*;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdmGenerator {


    @FXML
    JFXComboBox<Mans> comboBox;
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
    @FXML
    CheckBox algoritm;

    TableView.TableViewSelectionModel<Duty> selectionModel;

    @FXML
    private void initialize() throws ParseException, IOException {
        comboBox.setItems(FXCollections.observableArrayList(Mans.January, Mans.February, Mans.March, Mans.April, Mans.May, Mans.June, Mans.July, Mans.August, Mans.September, Mans.October, Mans.November, Mans.December ));

        comboBox.setOnAction(event ->  tablenaryd.setItems(FXCollections.observableArrayList(GeneratorDuty.getListDutiMans(comboBox.getValue()))));
        algoritm.setOnAction(event ->{
            if(selectionModel.getSelectedItem()!=null && algoritm.isSelected()){
                tableChange.setItems(FXCollections.observableArrayList(helpTable(selectionModel.getSelectedItem())));
            }else {
                tableChange.setItems(FakeRepositori.fakePeople);
            }
        } );
        columname.setCellValueFactory(t-> t.getValue().getPeople().nameProperty().concat(" ").concat(t.getValue().getPeople().sonameProperty()));
        columzaveren.setCellValueFactory( t -> new SimpleBooleanProperty(t.getValue().isCertified()));
        columzaveren.setCellFactory(t->new TableCell<Duty,Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item,empty);
                setText(empty?null:item?"затверджено":"незатверджено");
            }
        });

        comboBox.getSelectionModel().select(Calendar.getInstance().getTime().getMonth());

        columdate.setCellValueFactory( t -> new SimpleStringProperty( GeneratorDuty.dateFormat.format(t.getValue().getData())));

        //c2.setCellValueFactory(t-> t.getValue().nameProperty().concat(" ").concat(t.);

        columChange.setCellValueFactory(t-> t.getValue().nameProperty().concat(" ").concat(t.getValue().sonameProperty()));
        selectionModel = tablenaryd.getSelectionModel();



        selectionModel.selectedItemProperty().addListener(new ChangeListener<Duty>() {
            @Override
            public void changed(ObservableValue<? extends Duty> observable, Duty oldValue, Duty newValue) {
                if(newValue==null){
                    tableChange.setItems(null);
                }
                else{
                    if(algoritm.isSelected()){
                        tableChange.setItems(FXCollections.observableArrayList(helpTable(newValue)));
                        System.out.println("dfdfdfdf");
                    }
                    else{
                        tableChange.setItems(FakeRepositori.fakePeople);
                        System.out.println("uuuu");

                    }
                }
            }
        });
        tablenaryd.setItems(FXCollections.observableArrayList(GeneratorDuty.getListDutiMans(comboBox.getValue())));
    }

    private List<People> helpTable(Duty duty){
        Date date = duty.getDate();
        return FakeRepositori.fakePeople.stream()
                .filter(p->p.birsDay(date))
                .filter(p->p.dontVacatoin(date))
                .filter(p->p.dontTooDay(date))
                .sorted(GeneratorDuty::mysoort)
                .collect(Collectors.toList());
    }

    public void generButton(ActionEvent actionEvent) throws ParseException {
        tablenaryd.setItems(GeneratorDuty.GeneratorDutyMans(comboBox.getValue()));
    }

    @FXML
    public void changeDuty(ActionEvent actionEvent) {
        System.out.println(tablenaryd.getSelectionModel().getSelectedItem().getPeople().getName());
        System.out.println(tableChange.getSelectionModel().getSelectedItem().getName());
        new Thread(()->{
            RaportGenerator.raportChange(tablenaryd.getSelectionModel().getSelectedItem(),
                    tablenaryd.getSelectionModel().getSelectedItem().getPeople(),
                    tableChange.getSelectionModel().getSelectedItem());
        }).start();

        tablenaryd.getSelectionModel().getSelectedItem().getPeople().getListDuti().remove(tablenaryd.getSelectionModel().getSelectedItem()); // udalaym narad u persona
        tablenaryd.getSelectionModel().getSelectedItem().setPeople(tableChange.getSelectionModel().getSelectedItem());
        tablenaryd.refresh();
        tableChange.refresh();
    }

}
