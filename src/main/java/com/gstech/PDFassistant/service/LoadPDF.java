package com.gstech.PDFassistant.service;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LoadPDF {

    private final File PDF_DIRECTORY = new File("doc/teste.pdf");

    public void parsePDF() {

        try (InputStream stream = new FileInputStream(PDF_DIRECTORY)) {

            AutoDetectParser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();

            parser.parse(stream, handler, metadata);
            String content = handler.toString();

            saveToTxt(content, "doc/teste.txt");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveToTxt(String content, String fileName) {
        try {
            Files.write(
                    Path.of(fileName),
                    content.getBytes(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
