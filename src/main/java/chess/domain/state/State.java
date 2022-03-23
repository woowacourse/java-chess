package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public abstract class State {

    private final Board board = new Board();

    public final Map<Position, Piece> getBoard() {
        return board.toMap();
    }
}
