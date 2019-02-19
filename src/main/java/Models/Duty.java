package Models;
import Models.FakeRepositori;
import Models.GeneratorDuty;
import Models.People;

import java.util.Date;

public class Duty {

    private int id;
    private People people;
    private Date date;
    private boolean certified;

    public Duty(){}

    public Duty(Date firsdata, int id) {
        this.date = firsdata;
        this.id = id;
        if(id > 0) {
            for (int i = 0; i < FakeRepositori.fakePeople.size(); i++) {
                if(id==FakeRepositori.fakePeople.get(i).getId()){
                    setPeople(FakeRepositori.fakePeople.get(i));
                    FakeRepositori.fakePeople.get(i).getListDuti().add(this);
                    break;
                }
            }
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if(id > 0) {
            for (int i = 0; i < FakeRepositori.fakePeople.size(); i++) {
                if(id==FakeRepositori.fakePeople.get(i).getId()){
                    setPeople(FakeRepositori.fakePeople.get(i));
                    FakeRepositori.fakePeople.get(i).getListDuti().add(this);
                    break;
                }
            }
        }
    }

    public Date getData() {
        return date ;
    }

    public void setData(Date firsdata) {
        this.date = firsdata;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
        this.id=people.getId();
        people.getListDuti().add(this);
    }

    @Override
    public String toString() {
        return "Duty{" +
                "id=" + id +"   "+certified+
                ", date=" + GeneratorDuty.dateFormat.format(date) +people.toString2()+'}';
    }
}
