import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        float realVolts = 127.00F;

        List<Mensuration> mensList = new ArrayList<Mensuration>();
        mensList.add(new Mensuration(130.81F, 1, "0:00:00"));
        mensList.add(new Mensuration(130.00F, 2, "0:30:00"));
        mensList.add(new Mensuration(130.00F, 3, "1:00:00"));
        mensList.add(new Mensuration(130.00F, 4, "1:30:00"));
        mensList.add(new Mensuration(129.00F, 5, "2:00:00"));
        mensList.add(new Mensuration(129.65F, 6, "2:30:00"));
        mensList.add(new Mensuration(129.78F, 7, "3:00:00"));
        mensList.add(new Mensuration(129.50F, 8, "3:30:00"));
        mensList.add(new Mensuration(129.50F, 9, "4:00:00"));
        mensList.add(new Mensuration(128.00F, 10, "4:30:00"));

        StaticMethods statical = new StaticMethods(mensList, realVolts);


        System.out.println("Arithmetic Average: " + statical.arithmeticAverage() + " volts");
        System.out.println("\nErrors: ");
        List<Error> absoluteErrors = statical.getErrors();

        for (Error error: absoluteErrors)
            System.out.println("Hour: " + error.hour + "\nAE: " + error.getAbsolute() + " volts" + "\nRE: " + error.getRelative() + "%");

        System.out.println("\nMedian: " + statical.median() + " volts");

        Frequency moreRepeated = statical.getMoreRepeated();

        System.out.println("More Repeated: " + moreRepeated.getEl() + " volts, Frequency: " + moreRepeated.getFrequency());
    }
}
