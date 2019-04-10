package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.PERSIST_STORE;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GeneratorDuty {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static ObservableList<Duty> GeneratorDutyMans(Mans mans) throws ParseException {

        ArrayList<Duty> tt = getListDutiMans(mans); // Удаляем все наряды за месяци зколекцыи персонов
        FakeRepositori.fakeDuty.removeAll(tt);

        ObservableList<Duty> tempMansDuty = FXCollections.observableArrayList(); // создаем новые пустные наряды
        for (int i = 1; i <= mans.getCountDeyMans() ; i++) {
            tempMansDuty.add(new Duty(dateFormat.parse(Integer.toString(i).concat(mans.getCountMansEar())),0));
        }

        FakeRepositori.fakeDuty.addAll(tempMansDuty);

        for (int i = 0; i <tempMansDuty.size() ; i++) {

            Date date = tempMansDuty.get(i).getDate();
            tempMansDuty.get(i).setPeople(
                    FakeRepositori.fakePeople.stream()
                    .filter(p -> p.removeDeyDutyPerson(date))
                //  .filter(p -> p.removeDutiMansPerson(mans)).collect(Collectors.toList()).stream() //test proishol
                    .filter(p -> p.chesked(1)).collect(Collectors.toList()).stream()
                    .filter(p -> p.birsDay(date)).collect(Collectors.toList()).stream()
                    .filter(p -> p.chesked(2)).collect(Collectors.toList()).stream()
                    .filter(p -> p.dontVacatoin(date)).collect(Collectors.toList()).stream()
                    .filter(p -> p.chesked(3)).collect(Collectors.toList()).stream()
                    .filter(p -> p.dontTooDay(date)).collect(Collectors.toList()).stream()  //its work
                    .filter(p -> p.chesked(0))
                    .sorted((p1, p2) -> mysoort(p1,p2) )
                    .filter(p->p.chesked(55))
                    .collect(Collectors.toList())
                    .get(0)
            );
        }
        return FXCollections.observableArrayList(tempMansDuty);
    }

    public static int mysoort(People people, People people2){
        double coff1=0;
        double  coff2=0;

        int temp;
        for (int i = 0; i < people.getListDuti().size() ; i++) {
            temp = people.getListDuti().get(i).getData().getDay();
            switch (temp) {
                case 0:
                    coff1 += 1.3;
                    break;
                case 5:
                    coff1 += 1.4;
                    break;
                case 6:
                    coff1 += 1.6;
                    break;
                default:
                    coff1 += 1;
            }
        }


        for (int i = 0; i < people2.getListDuti().size() ; i++) {
            temp = people2.getListDuti().get(i).getData().getDay();
            switch (temp) {
                case 0:
                    coff2 += 1.3;
                    break;
                case 5:
                    coff2 += 1.4;
                    break;
                case 6:
                    coff2 += 1.6;
                    break;
                default:
                    coff2 += 1;
            }
        }
        return ((int)coff1-(int)coff2);
    }

    public static ArrayList<Duty> getListDutiMans(Mans mans){
        return (ArrayList<Duty>) FakeRepositori.fakeDuty.stream().filter(duty->duty.getData().getMonth()+1 == mans.getCountmans()).sorted((p1,p2) -> (int) (p1.getDate().getTime()-p2.getDate().getTime())).collect(Collectors.toList());
    }

    // nada Budet perenesti ato ne dilgen on tot but
    public static void RemovePerson( People peopleRemov){
        ArrayList<Duty> tt = (ArrayList<Duty>) FakeRepositori.fakeDuty.stream().filter(duty -> duty.getPeople()== peopleRemov).collect(Collectors.toList());
        FakeRepositori.fakeDuty.removeAll(tt);
        FakeRepositori.fakePeople.removeAll(peopleRemov);
    }

    public static void RemovePerson2( People peopleRemov){
        ArrayList<Duty> tt = (ArrayList<Duty>) FakeRepositori.fakeDuty.stream().filter(duty -> duty.getPeople()== peopleRemov).collect(Collectors.toList());
        for (int i = 0; i <tt.size() ; i++) {
            tt.get(i).setId(0);
            tt.get(i).setPeople(new People());
            tt.get(i).setCertified(false);
        }
        FakeRepositori.fakePeople.removeAll(peopleRemov);
    }
}
