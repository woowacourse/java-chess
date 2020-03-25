package chess.domain.chessPiece;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.MovableStrategy.QueenMovableStrategy;

public class Queen implements PieceType {

    public static final String NAME = "Q";

    private final PieceColor pieceColor;
    private final MovableStrategy queenMovableStrategy;

    Queen(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.queenMovableStrategy = new QueenMovableStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
