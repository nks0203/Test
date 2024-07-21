package com.jpmc.test.fix.parser;

import com.jpmc.test.fix.cache.Trie;

import java.util.HashMap;
import java.util.Map;

public class FixParser {

    public static final Trie fixMetaCache = new Trie();

    static {
        fixMetaCache.put("8", "BeginString");
        fixMetaCache.put("9", "BodyLength");
        fixMetaCache.put("35", "MsgType");
        fixMetaCache.put("49", "SenderCompID");
        fixMetaCache.put("56", "TargetCompID");
        fixMetaCache.put("34", "MsgSeqNum");
        fixMetaCache.put("52", "SendingTime");
        fixMetaCache.put("262", "MDReqID");
        fixMetaCache.put("268", "NoMDEntries");
        fixMetaCache.put("279", "MDUpdateAction");
        fixMetaCache.put("269", "MDEntryType");
        fixMetaCache.put("278", "MDEntryID");
        fixMetaCache.put("55", "Symbol");
        fixMetaCache.put("270", "MDEntryPx");
        fixMetaCache.put("15", "Currency");
        fixMetaCache.put("271", "MDEntrySize");
        fixMetaCache.put("346", "NumberOfOrders");
        fixMetaCache.put("10", "CheckSum");
    }

    public static void parseFixMessage(byte[] fixMessageBytes) {
        // Create a map to store the parsed key-value pairs
        Map<String, String> fixMap = new HashMap<>();

        // Parse the byte array directly to extract key-value pairs
        int start = 0;
        int length = fixMessageBytes.length;
        StringBuffer stringBuilder = new StringBuffer();
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
                String key = fixMetaCache.get(fixMessageBytes, start, equalsIndex);
                stringBuilder.append(key).append(" : ");

                for (equalsIndex++; equalsIndex < separatorIndex; equalsIndex++) {
                    stringBuilder.append((char) fixMessageBytes[equalsIndex]);
                }
                stringBuilder.append(System.lineSeparator());
                // Move the start index to the character after '|'
                start = separatorIndex + 1;
            } else {
                break;
            }
        }
        System.out.println(stringBuilder);
    }
}
