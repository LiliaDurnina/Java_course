import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {

            List<Person> persons = getPeople(Person.class, "https://fake-json-api.mock.beeceptor.com/users");
            System.out.println("Reading from 'https://fake-json-api.mock.beeceptor.com/users'");
            for (Person person : persons) {

                System.out.println("№" + (persons.indexOf(person) + 1) + " " + person + "\n");
            }

            List<Person2> persons2 = getPeople(Person2.class, "https://dummy-json.mock.beeceptor.com/todos");
            System.out.println("Reading from 'https://dummy-json.mock.beeceptor.com/todos'");
            for (Person2 person : persons2) {

                System.out.println("№" + (persons2.indexOf(person) + 1) + " " + person + "\n");
            }


        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private static <T> List<T> getPeople(Class<T> clazz, String url) throws IOException {

        URLConnection connection = new URL(url).openConnection();
        connection.connect();
        InputStream in = connection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();


        Gson gson = new Gson();
        Type clazzListType = TypeToken.getParameterized(List.class, clazz).getType();
        List<T> persons = gson.fromJson(jsonBuilder.toString(), clazzListType);
        return persons;
    }

}
