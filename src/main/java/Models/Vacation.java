package Models;

import Models.People;

import java.util.Date;
import java.util.Objects;

public class Vacation {

    private int id;
    private People people;
    private Date firstData;
    private Date lastData;

    public Vacation() {

    }

    public Vacation(Date firstData, Date lastData) {
        if(firstData.getTime()>lastData.getTime()){
            this.lastData = firstData;
            this.firstData = lastData;
        }else{
            this.firstData = firstData;
            this.lastData = lastData;
        }
    }

    public Vacation(Date firstData, Date lastData,People people) {
        if(firstData.getTime() > lastData.getTime()){
            this.lastData = firstData;
            this.firstData = lastData;
            this.id = people.getId();
            this.people = people;
        }else{
            this.firstData = firstData;
            this.lastData = lastData;
            this.id = people.getId();
            this.people = people;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation vacation = (Vacation) o;
        return Objects.equals(firstData, vacation.firstData) &&
                Objects.equals(lastData, vacation.lastData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstData, lastData);
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFirstData() {
        return firstData;
    }

    public void setFirstData(Date firstData) {

        if(lastData!=null && firstData.getTime()>lastData.getTime()){
            this.firstData = this.lastData;
            this.lastData=firstData;
        }else {
            this.firstData = firstData;
        }

    }

    public Date getLastData() {
        return lastData;
    }

    public void setLastData(Date lastData) {
        if(firstData!=null && firstData.getTime()> lastData.getTime()){
            this.lastData = this.firstData;
            this.firstData =lastData;
        }else {
            this.lastData = lastData;
        }
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id=" + id +
                ", firstData=" + firstData +
                ", lastData=" + lastData +
                '}';
    }
}