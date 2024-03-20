package com.huffman;

import com.utils.BinaryReader;

import java.io.DataInputStream;
import java.io.IOException;

public class HuffmanDecoder {

    private BinaryReader binReader;
    private byte[] data;
    private byte[] rawFileData;
    private int headerSize;
    private HuffmanNode tree;
    private String filename;

    public HuffmanDecoder(String filename) {
        this.filename = filename;
        this.tree = null;

        this.rawFileData = FileHandler.createByteArrayFromFile(filename);
        this.binReader = new BinaryReader(rawFileData);
        this.headerSize = extractHeaderSize();
        this.tree = generateTreeFromHeader();
        this.data = extractData();

        processFile();
    }

    public byte[] getData() {
        return data;
    }

    public int getHeaderSize() {
        return headerSize;
    }

    private void processFile() {

    }

    private void processHeader() {
        generateTreeFromHeader();
    }

    public int extractHeaderSize() {
        try (DataInputStream dis = new DataInputStream(binReader.getStream())) {
            binReader.getStream().readNBytes(4);
            return dis.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private HuffmanNode generateTreeFromHeader() {
        if (binReader.readBitFromStream() == '1') {
            return new HuffmanLeaf(0, binReader.readByteFromStream());
        } else {
            HuffmanNode left = generateTreeFromHeader();
            HuffmanNode right = generateTreeFromHeader();

            HuffmanNode interm = new HuffmanNode(0);
            interm.setLeftChild(left);
            interm.setRightChild(right);
            return interm;
        }
    }

    private byte[] extractData() {
        int headerSize = this.headerSize;
        int startIndex = 4 + headerSize;
        int sliceSize = rawFileData.length - startIndex;

        byte[] data = new byte[sliceSize];

        for (int i = startIndex, j = 0; i < rawFileData.length; i++, j++) {
            data[j] = rawFileData[i];
        }

        return data;
    }
}
