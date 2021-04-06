package chess.websocket.commander;

import chess.domain.TeamColor;
import chess.websocket.exception.FullRoomException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.eclipse.jetty.websocket.api.Session;

public class ChessRoom {

    private static final Map<Long, List<Session>> ROOMS = new HashMap<>();

    public TeamColor enter(Long roomId, Session session) {
        List<Session> players = ROOMS.getOrDefault(roomId, new ArrayList<>());
        if (players.size() >= 2) {
            throw new FullRoomException();
        }
        players.add(session);
        ROOMS.put(roomId, players);
        if(players.size() == 1) {
            return TeamColor.WHITE;
        }
        return TeamColor.BLACK;
    }

    public Optional<Session> otherPlayer(Session session) {
        Long roomId = keyBySession(session);
        return ROOMS.get(roomId)
            .stream()
            .filter(player -> !player.equals(session))
            .findAny();
    }

    public Long keyBySession(Session session) {
        return ROOMS.entrySet()
            .stream()
            .filter(entry -> entry.getValue().contains(session))
            .map(Entry::getKey)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public void remove(Session session) {
        Long gameId = keyBySession(session);
        ROOMS.get(gameId).remove(session);
    }
}
