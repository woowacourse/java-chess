package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class EmptyPiece extends ChessPiece {

    public EmptyPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }
}
