import java.io.*;  // for ByteArrayInputStream
import java.net.*; // for DatagramPacket

public class TCPResponseDecoderBin implements TCPResponseDecoder {

    private String encoding;  // Character encoding

    public TCPResponseDecoderBin() {
        encoding = null;
    }

    public TCPResponseDecoderBin(String encoding) {
        this.encoding = encoding;
    }

    public TCPResponse decode(InputStream wire) throws IOException {
        DataInputStream src = new DataInputStream(wire);
        byte TML=src.readByte();
        byte ResponseID=src.readByte();
        byte ErrorCode=src.readByte();
        int Result=src.readInt();

        return new TCPResponse(TML, ResponseID, ErrorCode, Result);
    }

    public TCPResponse decode(DatagramPacket p) throws IOException {
        ByteArrayInputStream payload =
                new ByteArrayInputStream(p.getData(), p.getOffset(), p.getLength());
        return decode(payload);
    }
}
