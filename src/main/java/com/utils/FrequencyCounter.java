package com.utils;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FrequencyCounter {

    private FileReader fileReader;
    private int[] frequencies;
    public final int ASCII_RANGE = 256;

    public FrequencyCounter(FileReader fileReader) {
        this.fileReader = fileReader;
        this.frequencies = new int[ASCII_RANGE];
        countCharFrequencies();
    }

    public int[] getFrequencies() {
        return this.frequencies;
    }

    private void countCharFrequencies() {
        try {
            BufferedReader inStream = new BufferedReader(fileReader);
            String line;
            while((line = inStream.readLine()) != null) {
                for (int char_i = 0; char_i < line.length(); char_i++) {
                    frequencies[(int) line.charAt(char_i)]++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
