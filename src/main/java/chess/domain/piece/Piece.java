package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.MoveStrategy;
import java.util.List;
import java.util.Locale;

public abstract class Piece {

    protected final Name name;
    protected final Color color;
    protected final MoveStrategy moveStrategy;

    public Piece(Name name, Color color, MoveStrategy moveStrategy) {
        this.name = name;
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    public abstract boolean canMove(Piece targetPiece, Position from, Position to);

    public Color getColor() {
        return color;
    }

    public String getName() {
        if (color.equals(Color.WHITE)) {
            return name.getValue().toLowerCase(Locale.ROOT);
        }
        return name.getValue();
    }

    public boolean isKing() {
        return false;
    }

    public boolean isSameColor(Piece targetPiece) {
        return this.color == targetPiece.color;
    }

    public boolean isOppositeColor(Color color) {
        return (color == Color.WHITE && this.color == Color.BLACK) ||
                (color == Color.BLACK && this.color == Color.WHITE);
    }

    public List<Position> getRoute(Position from, Position to) {
        return moveStrategy.getRoute(from, to);
    }

    public boolean isPiece() {
        return true;
    }
}
