package chess.domain.Piece.state;

import chess.domain.Piece.Piece;
import chess.domain.Position;

public abstract class Initialized implements Piece {
    protected Position position;
    protected Initialized(Position position) {
        this.position = position;
    }
}
