package com.huffman;

public class HuffmanNode {

    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(int frequency) {
        this.frequency = frequency;
        this.left = null;
        this.right = null;
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
