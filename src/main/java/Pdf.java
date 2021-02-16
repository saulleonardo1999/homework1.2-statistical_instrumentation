import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfTextArray;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public class Pdf {
    public void pdf(String Route){
    }
    public void  createPDF(List<Frequency> frequencies, List<Error> absoluteErrors, String Route, float media, Frequency moda) throws FileNotFoundException, MalformedURLException
    {
        float columns []  = {50f, 50f, 50f, 50f, 50f, 50f};
        Table table = new Table(columns);
        table.addCell(new Cell().add("Frequencia"));
        table.addCell(new Cell().add("Repeticiones"));
        table.addCell(new Cell().add("Frequencia"));
        table.addCell(new Cell().add("Repeticiones"));
        table.addCell(new Cell().add("Frequencia"));
        table.addCell(new Cell().add("Repeticiones"));

        String imgPath = "images//uaa.png";
        ImageData imgData = ImageDataFactory.create(imgPath);
        Image photo = new Image(imgData);

        String ErrPhotoPath = Route + "/Error.png";
        String HistPhotoPath = Route + "/histograma.png";
        ImageData ErrImgData = ImageDataFactory.create(ErrPhotoPath);
        ImageData HistImgData = ImageDataFactory.create(HistPhotoPath);
        Image ErrPhoto = new Image(ErrImgData);
        Image HistPhoto = new Image(HistImgData);

        String text = "A continuacion se presentan los cálculos resultantes de los datos recabados del archivo ingresado.";
        String modaText = "La moda calculada para los datos ingresados es de " + moda;
        String mediaText = "La media calculada para los datos ingresados es de " + media;
        String ErrorSub = "A continuacion presentamos los errores calculados con los datos ingresados";

        Paragraph ErrS = new Paragraph(ErrorSub);
        Paragraph modaT = new Paragraph(modaText);
        Paragraph mediaT = new Paragraph(mediaText);
        Paragraph paragraph = new Paragraph(text);
        PdfWriter writer = new PdfWriter(Route + "/Repote.pdf");

        PdfDocument doc = new PdfDocument(writer);
        doc.addNewPage();

        Document file = new Document(doc);

        file.add(photo);
        file.add(paragraph);
        file.add(modaT);
        file.add(mediaT);
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
        file.add(table);
        file.add(HistPhoto);
        file.add(ErrS);
        file.add(table2);
        file.add(ErrPhoto);
        file.close();
    }
}
