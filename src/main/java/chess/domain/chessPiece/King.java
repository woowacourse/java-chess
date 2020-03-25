package chess.domain.chessPiece;

import chess.domain.MovableStrategy.KingMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;

public class King implements PieceType {

    public static final String NAME = "K";

    private final PieceColor pieceColor;
    private final MovableStrategy kingMovableStrategy;

    King(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.kingMovableStrategy = new KingMovableStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }

}
