package chess.websocket.util;

import static j2html.TagCreator.article;
import static j2html.TagCreator.p;

import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class ChatSender {

    public void sendMessage(Session session, String name, String message) {
        try {
            session.getRemote().sendString(String.valueOf(new JSONObject()
                .put("userMessage", createHtmlMessage(name, message))
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createHtmlMessage(String name, String message) {
        return article().with(
            p(name + " : " + message)
        ).render();
    }
}
