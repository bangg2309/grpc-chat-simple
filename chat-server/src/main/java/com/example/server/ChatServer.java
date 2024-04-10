package com.example.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ChatServer {
    private final int port;
    private final Server server;

    public ChatServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new ChatServiceImpl())
                .build();
    }

    public void start() throws Exception {
        server.start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            ChatServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

     protected  void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
