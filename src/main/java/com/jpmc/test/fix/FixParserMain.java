package com.jpmc.test.fix;

import java.util.Scanner;

import static com.jpmc.test.fix.parser.FixParser.parseFixMessage;

public class FixParserMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to FixParser!!\n");

        FieldsCache.initCache();

        while (true) {
            System.out.println("Enter new FIX message (type 'exit' to quit):");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            byte[] fixMessageBytes = input.getBytes();
            System.out.println(parseFixMessage(fixMessageBytes));
            System.out.println("FIX message parsed!");
        }

        scanner.close();
        System.out.println("Fix Parser Terminated.");
    }

}

