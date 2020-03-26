package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;

public class Rook extends PieceType {

    public static final String NAME = "R";

    public Rook(PieceColor pieceColor, MovableStrategy movableStrategy) {
        super(pieceColor, movableStrategy);
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
