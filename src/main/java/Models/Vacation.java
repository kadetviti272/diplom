package Models;

import Models.People;

import java.util.Date;

public class Vacation {

    private int id;
    private People people;
    private Date firstData;
    private Date lastData;

    public Vacation() {

    }

    public Vacation(Date firstData, Date lastData) {
        this.firstData = firstData;
        this.lastData = lastData;

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
        this.firstData = firstData;
    }

    public Date getLastData() {
        return lastData;
    }

    public void setLastData(Date lastData) {
        this.lastData = lastData;
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