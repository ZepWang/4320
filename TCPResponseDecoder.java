import java.io.*;   // for InputStream and IOException
import java.net.*;  // for DatagramPacket

public interface TCPResponseDecoder {
    TCPResponse decode(InputStream source) throws IOException;

    TCPResponse decode(DatagramPacket packet) throws IOException;
}
