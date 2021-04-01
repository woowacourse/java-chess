package chess.websocket.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;

public class ResourceSender {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void send(Session session, T resource) {
        try {
            session.getRemote().sendString(objectMapper.writeValueAsString(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
