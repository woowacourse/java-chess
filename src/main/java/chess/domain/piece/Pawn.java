package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class Pawn extends ChessPiece {

    public Pawn(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }
}
