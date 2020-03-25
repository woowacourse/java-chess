package chess.domain.chessPiece;

import chess.domain.MovableStrategy.BishopMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;

public class Bishop implements PieceType {

    public static final String NAME = "B";

    private final PieceColor pieceColor;
    private final MovableStrategy bishopMovableStrategy;

    public Bishop(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.bishopMovableStrategy = new BishopMovableStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
