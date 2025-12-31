package com.gstech.pdfAssistant.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ReadPDF {



    public String extractTextPDF(MultipartFile file){

        try(PDDocument document = Loader.loadPDF(file.getBytes())){

//            if (document.isEncrypted()) {
//                throw new IllegalStateException("PDF is not encrypted");
//            }

            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);

            StringBuilder fullText = new StringBuilder();

            int totalPages = document.getNumberOfPages();

            for (int i = 1; i <= totalPages; i++) {
                stripper.setStartPage(i);
                stripper.setEndPage(i);

                fullText.append("\n\n=== Página ")
                        .append(i)
                        .append(" ===\n\n");

                fullText.append(stripper.getText(document));
            }

            return fullText.toString();

        } catch (IOException e) {
            throw new RuntimeException("Error reading PDF file", e);
        }
    }
}
