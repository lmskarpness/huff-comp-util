package com.utils;

import java.io.ByteArrayOutputStream;

public class BinaryWriter {

    private ByteArrayOutputStream stream;
    private byte bitBuffer = 0;
    private byte bitsInBuffer = 0;

    public BinaryWriter() {
        this.stream = new ByteArrayOutputStream();
    }

    public byte[] getByteStream() {
        return stream.toByteArray();
    }

    public void writeBitToStream(char bitCharacter) {
        if (bitsInBuffer > 7) {
            stream.write(bitBuffer);
            bitsInBuffer = 0;
            bitBuffer = 0;
        }

        bitBuffer = (byte) ((byte) (bitBuffer << 1) | (bitCharacter == '1' ? 1 : 0));
        bitsInBuffer++;
    }

    public void writeCharToStream(char symbol) {
        String bitString = Integer.toBinaryString(symbol);
        String binaryString = String.format("%8s", bitString).replaceAll(" ", "0");
        for (int i = 0; i < binaryString.length(); i++) {
            writeBitToStream(binaryString.charAt(i));
        }
    }

    public void writeIntToStream(int number) {
        String bitString = Integer.toBinaryString(number);
        String binaryString = String.format("%32s", bitString).replaceAll(" ", "0");
        for (int i = 0; i < binaryString.length(); i++) {
            writeBitToStream(binaryString.charAt(i));
        }
    }

    public void writeStringToStream(String inputString) {
        for (int i = 0; i < inputString.length(); i++) {
            writeCharToStream(inputString.charAt(i));
        }
    }

    public void flushBitBuffer() {
        if (bitsInBuffer > 0) {
            do {
                writeBitToStream('0');
            } while (bitsInBuffer != 1);
        }
    }
}
