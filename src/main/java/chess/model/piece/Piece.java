package chess.model.piece;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.piece.pawn.Pawn;
import chess.model.strategy.MovableStrategy;
import chess.model.strategy.move.MoveType;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private MovableStrategy strategy;

    public Piece(final Color color, final MovableStrategy strategy) {
        this.color = color;
        this.strategy = strategy;
    }

    public abstract double getPointValue();

    public boolean movable(final Square source, Square target, MoveType moveType) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("같은 위치로는 이동 불가능합니다.");
        }
        return this.strategy.movable(source, target, moveType);
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isAlly(Piece targetPiece) {
        return color.equals(targetPiece.color);
    }

    public boolean isPlayerPiece() {
        return color.isBlack() || color.isWhite();
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isEnemy(Piece targetPiece) {
        return color.isEnemy(targetPiece.color);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, strategy);
    }
}
