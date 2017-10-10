/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieradzik;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author rwajm
 */
@ServerEndpoint("/echo/{roomID}") 
public class EchoServer {
    
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomID") String roomID){    //, EndpointConfig config){
        session.getUserProperties().put("roomID", roomID);
        SessionHandler.addSession(session);
        SessionHandler.sendToSession(session, "Connection Established");
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
                //System.out.println("Message from " + session.getId() + ": " + message);
                //SessionHandler.sendToSession(session, message);
                //SessionHandler.sendToAllConnectedSessions(message);
        String roomID = session.getUserProperties().get("roomID").toString();
        System.out.println("Message from " + session.getId() + ":->roomID=" + roomID + "; " + message);
        SessionHandler.sendToAllConnectedSessionsInRoom(roomID, message);
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){   //, CloseReason closeReason){
        System.out.println("Session " +session.getId() + ":->roomID=" + session.getUserProperties().get("roomID") +" has ended");
        SessionHandler.removeSession(session);
    }    

    @OnError
    public void onError(Session session, Throwable t) {
        Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, t);
    }
}
