package chess.service.converter;

import chess.model.state.State;

public class StateToString {

    public static String convert(State state) {
        if (state.isReady() || state.isWhiteTurn()) {
            return "WHITE_TURN";
        }
        if(state.isBlackTurn()) {
            return "BLACK_TURN";
        }
        if(state.isFinished()) {
            return "END";
        }
        throw new IllegalStateException("[ERROR] 변경할수 있는 상태가 아닙니다.");
    }
}
