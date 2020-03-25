package chess.domain.chessPiece;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.MovableStrategy.RookMovableStrategy;

public class Rook implements PieceType {

    public static final String NAME = "R";

    private final PieceColor pieceColor;
    private final MovableStrategy rookMovableStrategy;

    Rook(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.rookMovableStrategy = new RookMovableStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
