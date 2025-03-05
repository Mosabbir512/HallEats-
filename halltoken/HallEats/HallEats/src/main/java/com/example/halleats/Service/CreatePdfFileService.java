package com.example.halleats.Service;

import com.example.halleats.Model.Token;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class CreatePdfFileService {
    public void createPdf(Token token) {

        String filePdf = "C:/Users/MD MOSABBIR/Downloads/MyToken.pdf";
        //C:\Users\MD MOSABBIR\Downloads

        try {
            PdfWriter writer = new PdfWriter(filePdf);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            Color textColor = new DeviceRgb(255, 0, 0);
            PdfFont font = PdfFontFactory.createFont("Helvetica-Bold", "Cp1252", true);
            Paragraph tokenInfo = new Paragraph()
                    .setFont(font)
                    .setFontSize(14)
                    .setFontColor(Color.BLACK)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(10)
                    .add("")
                    .add("Consumer: " + token.getName() + "\n")
                    .add("Identity: "+token.getIid()+"\n")
                    .add("HallName: " + token.getHallName() + "\n")
                    .add("Lunch Tokens: " + token.getLunch() + "\n")
                    .add("Dinner Tokens: " + token.getDinner() + "\n")

                    .add("Usable exclusively on :" + token.getDate()+"\n")
                    .add("TokenCode: "+token.getTid());
             document.add(tokenInfo);
             document.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
