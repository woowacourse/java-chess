package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class Turn {

    private State state;

    private Turn(State state) {
        this.state = state;
    }

    public static Turn create() {
        return new Turn(State.WAIT);
    }

    public void startGame() {
        if (state != State.WAIT) {
            throw new IllegalStateException("게임을 시작할 수 없는 상태입니다");
        }
        state = State.WHITE;
    }

    public void stopGame() {
        state = State.END;
    }

    public void oppositeTurn() {
        if (state == State.BLACK) {
            state = State.WHITE;
            return;
        }
        state = State.BLACK;
    }

    public boolean isActive() {
        return state == State.BLACK || state == State.WHITE;
    }

    public boolean isValidTurn(Piece piece) {
        return piece.isSameColor(state.color);
    }

    private enum State {
        WAIT(Color.EMPTY),
        WHITE(Color.WHITE),
        BLACK(Color.BLACK),
        END(Color.EMPTY);

        private final Color color;

        State(Color color) {
            this.color = color;
        }
    }
}
