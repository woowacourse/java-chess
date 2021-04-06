package chess.domain.game;

import chess.exception.NotFoundStateException;

import java.util.Arrays;
import java.util.function.Function;

public enum StateFactory {
    READY("Ready", Ready::new),
    END("End", End::new),
    WHITE_TURN("WhiteTurn", WhiteTurn::new),
    BLACK_TURN("BlackTurn", BlackTurn::new);

    StateFactory(final String state, final Function<ChessGame, State> function) {
        this.state = state;
        this.function = function;
    }

    private final String state;
    private final Function<ChessGame, State> function;

    public static State valueOf(String inputState, final ChessGame chessGame) {
        return Arrays.stream(values())
                .filter(state -> state.state.equals(inputState))
                .findAny()
                .orElseThrow(NotFoundStateException::new)
                .function
                .apply(chessGame);
    }

}
