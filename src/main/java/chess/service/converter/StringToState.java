package chess.service.converter;

import chess.model.board.Board;
import chess.model.state.Ready;
import chess.model.state.State;
import chess.model.state.finished.End;
import chess.model.state.finished.Status;
import chess.model.state.running.BlackTurn;
import chess.model.state.running.WhiteTurn;

public class StringToState {

    public static State convert(String stateName, Board board) {
        if(stateName.equals("READY")) {
            return new Ready();
        }
        if (stateName.equals("WHITE_TURN")) {
            return new WhiteTurn(board);
        }
        if (stateName.equals("BLACK_TURN")) {
            return new BlackTurn(board);
        }
        if (stateName.equals("END")){
            return new End(board);
        }
        if (stateName.equals("STATUS")) {
            return new Status(board);
        }
        throw new IllegalArgumentException("[ERROR] 일치하는 상태가 없습니다.");
    }
}
