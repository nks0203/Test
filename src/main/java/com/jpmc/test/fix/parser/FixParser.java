package com.jpmc.test.fix.parser;

import static com.jpmc.test.fix.FieldsCache.fieldsCache;
import static com.jpmc.test.fix.FieldsCache.fixVersionCache;

public class FixParser {

    public static StringBuilder parseFixMessage(byte[] fixMessageBytes) {
        int start = 0;
        int length = fixMessageBytes.length;
        StringBuilder outputSB = new StringBuilder();
        int fixDefaultVersionIndex = -1;
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
                    // get FIX version to fetch relevant field names. One time. can be taken out of this loop.
                    for (int j = 0; j < fixVersionCache.length; j++) {
                        for (int i = equalsIndex + 5; i < separatorIndex; i++) {
                            if (fixVersionCache[j][i - (equalsIndex + 5)] != fixMessageBytes[i]) {
                                break;
                            }
                            if (i == separatorIndex - 1) {
                                fixDefaultVersionIndex = j;
                                break;//Showing redundant as only version cache size is 1 only right now.
                            }
                        }
                    }
                    if(fixDefaultVersionIndex==-1){
                        return new StringBuilder().append("FIX version not supported");
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
