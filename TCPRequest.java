public class TCPRequest {
    public byte TML;
    public byte RequestID;
    public byte OpCode;
    public byte NumberOperands;
    public short Operand1;
    public short Operand2;


    public TCPRequest(byte TML, byte RequestID, byte OpCode, byte NumberOperands, short Operand1, short Operand2) {
        this.TML=TML;
        this.RequestID = RequestID;
        this.OpCode = OpCode;
        this.NumberOperands = NumberOperands;
        this.Operand1 = Operand1;
        this.Operand2=Operand2;
    }

    public String toString() {
        final String EOLN = java.lang.System.getProperty("line.separator");
        String value = "TML = 0x" + Integer.toHexString(TML) + EOLN +
                "TCPRequest ID = 0x" + Integer.toHexString(RequestID) + EOLN +
                "Op Code = 0x" + Integer.toHexString(OpCode) + EOLN +
                "Number Operands = 0x" + Integer.toHexString(NumberOperands) + EOLN +
                "Operand 1 = 0x" + Integer.toHexString(Operand1) + EOLN +
                "Operand 2 = 0x" + Integer.toHexString(Operand2) + EOLN;

        return value;
    }
}
