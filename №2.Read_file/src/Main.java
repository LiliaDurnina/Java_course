import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Dict dict = new Dict();
        Scanner sc = new Scanner(System.in, "UTF-8");
        System.out.println("Введите файл для которого хотите составить словарь: ");
        String fileInput = sc.nextLine();
        System.out.println("file for input: " + fileInput);
        System.out.println("Введите файл в который хотите выгрузить словарь: ");
        String fileOutput = sc.nextLine();
        System.out.println("file for output: " + fileOutput);
        sc.close();

        dict = dict.dictFromFileToFile(fileInput, fileOutput );
        System.out.println(dict);


    }
}