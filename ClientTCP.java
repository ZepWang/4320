import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException
import java.util.Scanner;

public class ClientTCP {
    private static final int TIMEOUT = 3000;   // Resend timeout (milliseconds)

    public static void main(String args[]) throws Exception {

        if (args.length != 2 && args.length != 3)  // Test for correct # of args
            throw new IllegalArgumentException("Parameter(s): <Destination>" +
                    " <Port> [<encoding]");


        InetAddress destAddr = InetAddress.getByName(args[0]);  // Destination address
        int destPort = Integer.parseInt(args[1]);               // Destination port

        // Use the encoding scheme given on the command line (args[2])
        TCPRequestEncoder encoder = new TCPRequestEncoderBin();
        TCPResponseDecoder decoder = new TCPResponseDecoderBin();

        Scanner scanner = new Scanner(System.in);
        byte TML=8;
        byte RequestID=0;
        byte OpCode=0;
        byte NumberOperands=0;
        short Operand1=0;
        short Operand2=0;

        while (true){

            System.out.print("Please input Op Code: ");
            OpCode=scanner.nextByte();
            if(OpCode>=0&&OpCode<=5){
                System.out.print("Please input Operand1: ");
                Operand1=scanner.nextShort();
                System.out.print("Please input Operand2: ");
                Operand2=scanner.nextShort();
                NumberOperands=2;
            }else if(OpCode==6){
                System.out.print("Please input Operand1: ");
                Operand1=scanner.nextShort();
                NumberOperands=1;
            }else {
                System.out.println("Program Exits");
                break;
            }

            Socket socket = new Socket(destAddr, destPort);

            TCPRequest request = new TCPRequest(TML, RequestID, OpCode, NumberOperands, Operand1, Operand2);

            RequestID++;


            socket.setSoTimeout(TIMEOUT);  // Maximum receive blocking time (milliseconds)

            long tic = System.nanoTime();
            OutputStream out = socket.getOutputStream(); // Get a handle onto Output Stream
            out.write(encoder.encode(request)); // Encode and send

            InputStream in = socket.getInputStream();
            TCPResponse response = decoder.decode(in);
            long toc = System.nanoTime();

            System.out.println("Received Result: " + response.Result);
            String duration = String.format("%.2f",(toc - tic)/1000000.0);
            System.out.println("Duration time: " + duration + " milliseconds.\n");
            System.out.println("--------------------------------------");
            System.out.println("Client Sent Binary-Encoded TCPRequest");
            System.out.println(request);
            System.out.println("Client Received Binary-Encoded TCPResponse");
            System.out.println(response);

            socket.close();
        }
    }
}
