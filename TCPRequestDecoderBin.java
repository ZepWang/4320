import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class TCPRequestDecoderBin implements TCPRequestDecoder {

    private String encoding;  // Character encoding

    public TCPRequestDecoderBin() {
        encoding = null;
    }

    public TCPRequestDecoderBin(String encoding) {
        this.encoding = encoding;
    }

    public TCPRequest decode(InputStream wire) throws IOException {
        DataInputStream src = new DataInputStream(wire);
        byte TML=src.readByte();
        byte RequestID=src.readByte();
        byte OpCode=src.readByte();
        byte NumberOperands=src.readByte();
        short Operand1=src.readShort();
        short Operand2=src.readShort();


        return new TCPRequest(TML, RequestID, OpCode, NumberOperands, Operand1, Operand2);
    }

    public TCPRequest decode(DatagramPacket p) throws IOException {
        ByteArrayInputStream payload =
                new ByteArrayInputStream(p.getData(), p.getOffset(), p.getLength());
        return decode(payload);
    }
}
