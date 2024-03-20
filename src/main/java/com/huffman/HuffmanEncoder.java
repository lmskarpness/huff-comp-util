package com.huffman;

import com.utils.BinaryWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanEncoder {

    private BinaryWriter binConverter;
    private byte[] encodedByteArray;
    private int headerSizeBits = 0;
    private HuffmanTree tree;
    private String filename;

    public HuffmanEncoder(String filename) throws FileNotFoundException {
        this.filename = filename;
        this.tree = new HuffmanTree(filename);
        this.binConverter = new BinaryWriter();
        headerSizePreprocessor(tree.getTree());
        processFile();
    }

    public HuffmanTree getTree() {
        return this.tree;
    }

    public byte[] getEncodedByteArray() {
        return encodedByteArray;
    }

    private void processFile() {
        writeHeaderSize();
        processTreeToHeader(tree.getTree());
        binConverter.flushBitBuffer();
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

    private void writeHeaderSize() {
        int bytes = this.headerSizeBits / 8;
        binConverter.writeIntToStream(bytes);
    }

    private void headerSizePreprocessor(HuffmanNode root) {
        if (root instanceof HuffmanLeaf) {
            // 1 bit ('1' indicator) + 1 byte (symbol)
            int size = 9;
            this.headerSizeBits += size;
        } else {
            this.headerSizeBits++;
            headerSizePreprocessor(root.getLeftChild());
            headerSizePreprocessor(root.getRightChild());
        }
    }

    public void processTreeToHeader(HuffmanNode root) {
        if (root instanceof HuffmanLeaf) {
            char symbol = ((HuffmanLeaf) root).getSymbol();
            binConverter.writeBitToStream('1');
            binConverter.writeCharToStream(symbol);

        } else {
            binConverter.writeBitToStream('0');
            processTreeToHeader(root.getLeftChild());
            processTreeToHeader(root.getRightChild());
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
