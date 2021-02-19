import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public class Pdf {
    public void pdf(String Route){
    }
    public void  createPDF(List<Frequency> frequencies, List<Error> absoluteErrors, String Route, float media, Frequency moda, double desProm
    , double semi_interquartile, double varianza, double desvEstd, double coefVar, double rango, float promAri, double average_desviation[]) throws FileNotFoundException, MalformedURLException
    {
        float columnsEstadisticos []  = {50f, 100f};
        Table tableEstadisticos = new Table(columnsEstadisticos);

        tableEstadisticos.addCell(new Cell().add("Moda"));
        Paragraph modap = new Paragraph(String.valueOf(moda));
        tableEstadisticos.addCell(new Cell().add(modap));

        tableEstadisticos.addCell(new Cell().add("Mediana"));
        Paragraph mediap = new Paragraph(String.valueOf(media));
        tableEstadisticos.addCell(new Cell().add(mediap));

        tableEstadisticos.addCell(new Cell().add("Media"));
        Paragraph mediaChida = new Paragraph(String.valueOf(promAri));
        tableEstadisticos.addCell(new Cell().add(mediaChida));

        tableEstadisticos.addCell(new Cell().add("Rango"));
        Paragraph rangeP = new Paragraph(String.valueOf(rango));
        tableEstadisticos.addCell(new Cell().add(rangeP));

        tableEstadisticos.addCell(new Cell().add("Desviacion Promedio"));
        Paragraph desvEstp = new Paragraph(String.valueOf(desProm));
        tableEstadisticos.addCell(new Cell().add(desvEstp));

        tableEstadisticos.addCell(new Cell().add("Desviacion Estandar"));
        Paragraph desvEstchida = new Paragraph(String.valueOf(desvEstd));
        tableEstadisticos.addCell(new Cell().add(desvEstchida));

        tableEstadisticos.addCell(new Cell().add("Varianza"));
        Paragraph variP = new Paragraph(String.valueOf(varianza));
        tableEstadisticos.addCell(new Cell().add(variP));

        tableEstadisticos.addCell(new Cell().add("Coeficiente de varianza"));
        Paragraph coefVarp = new Paragraph(String.valueOf(coefVar));
        tableEstadisticos.addCell(new Cell().add(coefVarp));

        tableEstadisticos.addCell(new Cell().add("semi-intercuartil"));
        Paragraph cuarPara = new Paragraph(String.valueOf(semi_interquartile));
        tableEstadisticos.addCell(new Cell().add(cuarPara));


        float columns []  = {50f, 50f, 50f, 50f, 50f, 50f};
        Table table = new Table(columns);
        table.addCell(new Cell().add("Frequencia"));
        table.addCell(new Cell().add("Repeticiones"));
        table.addCell(new Cell().add("Frequencia"));
        table.addCell(new Cell().add("Repeticiones"));
        table.addCell(new Cell().add("Frequencia"));
        table.addCell(new Cell().add("Repeticiones"));

        //String imgPath = "src/main/resources/images/uaa.png";
        //ImageData imgData = ImageDataFactory.create(imgPath);
        //Image photo = new Image(imgData);

        String ErrPhotoPath = Route + "/Error.png";
        String HistPhotoPath = Route + "/histograma.png";
        ImageData ErrImgData = ImageDataFactory.create(ErrPhotoPath);
        ImageData HistImgData = ImageDataFactory.create(HistPhotoPath);
        Image ErrPhoto = new Image(ErrImgData);
        Image HistPhoto = new Image(HistImgData);

        String integreantes = "Luis Fernando Regalado de la Rosa \nCésar Contreras Fiscal \nFernando Valentín Acevedo Peres \nSaúl Leonardo Rodriguez \nSebatian Ramirez Díaz";

        String text = "A continuacion se presentan los cálculos resultantes de los datos recabados del archivo ingresado.";


        String ErrorSub = "A continuacion presentamos los errores calculados con los datos ingresados";
        String FrecText = "Tabla de frecuencias";
        String DesMedText = "Desviación media";

        Paragraph ErrS = new Paragraph(ErrorSub);
        Paragraph paragraph = new Paragraph(text);
        Paragraph Frecparagraph = new Paragraph(FrecText);
        Paragraph intePara = new Paragraph(integreantes);
        Paragraph DesvMedParagraph = new Paragraph(DesMedText);
        PdfWriter writer = new PdfWriter(Route + "/Repote.pdf");

        PdfDocument doc = new PdfDocument(writer);
        doc.addNewPage();

        Document file = new Document(doc);

        //file.add(photo);
        file.add(new Paragraph(new String("Reporte de calculos estadisticos")));
        file.add(intePara);

        file.add(paragraph);

        file.add(tableEstadisticos);
        file.add(Frecparagraph);

        for (Frequency frequency: frequencies){
            Paragraph data1 = new Paragraph(String.valueOf(frequency.getFrequency()));
            Paragraph data2 = new Paragraph(String.valueOf(frequency.getEl()));
            table.addCell(new Cell().add(data1));
            table.addCell(new Cell().add(data2));

        }

        float columns2 []  = {50f, 100f, 100f, 50f, 100f, 100f};
        Table table2 = new Table(columns2);
        table2.addCell(new Cell().add("Hora"));
        table2.addCell(new Cell().add("Error absoluto"));
        table2.addCell(new Cell().add("Error relativo"));
        table2.addCell(new Cell().add("Hora"));
        table2.addCell(new Cell().add("Error absoluto"));
        table2.addCell(new Cell().add("Error relativo"));

        for (Error error: absoluteErrors){
            Paragraph hour = new Paragraph(String.valueOf(error.getHour()));
            Paragraph ab = new Paragraph(String.valueOf(error.getAbsolute()));
            Paragraph rel = new Paragraph(String.valueOf(error.getRelative()));
            table2.addCell(new Cell().add(hour));
            table2.addCell(new Cell().add(ab));
            table2.addCell(new Cell().add(rel));

        }

        float columnscalculos []  = {100f};
        Table table4 = new Table(columnscalculos);
        table4.addCell(new Cell().add("dato"));

        for (double item: average_desviation){
            Paragraph x = new Paragraph(String.valueOf(item));
            table4.addCell(new Cell().add(x));

        }
        file.add(table);
        file.add(DesvMedParagraph);
        file.add(table4);
        file.add(HistPhoto);
        file.add(ErrS);
        file.add(table2);
        file.add(ErrPhoto);
        file.close();
    }
}
