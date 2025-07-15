package net.todream.uni_translate.uni_translate.initializer;

import java.io.*;
import java.nio.file.*;

public class LanguageFileInitializer {
    
    private static final String LANGUAGE_FILE = "language.json";

    public static void ensureJsonFileExists() {
        Path externalPath = Paths.get(LANGUAGE_FILE);
        if (Files.exists(externalPath)) {
            return;
        }

        try (InputStream in = LanguageFileInitializer.class.getClassLoader().getResourceAsStream(LANGUAGE_FILE)) {
            if (in == null) {
                throw new FileNotFoundException("Internal resource '" + LANGUAGE_FILE + "' not found in JAR.");
            }

            Files.copy(in, externalPath);
            System.out.println("Extracted " + LANGUAGE_FILE + " to working directory.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract " + LANGUAGE_FILE, e);
        }
    }

}
