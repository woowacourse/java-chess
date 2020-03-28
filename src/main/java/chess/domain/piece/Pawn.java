package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Pawn extends Piece {
    public Pawn(char representation, Position position) {
        super(representation, position);
    }
}
