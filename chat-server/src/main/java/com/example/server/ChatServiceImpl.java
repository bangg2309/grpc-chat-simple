package com.example.server;

import com.example.ChatServiceGrpc;
import com.example.MessageRequest;
import com.example.MessageResponse;
import io.grpc.stub.StreamObserver;

public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {

        String sender = request.getSender();
        String content = request.getContent();

        System.out.println("Message received from " + sender + ": " + content);
        // Process message here
        MessageResponse response = MessageResponse
                                    .newBuilder()
                                    .setStatus("Message received")
                                    .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

