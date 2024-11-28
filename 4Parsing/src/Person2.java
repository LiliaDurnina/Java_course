public class Person2 {
    private int userId;
    private int id;
    private String title;
    private Boolean completed;


    public Person2(int userId, int id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @Override
    public String toString() {
        String s = "";
        return "Person2\n"
                + "userId: " + this.userId
                + "\nid: " + this.id
                + "\ntitle: " + this.title
                + "\ncompleted: " + this.completed;
    }

}
