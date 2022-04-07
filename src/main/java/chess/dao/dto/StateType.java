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

    READY("ready", Ready.class, chessBoard -> new Ready()),
    WHITE_TURN("white turn", WhiteTurn.class, WhiteTurn::new),
    BLACK_TURN("black turn", BlackTurn.class, BlackTurn::new),
    WHITE_WIN("white win", WhiteWin.class, WhiteWin::new),
    BLACK_WIN("black win", BlackWin.class, BlackWin::new),
    END("end", End.class, End::new);

    private final String type;
    private final Class<? extends State> stateClass;
    private final Function<ChessBoard, State> function;

    StateType(String type, Class<? extends State> stateClass, Function<ChessBoard, State> function) {
        this.type = type;
        this.stateClass = stateClass;
        this.function = function;
    }

    public static StateType of(String value) {
        return Arrays.stream(values())
                .filter(stateGenerator -> stateGenerator.getType().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다."));
    }

    public static StateType of(State state) {
        return Arrays.stream(values())
                .filter(stateType -> stateType.stateClass == state.getClass())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다."));
    }

    public State parseState(ChessBoard chessBoard) {
        return function.apply(chessBoard);
    }

    public String getType() {
        return type;
    }
}
