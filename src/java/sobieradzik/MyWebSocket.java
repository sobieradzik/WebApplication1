/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieradzik;

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
@ServerEndpoint("/MyServer")
public class MyWebSocket {

    @OnOpen
    public void onOpen(Session session, @PathParam("roomID") String roomID){ //, EndpointConfig config){
    }
 
    @OnMessage
    public void onMessage(String message, Session session){
    }
 
    @OnClose
    public void onClose(Session session){   //, CloseReason closeReason){
    }    

    @OnError
    public void onError(Session session, Throwable t) {
        Logger.getLogger(MyWebSocket.class.getName()).log(Level.SEVERE, null, t);
    }
}
