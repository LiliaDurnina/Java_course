import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

import static java.time.temporal.ChronoUnit.YEARS;




public class FIO {
    private String Surname;
    private String Name;
    private String Patronymic;
    private int age;
    private String gender;



    public FIO(String str) throws InvalidInputException, InvalidDateException {
        StringTokenizer st = new StringTokenizer(str, " ");
        if (st.countTokens()!=4){
            throw new InvalidInputException("Error: неверное количество аргументов: "+st.countTokens()+" из 4!");
        }
        Surname = st.nextToken();
        Name = st.nextToken();
        Patronymic = st.nextToken();
        this.timeIsRight(st.nextToken());
        this.detGender();
    }

    public void outputInfo() {
        System.out.println(Surname + " " +  Character.toUpperCase(Name.charAt(0)) + "." +  Character.toUpperCase(Patronymic.charAt(0)) + ". " + gender + " " + age+" " + this.forAge());

    }


    private String forAge(){
        int lastNum = (int) this.age%10;

        if (this.age>=11 && this.age<=14){
            return "лет";
        }
        if (lastNum==1){
            return "год";
        }
        if (lastNum<=4 && lastNum>=2){
            return "года";
        }

        return "лет";

    }

    private void detGender(){

        String last= this.Patronymic.toLowerCase().substring(this.Patronymic.length()-1);

        switch (last){
            case "а":
                this.gender="ж.";
                break;
            case "ч":
                this.gender="м.";
                break;
            default:
                this.gender="-";
                break;
        }
    }

    public  Boolean timeIsRight(String str) throws InvalidDateException {
        String[] patterns = {"dd.MM.yyyy", "dd/MM/yyyy", "dd-MM-yyyy"};
        LocalDate now= LocalDate.now();

        for (String pattern : patterns) {
            try {
                LocalDate date = LocalDate.parse(str, DateTimeFormatter.ofPattern(pattern));
                if (date.format(DateTimeFormatter.ofPattern(pattern)).equals(str)){

                    if ((int) YEARS.between(date, now)>= 0){
                        age = (int) YEARS.between(date, now);
                        return true;
                    }
                }

            } catch (DateTimeParseException e) {

            }
        }
        throw new InvalidDateException("!Error: Дата введена неверно!");

    }

}
