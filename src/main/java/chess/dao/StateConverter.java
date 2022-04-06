package chess.dao;

import chess.domain.board.Board;
import chess.domain.state.Black;
import chess.domain.state.End;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.domain.state.White;

public class StateConverter {

    public static State of(String state) {
        System.out.println(state);
        if (state.equals("black")) {
            return new Black(new Board());
        }

        if (state.equals("white")) {
            return new White(new Board());
        }

        if (state.equals("ready")) {
            return new Ready();
        }
        if (state.equals("end")) {
            return new End(new Board());
        }
        throw new IllegalArgumentException("[ERROR] 상태가 존재하지 않습니다.");
    }
}
