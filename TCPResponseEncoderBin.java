import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class TCPResponseEncoderBin implements TCPResponseEncoder {

    private String encoding;  // Character encoding

    public TCPResponseEncoderBin() {
        encoding = null;
    }

    public TCPResponseEncoderBin(String encoding) {
        this.encoding = encoding;
    }

    public byte[] encode(TCPResponse TCPResponse) throws Exception {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);
        out.writeByte(TCPResponse.TML);
        out.writeByte(TCPResponse.ResponseID);
        out.writeByte(TCPResponse.ErrorCode);
        out.writeInt(TCPResponse.Result);
        out.flush();
        return buf.toByteArray();
    }
}
