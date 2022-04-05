package chess.dao;

import chess.model.board.Board;
import chess.model.state.Ready;
import chess.model.state.State;
import chess.model.state.finished.End;
import chess.model.state.finished.Status;
import chess.model.state.running.BlackTurn;
import chess.model.state.running.WhiteTurn;

public class StateGenerator {

    public static State generateFrom(String stateName) {
        if (stateName.equals("end")) {
            return new End(new Board());
        }
        if (stateName.equals("blacktrun")) {
            return new BlackTurn(new Board());
        }
        if (stateName.equals("whiteturn")) {
            return new WhiteTurn(new Board());
        }
        if (stateName.equals("status")) {
            return new Status(new Board());
        }
        return new Ready();
    }
}
