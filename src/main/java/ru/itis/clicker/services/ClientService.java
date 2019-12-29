package ru.itis.clicker.services;

import ru.itis.clicker.client.SocketClient;

public class ClientService {
    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    private SocketClient socketClient = new SocketClient();

    public void startConnection() {
        socketClient.startConnection(host, 1234);
    }

    public void sendMessage(String message) {
        socketClient.sendMessage(message);
    }
}
