package com.utils;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FrequencyCounter {

    private String filename;
    private int[] frequencies;
    public final int ASCII_RANGE = 256;

    public FrequencyCounter(String filename) {
        this.filename = filename;
        this.frequencies = new int[ASCII_RANGE];
        countCharFrequencies();
    }

    public int[] getFrequencies() {
        return this.frequencies;
    }

    private void countCharFrequencies() {
        try {
            BufferedReader inStream = new BufferedReader(new FileReader(filename));
            String line;
            while((line = inStream.readLine()) != null) {
                for (int char_i = 0; char_i < line.length(); char_i++) {
                    frequencies[(int) line.charAt(char_i)]++;
                }
            }
            inStream.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
