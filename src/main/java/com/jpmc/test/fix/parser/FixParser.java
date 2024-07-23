package com.jpmc.test.fix.parser;

import static com.jpmc.test.fix.FieldsCache.*;

public class FixParser {


    public static StringBuilder parseFixMessage(byte[] fixMessageBytes) {
        // Create a map to store the parsed key-value pairs
        // Parse the byte array directly to extract key-value pairs
        int start = 0;
        int length = fixMessageBytes.length;
        StringBuilder outputSB = new StringBuilder();
        int fixDefaultVersionIndex = 0;
        while (start < length) {

            int equalsIndex = -1;
            int separatorIndex = -1;

            // Find the next '=' and '|' characters
            for (int i = start; i < length; i++) {
                if (fixMessageBytes[i] == '=') {
                    equalsIndex = i;
                } else if (fixMessageBytes[i] == '|') {
                    separatorIndex = i;
                    break;
                }
            }

            // If both '=' and '|' are found, extract key and value
            if (equalsIndex != -1 && separatorIndex != -1) {
                int key = fixMessageBytes[start] - 48;
                if (key == 8) {
                    // get FIX version to fetch relevant field names
                    String version = new String(fixMessageBytes, equalsIndex + 1, separatorIndex - equalsIndex - 1);
                    for (; fixDefaultVersionIndex < fixVersionCache.length; fixDefaultVersionIndex++) {
                        if (fixVersionCache[fixDefaultVersionIndex].equalsIgnoreCase(version)) {
                            break;
                        }
                    }
                }
                for (start++; start < equalsIndex; start++) {
                    key = key * 10 + (fixMessageBytes[start] - 48);
                }
                outputSB.append(key).append("\t\t").append(fieldsCache[fixDefaultVersionIndex][key]).append(" : ");

                for (equalsIndex++; equalsIndex < separatorIndex; equalsIndex++) {
                    outputSB.append((char) fixMessageBytes[equalsIndex]);
                }
                outputSB.append(System.lineSeparator());

                // Move the start index to the character after '|'
                start = separatorIndex + 1;
            } else {
                break;
            }
        }
       return outputSB;
    }
}
