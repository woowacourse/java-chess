package chess.domain.chessPiece;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.MovableStrategy.PawnMovableStrategy;

public class Pawn implements PieceType {

    public static final String NAME = "P";

    private final PieceColor pieceColor;
    private final MovableStrategy pawnMovableStrategy;

    Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.pawnMovableStrategy = new PawnMovableStrategy(pieceColor);
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
