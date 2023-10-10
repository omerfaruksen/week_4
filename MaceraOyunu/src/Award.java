public class Award {
    private String name;
    private int id;
    public static Award[] awards() {
        Award[] awardList = new Award[3];
        awardList[0] = new Award("Yemek ", 1);
        awardList[1] = new Award("Odun ", 2);
        awardList[2] = new Award("Su ", 3);

        return awardList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Award(String name, int id) {
        this.name = name;
        this.id = id;
    }


}