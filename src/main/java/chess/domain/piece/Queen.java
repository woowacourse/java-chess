package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class Queen extends ChessPiece {

    public Queen(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
