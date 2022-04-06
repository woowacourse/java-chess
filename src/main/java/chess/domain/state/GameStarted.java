package chess.domain.state;

import chess.domain.board.Board;
import java.util.Map;

public abstract class GameStarted implements GameState {

    protected final Board board;

    public GameStarted(Board board) {
        this.board = board;
    }

    public static GameState of(Board board, String turn) {
        if (turn.equals("백팀 차례")) {
            return new WhiteTurn(board);
        }
        if (turn.equals("흑팀 차례")) {
            return new BlackTurn(board);
        }
        if (turn.equals("백팀 승리")) {
            return new WhiteWin(board);
        }
        if (turn.equals("흑팀 승리")) {
            return new BlackWin(board);
        }
        if (turn.equals("종료")) {
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
        map.put("turn", getTurn());
        return map;
    }
}
