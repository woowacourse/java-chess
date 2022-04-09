package chess.dao;

import chess.domain.board.Board;
import chess.domain.state.Black;
import chess.domain.state.End;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.state.White;

public class StateConverter {

    public static State of(String state, Board board) {
        if ("black".equals(state)) {
            return new Black(board);
        }
        if ("white".equals(state)) {
            return new White(board);
        }
        if ("ready".equals(state)) {
            return new Ready(board);
        }
        if ("end".equals(state)) {
            return new End(board);
        }
        throw new IllegalArgumentException("[ERROR] 상태가 존재하지 않습니다.");
    }
}
