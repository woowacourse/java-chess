package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class Knight extends ChessPiece {

    public Knight(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }
}
