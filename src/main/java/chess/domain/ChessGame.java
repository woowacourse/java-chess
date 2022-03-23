package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;

import java.util.Map;

public class ChessGame {

    private State state = new Ready();

    public void start() {
    }

    public void end() {
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }
}
