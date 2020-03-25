package chess.domain.chessPiece;

import chess.domain.MovableStrategy.KnightMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;

public class Knight implements PieceType {

    public static final String NAME = "N";

    private final PieceColor pieceColor;
    private final MovableStrategy knightMovableStrategy;

    Knight(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.knightMovableStrategy = new KnightMovableStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
