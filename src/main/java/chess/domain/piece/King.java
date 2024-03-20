package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class King extends ChessPiece {

    public King(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
