package com.huffman;

import com.utils.FrequencyCounter;

public class HuffmanTree {

    public final int ASCII_RANGE = 256;
    private int[] asciiFrequencies;
    private String[] huffmanCodes;
    private HuffmanNode[] huffmanNodes;
    private HuffmanNode treeRoot;
    private int queueSize = 0;

    public HuffmanTree(String filename) {
        FrequencyCounter frequencyCounter = new FrequencyCounter(filename);
        this.asciiFrequencies = frequencyCounter.getFrequencies();
        this.huffmanNodes = new HuffmanNode[ASCII_RANGE];
        this.huffmanCodes = new String[ASCII_RANGE];
        populateNodeQueue();
        generateTree();
        generateHuffmanCodes(this.getTree(), "");
    }

    public HuffmanNode getTree() {
        return this.treeRoot;
    }

    public String[] getHuffmanCodes() {
        return this.huffmanCodes;
    }

    private void generateTree() {
        while (this.queueSize > 1) {
            int min1Idx = 0, min2Idx = 1;
            if (huffmanNodes[min1Idx].getFrequency() > huffmanNodes[min2Idx].getFrequency()) {
                int temp = min1Idx;
                min1Idx = min2Idx;
                min2Idx = temp;
            }
            for (int i = 2; i < queueSize; i++) {
                if (huffmanNodes[i].getFrequency() < huffmanNodes[min1Idx].getFrequency()) {
                    min2Idx = min1Idx;
                    min1Idx = i;
                } else if (huffmanNodes[i].getFrequency() < huffmanNodes[min2Idx].getFrequency()) {
                    min2Idx = i;
                }
            }

            HuffmanNode sumNode = new HuffmanNode('0',
                    huffmanNodes[min1Idx].getFrequency() + huffmanNodes[min2Idx].getFrequency());
            sumNode.setLeftChild(huffmanNodes[min1Idx]);
            sumNode.setRightChild(huffmanNodes[min2Idx]);

            huffmanNodes[min1Idx] = sumNode;
            huffmanNodes[min2Idx] = huffmanNodes[--queueSize];
        }

        this.treeRoot = huffmanNodes[0];
    }

    private void populateNodeQueue() {
        for (int i = 0; i < ASCII_RANGE; i++) {
            if (asciiFrequencies[i] > 0) {
                huffmanNodes[this.queueSize++] = new HuffmanNode((char) i, asciiFrequencies[i]);
            }
        }
    }

    private void generateHuffmanCodes(HuffmanNode node, String code) {
        if (node.getLeftChild() != null) {
            code += '0';
            generateHuffmanCodes(node.getLeftChild(), code);
        }
        if (node.getRightChild() != null) {
            code += '1';
            generateHuffmanCodes(node.getRightChild(), code);
        }
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            huffmanCodes[(int) node.getSymbol()] = code;
        }
    }
}
