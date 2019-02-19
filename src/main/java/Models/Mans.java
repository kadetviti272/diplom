package Models;

public enum Mans {

    January(1,31,"/01/2019"),
    February(2,28,"/02/2019"),
    March(3,31,"/03/2019"),
    April(4,30,"/04/2019"),
    May(5,31,"/05/2019"),
    June(6,30,"/06/2019"),
    July(7,31,"/07/2019"),
    August(8,31,"/08/2019"),
    September(9,30,"/09/2019"),
    October(10,31,"/10/2019"),
    November(11,30,"/11/2019"),
    December(12,31,"/12/2019");

    int countmans;
    int countDeyMans;
    String countMansEar;

    Mans(int j, int i,String countMansEar) {
        this.countmans = j;
        this.countDeyMans = i;
        this.countMansEar = countMansEar;
    }

    public int getCountmans() {
        return countmans;
    }

    public void setCountmans(int countmans) {
        this.countmans = countmans;
    }

    public int getCountDeyMans() {
        return countDeyMans;
    }

    public void setCountDeyMans(int countDeyMans) {
        this.countDeyMans = countDeyMans;
    }

    public String getCountMansEar() {
        return countMansEar;
    }

    public void setCountMansEar(String countMansEar) {
        this.countMansEar = countMansEar;
    }

    @Override
    public String toString() {
        switch (countmans) {
            case 1:
                return "січень";
            case 2:
                return "лютий";
            case 3:
                return "березень";
            case 4:
                return "квітень";
            case 5:
                return "травень";
            case 6:
                return "червень";
            case 7:
                return "липень";
            case 8:
                return "серпень";
            case 9:
                return "вересень";
            case 10:
                return "жовтень";
            case 11:
                return "листопад";
            case 12:
                return "грудень";
            default:
                return "я олень";
        }
    }
}
