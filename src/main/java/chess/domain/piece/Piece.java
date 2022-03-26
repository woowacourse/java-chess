package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;
    private final MovingStrategy movingStrategy;

    protected Piece(PieceType pieceType, Color color, MovingStrategy movingStrategy) {
        this.pieceType = pieceType;
        this.color = color;
        this.movingStrategy = movingStrategy;
    }

    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        movingStrategy.validateMove(board, sourcePosition, targetPosition);
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isEmpty() {
        return false;
    }

    public String getNotation() {
        return pieceType.getNotation(color);
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public boolean isSamePieceType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }
}
