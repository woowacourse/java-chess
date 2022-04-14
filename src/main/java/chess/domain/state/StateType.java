package chess.domain.state;

import chess.domain.ChessBoard;
import java.util.Arrays;
import java.util.function.Function;

public enum StateType {

    READY("ready", chessBoard -> new Ready()),
    WHITE_TURN("whiteTurn", WhiteTurn::new),
    BLACK_TURN("blackTurn", BlackTurn::new),
    WHITE_WIN("whiteWin", WhiteWin::new),
    BLACK_WIN("blackWin", BlackWin::new),
    END("end", End::new)
    ;

    private final String notation;
    private final Function<ChessBoard, State> function;

    StateType(String notation, Function<ChessBoard, State> function) {
        this.notation = notation;
        this.function = function;
    }

    public static StateType from(String notation) {
        return Arrays.stream(values())
                .filter(state -> state.getNotation().equals(notation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다."));
    }

    public State newState(ChessBoard chessBoard) {
        return function.apply(chessBoard);
    }

    public String getNotation() {
        return notation;
    }
}
