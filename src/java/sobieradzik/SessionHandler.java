/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieradzik;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author rwajm
 */
public class SessionHandler {

    private static final Set<Session> sessions = new HashSet<>();

    public static void addSession(Session session) {
        sessions.add(session);
    }

    public static void removeSession(Session session) {
        sessions.remove(session);
    }

    public static void sendToSession(Session session, String message) {
        Future<Void> future = session.getAsyncRemote().sendText(message);
        //try {
            //session.getBasicRemote().sendText(message);
            System.out.println("Message to " + session.getId() + ":->roomID="+ session.getUserProperties().get("roomID") + "; " + message);
        //} catch (IOException ex) {
        //    sessions.remove(session);
        //    Logger.getLogger(SessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }
    
    public static void sendToAllConnectedSessions(String message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    public static void sendToAllConnectedSessionsInRoom(String roomID, String message) {
        for (Session session : sessions) {
            if (session.getUserProperties().get("roomID").equals(roomID))
                sendToSession(session, message);
        }
    }
}
