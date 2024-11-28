import java.util.*;


public class Person {
    private int id;
    private String name;
    private String company;
    private String username;
    private String email;
    private String address;
    private String zip;
    private String state;
    private String country;
    private String phone;


    public Person(int id, String name, String company, String username, String email, String address, String zip, String state, String country, String phone) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.username = username;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.state = state;
        this.country = country;
        this.phone = phone;

    }

    @Override
    public String toString() {
        String s = "";
        return "Person\n"
                + "id: " + this.id
                + "\nname: " + this.name
                + "\nusername: " + this.username
                + "\ncompany: " + this.company
                + "\nemail: " + this.email
                + "\naddress: " + this.address
                + "\nzip: " + this.zip
                + "\nstate: " + this.state
                + "\ncountry: " + this.country
                + "\nphone: " + this.phone;


    }


}
