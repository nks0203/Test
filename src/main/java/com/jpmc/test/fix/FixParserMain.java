package com.jpmc.test.fix;

import static com.jpmc.test.fix.parser.FixParserStringBuffer.*;

public class FixParserMain {


    public static void main(String[] args) {

        // Example byte array input representing the FIX message
        byte[] fixMessageBytes = "8=FIX.4.2|9=196|35=X|49=A|56=B|34=12|52=20100318-03:21:11.364|262=A|268=2|279=0|269=0|278=BID|55=EUR/USD|270=1.37215|15=EUR|271=2500000|346=1|279=0|269=1|278=OFFER|55=EUR/USD|270=1.37224|15=EUR|271=2503200|346=1|10=171|".getBytes();
        byte[] fixMessageBytes1 = "8=FIX.4.2|9=97|35=6|49=BKR|56=IM|34=14|52=20100204-09:18:42|23=115685|28=N|55=SPMI.MI|54=2|44=2200.75|27=S|25=H|10=248|".getBytes();
        byte[] fixMessageBytes2 = "8=FIX.4.4|9=122|35=D|34=215|49=CLIENT12|52=20100225-19:41:57.316|56=B|1=Marcel|11=13346|21=1|40=2|44=5|54=1|59=0|60=20100225-19:39:52.020|10=072|".getBytes();
        byte[] fixMessageBytes3 = "8=FIX.4.4|9=117|35=AD|34=2|49=A|50=1|52=20100219-14:33:32.258|56=B|57=M|263=1|568=1|569=0|580=1|75=20100218|60=20100218-00:00:00.000|10=202|".getBytes();
        byte[] fixMessageBytes4 = "8=FIX.4.4|9=94|35=3|34=214|49=A|50=U1|52=20100304-09:42:23.130|56=AB|128=B1|45=176|58=txt|371=15|372=X|373=1|10=058|".getBytes();
        byte[] fixMessageBytes5 = "8=FIX.4.4|9=70|35=4|49=A|56=XYZ|34=129|52=20100302-19:38:21|43=Y|57=LOL|123=Y|36=175|10=192|".getBytes();
        byte[] fixMessageBytes6 = "8=FIX.4.4|9=122|35=D|34=215|49=CLIENT12|52=20100225-19:41:57.316|56=B|1=Marcel|11=13346|21=1|40=2|44=5|54=1|59=0|60=20100225-19:39:52.020|10=072|".getBytes();
        byte[] fixMessageBytes7 = "8=FIX.4.2|9=196|35=X|49=A|56=B|34=12|52=20100318-03:21:11.364|262=A|268=2|279=0|269=0|278=BID|55=EUR/USD|270=1.37215|15=EUR|271=2500000|346=1|279=0|269=1|278=OFFER|55=EUR/USD|270=1.37224|15=EUR|271=2503200|346=1|10=171|".getBytes();
        byte[] fixMessageBytes8 = "8=FIX.4.2|9=42|35=0|49=A|56=B|34=12|52=20100304-07:59:30|10=185|".getBytes();
        byte[] fixMessageBytes9 = "8=FIX.4.2|9=97|35=6|49=BKR|56=IM|34=14|52=20100204-09:18:42|23=115685|28=N|55=SPMI.MI|54=2|44=2200.75|27=S|25=H|10=248|".getBytes();

        long t = System.currentTimeMillis();
        parseFixMessage(fixMessageBytes);
        while (true) {
            parseFixMessage(fixMessageBytes2);
            parseFixMessage(fixMessageBytes1);
            parseFixMessage(fixMessageBytes3);
            parseFixMessage(fixMessageBytes);
            parseFixMessage(fixMessageBytes4);
            parseFixMessage(fixMessageBytes);
            parseFixMessage(fixMessageBytes5);
            parseFixMessage(fixMessageBytes7);
            parseFixMessage(fixMessageBytes8);
            parseFixMessage(fixMessageBytes);
            parseFixMessage(fixMessageBytes9);
            parseFixMessage(fixMessageBytes);
            parseFixMessage(fixMessageBytes6);
            System.out.println("totalTime " + (System.currentTimeMillis() - t));
        }
    }
/*
    private static void parseFixMessage(byte[] fixMessageBytes) {
        // Create a map to store the parsed key-value pairs
        Map<String, String> fixMap = new HashMap<>();

        // Parse the byte array directly to extract key-value pairs
        int start = 0;
        int length = fixMessageBytes.length;
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
                String key = new String(fixMessageBytes, start, equalsIndex - start);
                String value = new String(fixMessageBytes, equalsIndex + 1, separatorIndex - equalsIndex - 1);
                fixMap.put(key, value);

                // Move the start index to the character after '|'
                start = separatorIndex + 1;
            } else {
                // Invalid message format, exit the loop
                break;
            }
        }

        // Output the parsed key-value pairs
        for (Map.Entry<String, String> entry : fixMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }*/
}

