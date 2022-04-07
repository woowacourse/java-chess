package chess.domain.state;

import chess.domain.board.Board;
import java.util.Map;

public abstract class GameStarted implements GameState {

    protected final Board board;

    public GameStarted(Board board) {
        this.board = board;
    }

    public static GameState of(Board board, String turn) {
        if (turn.equals("white_turn")) {
            return new WhiteTurn(board);
        }
        if (turn.equals("black_turn")) {
            return new BlackTurn(board);
        }
        if (turn.equals("white_win")) {
            return new WhiteWin(board);
        }
        if (turn.equals("black_win")) {
            return new BlackWin(board);
        }
        if (turn.equals("terminated")) {
            return new Terminate(board);
        }
        throw new IllegalStateException("존재하지 않는 상태입니다.");
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = board.toMap();
        map.put("turn", getStateName());
        return map;
    }
}
