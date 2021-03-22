package chess.domain.position;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece2;
import chess.domain.piece.PieceColor;
import chess.domain.piece.strategy.MoveUnit;

public final class Position2 {

    private final Column column;
    private final Row row;
    private final Piece2 holdingPiece;

    public Position2(final Position2 position2, final Piece2 piece) {
        this(position2.column, position2.row, piece);
    }

    public Position2(final Column column, final Row row) {
        this(column, row, new EmptyPiece());
    }

    public Position2(final Column column, final Row row, final Piece2 holdingPiece) {
        this.column = column;
        this.row = row;
        this.holdingPiece = holdingPiece;
    }

    public Position2 replacePieceTo(Piece2 piece) {
        return new Position2(this, piece);
    }

//    public Position2 move(MoveUnit moveUnit) {
//
//    }

    public boolean isColor(PieceColor color) {
        return holdingPiece.isColor(color);
    }

    public boolean isEmpty() {
        return holdingPiece.isNothing();
    }
}
