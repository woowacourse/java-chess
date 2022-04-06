package chess.dao;

import chess.domain.board.Board;
import chess.domain.state.Black;
import chess.domain.state.End;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.state.White;

public class StateConverter {

    public static State of(String state, Board board) {
        if (state.equals("black")) {
            return new Black(board);
        }
        if (state.equals("white")) {
            return new White(board);
        }
        if (state.equals("ready")) {
            return new Ready(board);
        }
        if (state.equals("end")) {
            return new End(board);
        }
        throw new IllegalArgumentException("[ERROR] 상태가 존재하지 않습니다.");
    }
}
