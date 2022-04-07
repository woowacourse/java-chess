package chess.dao.dto;

import chess.domain.ChessBoard;
import chess.domain.state.BlackTurn;
import chess.domain.state.BlackWin;
import chess.domain.state.End;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.state.WhiteTurn;
import chess.domain.state.WhiteWin;
import java.util.Arrays;
import java.util.function.Function;

public enum StateType {

    READY("ready", chessBoard -> new Ready()),
    WHITE_TURN("white turn", WhiteTurn::new),
    BLACK_TURN("black turn", BlackTurn::new),
    WHITE_WIN("white win", WhiteWin::new),
    BLACK_WIN("black win", BlackWin::new),
    END("end", End::new);

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
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다."));
    }

    public State parseState(ChessBoard chessBoard) {
        return function.apply(chessBoard);
    }

    public String getValue() {
        return value;
    }
}
