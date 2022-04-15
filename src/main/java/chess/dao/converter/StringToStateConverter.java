package chess.dao.converter;

import chess.model.board.Board;
import chess.model.state.State;
import chess.model.state.finished.End;
import chess.model.state.running.BlackTurn;
import chess.model.state.running.WhiteTurn;

public class StringToStateConverter {

    private static final String WHITE_TURN = "WHITE_TURN";
    private static final String BLACK_TURN = "BLACK_TURN";
    private static final String END = "END";

    public static State convert(String stateName, Board board) {
        if (WHITE_TURN.equals(stateName)) {
            return new WhiteTurn(board);
        }
        if (BLACK_TURN.equals(stateName)) {
            return new BlackTurn(board);
        }
        if (END.equals(stateName)) {
            return new End(board);
        }
        throw new IllegalArgumentException("[ERROR] 일치하는 상태가 없습니다.");
    }
}
