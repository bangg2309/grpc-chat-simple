package com.example.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientApplication {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 12345;
    public void startClient(){
        ChatClient client = new ChatClient(HOST, PORT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            try {
                System.out.println("Command list: ");
                System.out.println("1. Send message");
                System.out.println("2. Exit");
                System.out.println("Enter your command: ");
                String command = reader.readLine();
                if(command.equals("2")){
                    client.shutdown();
                    break;
                }
                System.out.println("Enter your name: ");
                String sender = reader.readLine();
                System.out.println("Enter your message: ");
                String content = reader.readLine();
                client.sendMessage(sender, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ClientApplication clientApplication = new ClientApplication();
        clientApplication.startClient();
    }
}
