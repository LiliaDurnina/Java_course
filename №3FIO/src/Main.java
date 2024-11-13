import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidInputException, InvalidDateException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите в строку через пробелы ФИО на русском языке и дату рождения ");
        String str = sc.nextLine();
        System.out.println(str);
        sc.close();
        try {
            FIO t = new FIO(str);
            t.outputInfo();
        }
        catch (InvalidInputException e) {
            System.err.println(e.getMessage());
        }
        catch (InvalidDateException e) {
            System.err.println(e.getMessage());
        }



    }
}