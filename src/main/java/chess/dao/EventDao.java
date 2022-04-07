package chess.dao;

import chess.domain.event.Event;
import chess.domain.event.MoveCommand;
import java.util.List;

public interface EventDao {

    List<Event> findAllByGameId(int gameId);

    void saveMove(int gameId, MoveCommand moveCommand);
}
