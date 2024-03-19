package com.huffman;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String in = "src/main/java/com/huffman/test.txt";
        String out = "src/main/resources/output/compressed.huf";
        try {
            HuffmanEncoder he = new HuffmanEncoder(in);

            System.out.println("Codes:");
            for(String code : he.getTree().getHuffmanCodes()) {
                if (code != null) {
                    System.out.println(code);
                }
            }
            for (byte data : he.getEncodedByteArray()) {
                System.out.println(data);
            }

            FileHandler fh = new FileHandler(out);
            fh.createCompressedFile(he.getEncodedByteArray());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}