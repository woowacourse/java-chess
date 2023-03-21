package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class ChessGame {

    private State state;
    private final Board board;

    public ChessGame(final Board board) {
        this.state = State.RUN;
        this.board = board;
    }

    public void movePiece(final Position source, final Position target) {
        checkPlayable();
        board.move(source, target);
    }

    private void checkPlayable() {
        if (state.isStart()) {
            return;
        }
        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }

    public void start() {
        this.state = State.START;
    }

    public void end() {
        this.state = State.END;
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public boolean isStart() {
        return state.isStart();
    }

    public Board getBoard() {
        return board;
    }
}
