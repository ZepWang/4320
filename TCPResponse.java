public class TCPResponse {
    public byte TML;
    public byte ResponseID;
    public byte ErrorCode;
    public int Result;


    public TCPResponse(byte TML, byte ResponseID, byte ErrorCode, int Result) {
        this.TML=TML;
        this.ResponseID = ResponseID;
        this.ErrorCode = ErrorCode;
        this.Result = Result;
    }

    public String toString() {
        final String EOLN = java.lang.System.getProperty("line.separator");
        String value = "TML = 0x" + Integer.toHexString(TML) + EOLN +
                "TCPResponse ID = 0x" + Integer.toHexString(ResponseID) + EOLN +
                "ErrorCode = 0x" + Integer.toHexString(ErrorCode) + EOLN +
                "Result = 0x" + Integer.toHexString(Result) + EOLN;

        return value;
    }
}
