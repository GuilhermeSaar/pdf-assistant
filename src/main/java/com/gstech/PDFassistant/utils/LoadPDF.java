package com.gstech.PDFassistant.utils;

import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.extractor.ParsingEmbeddedDocumentExtractor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LoadPDF {

    private final File PDF_DIRECTORY = new File("doc/teste.pdf");

    public void parsePDF() {

        try (InputStream stream = new FileInputStream(PDF_DIRECTORY)) {

            AutoDetectParser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();

            PDFParserConfig config = new PDFParserConfig();
            config.setEnableAutoSpace(true);
            config.setExtractInlineImages(false);
            config.setSortByPosition(true);
            config.setSuppressDuplicateOverlappingText(true);

            context.set(PDFParserConfig.class, config);
            context.set(EmbeddedDocumentExtractor.class, new ParsingEmbeddedDocumentExtractor(context));

            BodyContentHandler handler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();

            parser.parse(stream, handler, metadata);
            String content = handler.toString()
                    .replace("\u00AD", "")
                    .replaceAll("-\\s*\n", "")
                    .replaceAll("\n", " ");

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
