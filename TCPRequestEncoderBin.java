import java.io.*;  // for ByteArrayOutputStream and DataOutputStream

public class TCPRequestEncoderBin implements TCPRequestEncoder {

    private String encoding;  // Character encoding

    public TCPRequestEncoderBin() {
        encoding = null;
    }

    public TCPRequestEncoderBin(String encoding) {
        this.encoding = encoding;
    }

    public byte[] encode(TCPRequest request) throws Exception {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);
        out.writeByte(request.TML);
        out.writeByte(request.RequestID);
        out.writeByte(request.OpCode);
        out.writeByte(request.NumberOperands);
        out.writeShort(request.Operand1);
        out.writeShort(request.Operand2);
        out.flush();
        return buf.toByteArray();
    }
}
