package model.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class SocketServer {
    private static volatile ServerSocket socket;
    private static volatile Socket playerSocket = null;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;


    static void setSocket() {
        try {
            socket = new ServerSocket(7070, 0, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void waitPlayers() {
        try {
            playerSocket = socket.accept();
            outputStream = new DataOutputStream(playerSocket.getOutputStream());
            inputStream = new DataInputStream(playerSocket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void closeSockets() {
        if (playerSocket != null)
            try {
                inputStream.close();
                outputStream.close();
                playerSocket.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    static void sendData(byte[] sendData) {
        try {
            outputStream.write(sendData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static byte[] receiveData() {
        byte[] result = new byte[1024];
        try {
            inputStream.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
