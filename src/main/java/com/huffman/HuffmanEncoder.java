package com.huffman;

import com.utils.BinaryConverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanEncoder {

    private BinaryConverter binConverter;
    private byte[] encodedByteArray;
    private HuffmanTree tree;
    private FileReader fileReader;
    private String filename;

    public HuffmanEncoder(String filename) throws FileNotFoundException {
        this.filename = filename;
        this.tree = new HuffmanTree(filename);
        this.binConverter = new BinaryConverter();
        processFile();
    }

    public HuffmanTree getTree() {
        return this.tree;
    }

    public byte[] getEncodedByteArray() {
        return encodedByteArray;
    }

    private void processFile() {
        try {
            String line;
            FileReader reader = new FileReader(filename);
            BufferedReader inStream = new BufferedReader(reader);
            while ((line = inStream.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    writeCodeToBinaryConverterStream(charToHuffmanCode(line.charAt(i)));
                }
            }
            binConverter.flushBitBuffer();
            this.encodedByteArray = binConverter.getByteStream();
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String charToHuffmanCode(char symbol) {
        return tree.getHuffmanCodes()[(int) symbol];
    }

    private void writeCodeToBinaryConverterStream(String code) {
        for (int i = 0; i < code.length(); i++) {
            binConverter.writeBitToStream(code.charAt(i));
        }
    }
}
