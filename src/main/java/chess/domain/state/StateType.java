package chess.domain.state;

import chess.domain.board.Board;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum StateType {
    BLACK_TURN("blackTurn"),
    WHITE_TURN("whiteTurn"),
    BLACK_WIN("blackWin"),
    WHITE_WIN("whiteWin"),
    TERMINATE("terminate")
    ;

    private static final Map<String, Function<Board, GameState>> stateSupplier = new HashMap<>() {{
        put(StateType.BLACK_TURN.getName(), BlackTurn::new);
        put(StateType.WHITE_TURN.getName(), WhiteTurn::new);
        put(StateType.BLACK_WIN.getName(), BlackWin::new);
        put(StateType.WHITE_WIN.getName(), WhiteWin::new);
        put(StateType.TERMINATE.getName(), Terminate::new);
    }};

    public static GameState createState(String stateName, Board board) {
        return stateSupplier.get(stateName)
                .apply(board);
    }

    private final String name;

    StateType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
