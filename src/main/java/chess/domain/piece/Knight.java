package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public class Knight extends ChessPiece {
    
    public Knight(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public boolean move(Position newPosition, Board board, boolean isDisturbed) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return false;
        }
        return true;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
