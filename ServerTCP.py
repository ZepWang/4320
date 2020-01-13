import sys
import socket
import struct

BUFSIZE = 1024

if len(sys.argv)!=3:
    raise ValueError("Parameter(s): <Address> <Port>")

address = sys.argv[1]
port = int(sys.argv[2])
serverAddress =(address,port)



while True:
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    serverSocket.bind(serverAddress)

    serverSocket.listen(5)
    clientSocket, addr = serverSocket.accept()

    request = clientSocket.recv(BUFSIZE)
    TML, RequestID, OpCode, NumberOperands, Operand1, Operand2 = struct.unpack('!bbbbhh',request)

    ErrorCode=127
    if TML == len(request):
        ErrorCode=0

    Result=0
    if OpCode == 0:
        Result = Operand1 + Operand2
    elif OpCode == 1:
        Result = Operand1 - Operand2
    elif OpCode == 2:
        Result = Operand1 * Operand2
    elif OpCode == 3:
        Result = Operand1 // Operand2
    elif OpCode == 4:
        Result = Operand1 >> Operand2
    elif OpCode == 5:
        Result = Operand1 << Operand2
    elif OpCode == 6:
        Result = ~Operand1

    newTML = 7

    bytesToSend = struct.pack('!bbbi',newTML, RequestID, ErrorCode, Result)
    clientSocket.sendto(bytesToSend, addr)

    EOLN = '\n'
    print("--------------------------------------")
    print("Server Received Binary-Encoded TCPRequest")
    print("TML = " + hex(TML) + EOLN + \
                "Request ID = " + hex(RequestID) + EOLN + \
                "Op Code  = " + hex(OpCode) + EOLN + \
                "Number Operands  = " + hex(NumberOperands) + EOLN + \
                "Operand 1  = " + hex(Operand1) + EOLN + \
                "Operand 2  = " + hex(Operand2) + EOLN)
    print("Server Sent Binary-Encoded TCPResponse")
    print("TML = " + hex(newTML) + EOLN + \
                "Response ID = " + hex(RequestID) + EOLN + \
                "ErrorCode = " + hex(ErrorCode) + EOLN + \
                "Result = " + hex(Result) + EOLN)


    clientSocket.close()
