package chess.domain.state;

import chess.domain.board.Board;
import java.util.Map;

public abstract class GameStarted implements GameState {

    protected final Board board;

    public GameStarted(Board board) {
        this.board = board;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = board.toMap();
        map.put("turn", getTurn());
        return map;
    }
}
