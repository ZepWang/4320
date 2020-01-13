public interface TCPResponseEncoder {
    byte[] encode(TCPResponse response) throws Exception;
}
