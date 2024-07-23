package com.jpmc.test.fix;

import com.sun.tools.javac.Main;

import java.io.*;

public class FieldsCache {

    public static final String[] fixVersionCache = new String[2];
    public static String[][] fieldsCache;

    static void initCache() {
        fixVersionCache[0]="FIX.4.4";
        fixVersionCache[1]="FIX.4.2";
        fieldsCache = readDataFromFile("Fields.txt", 1, 957);
    }

    private static String[][] readDataFromFile(String filename, int rows, int cols) {
        String[][] fieldsCache = new String[rows][cols];
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filename)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\t");
                    int index = Integer.parseInt(parts[0]);
                    fieldsCache[0][index] = parts[1];
                }
            }
        } catch (Exception e) {
            System.out.println("Error while parsing fields cache data.Please contact support team.Terminating..!");
            System.exit(-1);
        }

        return fieldsCache;
    }
}
