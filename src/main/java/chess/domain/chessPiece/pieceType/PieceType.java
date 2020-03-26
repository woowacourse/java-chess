package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;

import java.util.Objects;

public abstract class PieceType {

    protected final PieceColor pieceColor;
    protected final MovableStrategy movableStrategy;

    public PieceType(PieceColor pieceColor, MovableStrategy movableStrategy) {
        Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
        Objects.requireNonNull(movableStrategy, "피스 전략이 null입니다.");
        this.pieceColor = pieceColor;
        this.movableStrategy = movableStrategy;
    }

//    boolean canMove(Position source, Position target) {
//
//    }

    public abstract String getName();
}
