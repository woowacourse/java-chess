package chess.domain.piece;

import chess.domain.position.Position;

public class RealPiece extends Piece{
    private final Color color;

    public RealPiece(Position position, String notation, Color color) {
        super(position, notation);
        this.color = color;
    }
}
