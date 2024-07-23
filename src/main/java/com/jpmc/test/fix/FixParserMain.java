package com.jpmc.test.fix;

import java.util.Scanner;

import static com.jpmc.test.fix.parser.FixParser.parseFixMessage;

public class FixParserMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to FixParser!! This API supports only FIX.4.4 version so far.\n");

        while (true) {
            System.out.println("Enter new FIX message (type 'e' to exit):");
            String input = scanner.nextLine();
            if ("e".equalsIgnoreCase(input)) {
                break;
            }

            System.out.println("\nMessage:");
            byte[] fixMessageBytes = input.getBytes();
            System.out.println(parseFixMessage(fixMessageBytes));
        }

        scanner.close();
        System.out.println("Fix Parser Terminated.");
    }

}

