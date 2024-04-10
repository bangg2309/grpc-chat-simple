package com.example.server;

public class ServerApplication {
    public static final int PORT = 12345;

    public static void main(String[] args) throws Exception {
        ChatServer server = new ChatServer(PORT);
        server.start();
        server.blockUntilShutdown();
    }
}
