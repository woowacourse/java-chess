package chess.domain.state;

import chess.domain.ChessBoard;
import java.util.Arrays;
import java.util.function.Function;

public enum StateType {

    READY("ready", chessBoard -> new Ready()),
    WHITE_TURN("white turn", chessBoard -> new WhiteTurn(chessBoard)),
    BLACK_TURN("black turn", chessBoard -> new BlackTurn(chessBoard)),
    WHITE_WIN("white win", chessBoard -> new WhiteWin(chessBoard)),
    BLACK_WIN("black win", chessBoard -> new BlackWin(chessBoard)),
    END("end", chessBoard -> new End(chessBoard));

    private final String value;
    private final Function<ChessBoard, State> function;

    StateType(String value, Function<ChessBoard, State> function) {
        this.value = value;
        this.function = function;
    }

    public static StateType of(String value) {
        return Arrays.stream(values())
                .filter(stateGenerator -> stateGenerator.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public State parseState(ChessBoard chessBoard) {
        return function.apply(chessBoard);
    }

    public String getValue() {
        return value;
    }
}
