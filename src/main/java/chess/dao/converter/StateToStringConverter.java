package chess.dao.converter;

import chess.model.state.State;

public class StateToStringConverter {

    private static final String WHITE_TURN = "WHITE_TURN";
    private static final String BLACK_TURN = "BLACK_TURN";
    private static final String END = "END";

    public static String convert(State state) {
        if (state.isWhiteTurn()) {
            return WHITE_TURN;
        }
        if(state.isBlackTurn()) {
            return BLACK_TURN;
        }
        if(state.isFinished()) {
            return END;
        }
        throw new IllegalStateException("[ERROR] 변경할수 있는 상태가 아닙니다.");
    }
}
