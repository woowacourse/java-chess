package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class ChessPiece implements Piece {
    final PieceInfo pieceInfo;
    final MoveStrategy moveStrategy;

    public ChessPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        this.pieceInfo = pieceInfo;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public boolean move(Position newPosition, Board board, boolean isDisturbed) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return false;
        }
        if (isDisturbed) {
            return false;
        }
        return true;
    }

    @Override
    public abstract PieceType getType();

    @Override
    public PieceInfo getPieceInfo() {
        return pieceInfo;
    }
}
