package chess.domain.piece;

import chess.domain.piece.rule.Rule;
import chess.domain.position.Position;
import chess.domain.position.PositionDifference;

public abstract class Piece {

    private final Color color;
    private final Rule moveRule;

    public Piece(Color color, Rule moveRule) {
        this.color = color;
        this.moveRule = moveRule;
    }

    public boolean isSameColor(Piece piece) {
        return isSameColor(piece.color);
    }

    public boolean isSameColor(Color otherColor) {
        return color == otherColor;
    }

    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference = from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(moveRule);
    }

    public abstract boolean isCatchable(Position from, Position to);

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }
}
