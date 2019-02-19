package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GeneratorDuty {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static ObservableList<Duty> GeneratorDutyMans(Mans mans ) throws ParseException {

        ObservableList<Duty> tempMansDuty = FXCollections.observableArrayList();
        for (int i = 1; i <= mans.getCountDeyMans(); ) {
            for (int j = 0; j < FakeRepositori.fakePeople.size() ; j++,i++){
                if(i>mans.getCountDeyMans()) break;
                Duty duty = new Duty(dateFormat.parse(Integer.toString(i).concat(mans.getCountMansEar())),0);
                duty.setPeople(FakeRepositori.fakePeople.get(j));
                tempMansDuty.add(duty);
            }
        }

        ArrayList<Duty> tt = getListDutiMans(mans);
        tt.clear();

        FakeRepositori.fakeDuty.addAll(tempMansDuty);
        return FXCollections.observableArrayList(tempMansDuty);
    }

    public static ArrayList<Duty> getListDutiMans(Mans mans){
        return (ArrayList<Duty>) FakeRepositori.fakeDuty.stream().filter(duty->duty.getData().getMonth()+1 == mans.getCountmans()).collect(Collectors.toList());
    }


}
