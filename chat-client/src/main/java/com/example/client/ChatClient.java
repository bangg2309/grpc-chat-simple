package com.example.client;
import com.example.ChatServiceGrpc;
import com.example.MessageRequest;
import com.example.MessageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public class ChatClient {
    private final ManagedChannel channel;
    private final ChatServiceGrpc.ChatServiceBlockingStub blockingStub;

    public ChatClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    public ChatClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = ChatServiceGrpc.newBlockingStub(channel);
    }

    public void sendMessage(String sender, String content) {
        MessageRequest request = MessageRequest.newBuilder()
                                .setSender(sender)
                                .setContent(content)
                                .build();
        MessageResponse response = blockingStub.sendMessage(request);
        System.out.println("Response from server: " + response.getStatus());
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
