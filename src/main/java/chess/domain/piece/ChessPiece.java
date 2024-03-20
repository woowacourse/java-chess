package chess.domain.piece;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.strategy.MoveStrategy;

public abstract class ChessPiece implements Piece {
    private final PieceInfo pieceInfo;
    private final MoveStrategy moveStrategy;

    public ChessPiece(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        this.pieceInfo = pieceInfo;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public boolean move(Position newPosition, Board board) {
        Position currentPosition = pieceInfo.getPosition();

        return moveStrategy.canMove(currentPosition, newPosition);
    }

    @Override
    public abstract PieceType getType();

    @Override
    public PieceInfo getPieceInfo() {
        return pieceInfo;
    }
}
