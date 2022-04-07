package chess.dao;

import chess.domain.event.Event;
import java.util.List;

public interface EventDao {

    List<Event> findAllByGameId(int gameId);

    void save(int gameId, Event event);
}
