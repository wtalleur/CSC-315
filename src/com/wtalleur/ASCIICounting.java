package com.wtalleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author William Talleur <wtalleur@callutheran.edu>
 * @date January 19, 2017
 */
public class ASCIICounting {

    private static File dataFile = new File("./data/HuckleberryFinnASCII.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input character to search for:");
        char c = scanner.next().charAt(0);
        int countR = 0, countI = 0;

        if (dataFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String s;
                while ((s = reader.readLine()) != null) {
                    countR += CharCountRecursive(c, s);
                    countI += CharCountIterative(c, s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[Recursive]: " + c + " occured " + countR + " times");
        System.out.println("[Iterative]: " + c + " occured " + countI + " times");
    }

    public static int CharCountIterative(char c, String s) {
        int charCount = 0;
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == c)
                charCount++;
        }
        return charCount;
    }

    public static int CharCountRecursive(char c, String s) {
        if (s.length() == 0)
            return 0;
        if (s.charAt(0) == c)
            return 1 + CharCountRecursive(c, s.substring(1));
        return CharCountRecursive(c, s.substring(1));
    }
}