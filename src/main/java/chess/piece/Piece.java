package chess.piece;

import java.util.List;

public abstract class Piece {

    private final List<Direction> directions;
    private final PieceAttributes pieceAttributes;

    public Piece(List<Direction> directions, PieceAttributes pieceAttributes) {
        this.directions = directions;
        this.pieceAttributes = pieceAttributes;
    }

    public boolean hasAttributesOf(PieceAttributes pieceAttributes) {
        return this.pieceAttributes.equals(pieceAttributes);
    }
}
