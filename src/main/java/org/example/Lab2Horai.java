package org.example;

import java.io.IOException;
import java.util.*;

public class Lab2Horai {
    public static void main(String[] args) {
        try {
            String[] words = readConsoleInput();
            String[] filteredWords = removeDuplicates(words);
            String[] filteredArray = filteredWords.clone();
            Arrays.sort(filteredArray, Comparator.comparing(String::toLowerCase));
            outputMessage(words, "\nYOUR INPUT");
            outputMessage(filteredWords, "\nFILTERED DATA");
            outputMessage(filteredArray, "\nUNIQUE FILTERED DATA");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the console input" + e.getMessage());
        }
    }

    public static String[] readConsoleInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] words = line.split("[,.!?:\\- ]+");
        for (String word : words) {
            if (!word.matches("[a-zA-Zа-яА-Яґєїі'`]+|\\d+")) {
                scanner.close();
                throw new IOException(" INVALID INPUT " + word);
            }
        }
        scanner.close();
        return words;
    }

    public static String[] removeDuplicates(String[] unfilteredWords) {
        Map<String, String> uniqueWordsMap = new LinkedHashMap<>();

        for (String word : unfilteredWords) {
            String lowercaseWord = word.toLowerCase();
            uniqueWordsMap.putIfAbsent(lowercaseWord, word);
        }

        return uniqueWordsMap.values().toArray(new String[0]);
    }

    public static String arrayToString(String[] array) {
        return Arrays.toString(array).replaceAll("[\\[\\]]", "");
    }

    public static void outputMessage(String[] array, String message) {
        System.out.println(message);
        System.out.println(arrayToString(array));
    }

}