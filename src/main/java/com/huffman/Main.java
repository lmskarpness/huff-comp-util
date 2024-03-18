package com.huffman;

import com.utils.BinaryConverter;
import com.utils.FrequencyCounter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fp = "src/main/java/com/huffman/test.txt";

        try {
            HuffmanEncoder he = new HuffmanEncoder(fp);
            System.out.println("Codes:");
            for(String code : he.getTree().getHuffmanCodes()) {
                if (code != null) {
                    System.out.println(code);
                }
            }
            for (byte data : he.getEncodedByteArray()) {
                System.out.println(data);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}