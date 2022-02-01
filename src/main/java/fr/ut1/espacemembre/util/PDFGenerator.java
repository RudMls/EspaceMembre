package fr.ut1.espacemembre.util;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class PDFGenerator {

    public static void ficheAppel(HttpServletResponse response, String realPath) {

        ArrayList<Cell> headCells = new ArrayList<>(Arrays.asList(
                new Cell().add("Etudiant"),
                new Cell().add("Présent"),
                new Cell().add("Abscent"),
                new Cell().add("Justificatif")
        ));
        ArrayList<ListItem> listItems = new ArrayList<>(Arrays.asList(
                new ListItem("Enseignant : Nathalie VALLES"),
                new ListItem("Cours : Démarche de developpement Agile"),
                new ListItem("Date : 25/01/2022"),
                new ListItem("Durée : 2h")
        ));
        List list = new List();

        listItems.forEach(list::add);

        Table table = new Table(4);

        // add style
        headCells.forEach(headCell -> {
            headCell.setFontColor(new DeviceRgb(245, 245, 245));
            headCell.setBackgroundColor(Color.DARK_GRAY);
            headCell.setBorder(Border.NO_BORDER);
            headCell.setTextAlignment(TextAlignment.CENTER);
        });

        Document document = null;
        response.setContentType("application/pdf");

        try (PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()))) {

            ImageData ut1ImageData = ImageDataFactory.create(realPath + "image/ut1.png");
            ImageData checkImageData = ImageDataFactory.create(realPath + "image/check.png");
            Image ut1Image = new Image(ut1ImageData)
                    .setWidth(65)
                    .setHeight(65)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);
            Image checkImage = new Image(checkImageData)
                    .setWidth(10)
                    .setHeight(10)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);

            document = new Document(pdfDoc);
            document.add(ut1Image);

            // Titre du document
            document.add(new Paragraph(new Text("Fiche d'appel").setBold()));
            // Informations du document
            document.add(list);
            // En-tête du document
            headCells.forEach(table::addCell);

            for (int i = 0; i < 10; i++) {
                table.addCell(
                        new Cell()
                                .add("Etudiant " + i)
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                );
                table.addCell(
                        new Cell()
                                .add(checkImage)
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
                table.addCell(
                        new Cell()
                                .add(checkImage)
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
                table.addCell(
                        new Cell()
                                .add(checkImage)
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(i % 2 == 0 ? Color.WHITE : new DeviceRgb(245, 245, 245))
                                .setTextAlignment(TextAlignment.CENTER)
                                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
            }

            document.add(table);

        } catch (Exception e) {
            e.printStackTrace();
//            request.setAttribute("general_error", e.getMessage());
//            request.getRequestDispatcher("/").forward(request, response);
        } finally {
            assert document != null;
            document.close();
        }

    }


}
