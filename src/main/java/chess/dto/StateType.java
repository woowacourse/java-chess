package chess.dto;

import chess.domain.board.Board;
import chess.domain.state.BlackTurn;
import chess.domain.state.End;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.state.WhiteTurn;

import java.util.Arrays;
import java.util.function.Function;

public enum StateType {

    READY("ready", Ready::new),
    WHITE_TURN("WhiteTurn", WhiteTurn::new),
    BLACK_TURN("BlackTurn", BlackTurn::new),
    END("End", (board) -> new End()),
    ;

    private final String type;
    private final Function<Board, State> generate;

    StateType(final String type, final Function<Board, State> generate) {
        this.type = type;
        this.generate = generate;
    }

    public static String move(final String now) {
        if (now.equals(WHITE_TURN.type)) {
            return BLACK_TURN.type;
        }
        if (now.equals(BLACK_TURN.type)) {
            return WHITE_TURN.type;
        }
        return now;
    }

    public static State getState(final String type, final Board board) {
        return Arrays.stream(StateType.values())
                .filter(stateType -> stateType.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다."))
                .generate
                .apply(board);
    }
}
