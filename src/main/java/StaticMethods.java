import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfTextArray;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class StaticMethods {

    public List<Mensuration> mensurationList;
    public float realVolts;
    public int n;

    public StaticMethods(List<Mensuration> mensurationList, float realVolts) {
        this.mensurationList = mensurationList;
        this.realVolts = realVolts;
        this.n = this.mensurationList.toArray().length;

        System.out.println("Object was created successfully");
    }

    public float arithmeticAverage() {
        float sum = 0;
        for(Mensuration mensuration: this.mensurationList)
            sum += mensuration.getVolts();

        return  sum/this.n;
    }

    public List<Error> getErrors() {
        List<Error> errors = new ArrayList<Error>();

        for (int i = 0; i < this.mensurationList.toArray().length; i++) {
            if ((i + 1) % 2 != 0 && (i + 1) < this.mensurationList.toArray().length) {
                float absolute = this.absoluteErrorPerHour(
                        this.mensurationList.get(i).getVolts(),
                        this.mensurationList.get(i + 1).getVolts());
                float relative = this.relativeErrorPerHour(absolute);

                errors.add(new Error(
                        absolute,
                        relative,
                        this.mensurationList.get(i).getTime()
                ));
            }
        }

        return errors;
    }

    public float absoluteErrorPerHour(float firstReading, float secondReading) {
        return (((firstReading + secondReading) / 2) - this.realVolts);
    }

    public float relativeErrorPerHour(float absoluteError) {
        return (absoluteError / this.realVolts) * 100;
    }

    public float median() {
        List<Mensuration> sortedList = new ArrayList<Mensuration>(this.mensurationList);
        //SortList
        sortedList.sort((m1, m2) -> (int) m1.sortBy(m2));

        if (this.n % 2 == 0) {
            return (sortedList.get((this.n / 2) - 1).getVolts() + sortedList.get(this.n / 2).getVolts()) / 2;
        } else {
            return sortedList.get((int) Math.ceil((n / 2))).getVolts();
        }
    }
    public double mean_desviation()
    {
        double var = this.arithmeticAverage();
        double suma= 0;
        double [] averages_desviation;
        averages_desviation = this.average_desviation();
        for (int i = 0; i < this.mensurationList.toArray().length; i++) {
             suma =+ averages_desviation[i];
        }
        return suma/n;
    }

    public double semi_interquartile()
    {
        List<Mensuration> sortedList = new ArrayList<Mensuration>(this.mensurationList);
        //SortList
        sortedList.sort((m1, m2) -> (int) m1.sortBy(m2));
        
        int q3, q1;
        
        q1 = (n + 1)/4;
        q3 = 3*(n+1)/4;
        
        return (sortedList.get(q3).getVolts() - sortedList.get(q1).getVolts())/2;
    }
    public double[] average_desviation()
    {
        double [] array;
        array = new double[this.mensurationList.toArray().length];
        for (int i = 0; i < this.mensurationList.toArray().length; i++) {
             array[i] = this.mensurationList.get(i).getVolts() - this.arithmeticAverage();
        }
        
        return array;
    }
    public double variance()
    {
        double var = this.median();
        double suma= 0;
        for (int i = 0; i < this.mensurationList.toArray().length; i++) {
             suma =+ Math.pow(this.mensurationList.get(i).getVolts() - var, 2);
        }
        return suma/n;
    }
    
    public double standar_deviation()
    {
        return Math.sqrt(this.variance());
    }
    
    public double coefficient_of_variation()
    {
        return (this.standar_deviation() / this.median());
    }
    public double range()
    {
         List<Mensuration> sortedList = new ArrayList<Mensuration>(this.mensurationList);
        //SortList
        sortedList.sort((m1, m2) -> (int) m1.sortBy(m2));
        
        return  sortedList.get((int) Math.ceil((n-1))).getVolts() - sortedList.get((int) Math.ceil((0))).getVolts() ;
    }

    public List<Frequency> getFrequencies() {
        List<Frequency> frequencies = new ArrayList<Frequency>();

        for(Mensuration mensuration: this.mensurationList) {

            boolean found = false;
            for (Frequency frequency: frequencies) {
                if(frequency.getEl() == mensuration.getVolts()){
                    found = true;
                    frequency.increment();
                    break;
                }
            }
            if (!found) {
                frequencies.add(new Frequency(mensuration.getVolts()));
            }

        }

        for (Frequency frequency: frequencies) {
            System.out.println("Frequency: " + frequency.el + " ,it was repeated " + frequency.frequency + " times");
        }


        return frequencies;
    }

    public Frequency getMoreRepeated() throws FileNotFoundException, MalformedURLException {
        List<Frequency> frequencies = this.getFrequencies();
        frequencies.sort((m1, m2) -> (int) m1.sortBy(m2));

        return frequencies.get(0);
    }

}
