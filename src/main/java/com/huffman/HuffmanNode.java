package com.huffman;

public class HuffmanNode {

    private char symbol;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(char symbol, int frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setLeftChild(HuffmanNode huffmanNode) {
        this.left = huffmanNode;
    }

    public HuffmanNode getLeftChild() {
        return this.left;
    }


    public void setRightChild(HuffmanNode huffmanNode) {
        this.right = huffmanNode;
    }

    public HuffmanNode getRightChild() {
        return this.right;
    }

}
