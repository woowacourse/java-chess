package chess.dao;

import chess.model.board.Board;
import chess.model.state.State;

public interface StateDao {

    void save(State state);

    State find(Board board);

    void delete();

    void update(State now, State next);
}
