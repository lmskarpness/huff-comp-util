package com.utils;

import java.io.ByteArrayInputStream;

public class BinaryReader {

    private ByteArrayInputStream stream;
    private int currentByte = 0;
    private byte bitMask = 0;
    private byte bitOffset = 0;

    public BinaryReader(byte[] buffer) {
        this.stream = new ByteArrayInputStream(buffer);
    }

    public char readBitFromStream() {
        if (bitOffset == 0) {
            currentByte = (byte) stream.read();
            bitMask = (byte) (1 << 7);
        }

        int masked = currentByte & bitMask;

        bitMask = (byte) (bitMask >> 1);
        bitOffset++;

        if (bitOffset == 8) {
            bitOffset = 0;
        }

        return (masked > 0) ? '1' : '0';
    }

    // 8-bit char (ASCII)
    public char readByteFromStream() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            builder.append(readBitFromStream());
        }
        byte b = Byte.parseByte(String.valueOf(builder), 2);
        return (char) b;
    }

    public ByteArrayInputStream getStream() {
        return stream;
    }



}
