package chess.websocket;

import chess.websocket.commander.RequestCommand;
import chess.websocket.commander.RequestCommander;
import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WebSocketHandler {

    private RequestCommander commander = new RequestCommander();

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        System.out.println("connected!!!!!!!");
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        commander.leaveRoom(user);
        System.out.println("left user");
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) throws IOException {
        if (message.startsWith("<request>")) {
            RequestCommand.execute(commander, message.split(" "), user);
        } else {
            commander.sendMessage(user, message);
        }
    }
}
