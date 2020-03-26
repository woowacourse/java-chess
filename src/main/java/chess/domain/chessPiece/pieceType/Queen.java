package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;

public class Queen extends PieceType {

    public static final String NAME = "Q";

    public Queen(PieceColor pieceColor, MovableStrategy movableStrategy) {
        super(pieceColor, movableStrategy);
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
