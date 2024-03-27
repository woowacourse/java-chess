package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Arrays;

public class Turn {

    private State state;

    private Turn(State state) {
        this.state = state;
    }

    public static Turn create() {
        return new Turn(State.WAIT);
    }

    public void startGame() {
        if (state == State.END) {
            throw new IllegalStateException("게임을 시작할 수 없는 상태입니다");
        }
        if (state == State.WAIT) {
            state = State.WHITE;
        }
    }

    public void pauseGame() {
        state = State.inactive(state.color);
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

    public boolean isEnd() {
        return state == State.END;
    }

    public boolean isValidTurn(Piece piece) {
        return piece.isSameColor(state.color);
    }

    public String state() {
        return this.state.name();
    }

    private enum State {
        WAIT(Color.EMPTY),
        WHITE(Color.WHITE),
        WHITE_INACTIVE(Color.WHITE),
        BLACK(Color.BLACK),
        BLACK_INACTIVE(Color.BLACK),
        END(Color.EMPTY);

        private final Color color;

        State(Color color) {
            this.color = color;
        }

        private static State inactive(Color color) {
            return Arrays.stream(values())
                    .filter(value -> value.name().contains("INACTIVE"))
                    .filter(value -> value.color == color)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 상태 설정입니다"));
        }
    }
}
