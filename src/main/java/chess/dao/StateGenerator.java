package chess.dao;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.Ready;
import chess.model.state.State;
import chess.model.state.finished.End;
import chess.model.state.finished.Status;
import chess.model.state.running.BlackTurn;
import chess.model.state.running.WhiteTurn;
import java.util.Map;

public class StateGenerator {

    public static State generateState(String stateName, Map<Position, Piece> board) {
        if (stateName.equals("end")) {
            return new End(Board.from(board));
        }
        if (stateName.equals("blacktrun")) {
            return new BlackTurn(Board.from(board));
        }
        if (stateName.equals("whiteturn")) {
            return new WhiteTurn(Board.from(board));
        }
        if (stateName.equals("status")) {
            return new Status(Board.from(board));
        }
        return new Ready();
    }
}
