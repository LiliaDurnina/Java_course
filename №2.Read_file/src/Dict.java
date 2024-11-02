import java.io.*;

public class Dict {
    private final int[] count = new int[26 + 26];
    private final char[] letter = new char[26 + 26];

    public Dict() {
        for (int i = 0; i < 26 + 26; i++) {
            this.count[i] = 0;
            this.letter[i] = '#';
        }
    }

    public char[] getLetter() {
        return this.letter;
    }

    public int[] getCount(char[] letter) {
        return this.count;
    }

    private void addLetter(char ch) {
        if ('a' <= ch && ch <= 'z') {
            for (int i = 0; i < 26; i++) {
                if (count[i] == 0) {
                    letter[i] = ch;
                    count[i]++;
                    break;
                }
                if (letter[i] == ch) {
                    count[i]++;
                    break;
                }
            }
        } else {
            for (int i = 26; i < 26 + 26; i++) {
                if (count[i] == 0) {
                    letter[i] = ch;
                    count[i]++;
                    break;
                }
                if (letter[i] == ch) {
                    count[i]++;
                    break;
                }
            }
        }
    }

    public Dict makeDictFromFile(String fileName) throws FileNotFoundException {
        Dict dict = new Dict();
        try {
            InputStream fp = new FileInputStream(fileName);
            int bytesRead = fp.read();
            while (bytesRead != -1) {
                char ch = (char) bytesRead;
                if (Character.isLetter(ch) && ((ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A'))) {
                    dict.addLetter(ch);
                }
                bytesRead = fp.read();
            }
            fp.close();
            System.out.println("Словарь успешно составлен из файла " + fileName);

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return dict;

    }

    public void sendDictToFile(String fileName) throws FileNotFoundException {
        try {
            int flag = 0;
            //Добавление данных в конец указанного файла, без удаление имеющихся данных, с помощью 2-го параметра true
            OutputStream fp = new FileOutputStream(fileName, true);
            for (int i = 0; i < 26 + 26; i++) {
                if (this.count[i] != 0) {
                    flag = 1;
                    String line = this.letter[i] + ":" + this.count[i] + "\n";
                    fp.write(line.getBytes());
                }

            }
            if (flag == 0) {
                System.out.println("\nСловарь, который вы пытаетесь выгрузить - пуст");
            } else {
                System.out.println("\nСловарь успешно выгружен в файл " + fileName);
            }
            fp.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Dict dictFromFileToFile(String input, String output) throws FileNotFoundException {
        Dict dict = new Dict();
        dict = makeDictFromFile(input);
        dict.sendDictToFile(output);
        return dict;

    }


    public String toString() {
        String str = "{";
        int flag = 0;

        for (int i = 0; i < 26 + 26; i++) {
            if (count[i] != 0) {
                if (flag == 0) {
                    str = str + this.letter[i] + ":" + this.count[i];
                    flag++;
                } else {
                    str = str + ", " + this.letter[i] + ":" + this.count[i];

                }
            }
        }
        return str + "}";
    }


}
