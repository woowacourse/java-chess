package domain.piece;

import domain.position.Position;

import java.util.List;

public abstract class Piece {

    private final PieceName name;
    private final Color color;

    protected Piece(PieceName name, Color color) {
        this.name = name;
        this.color = color;
    }

    public PieceName getPieceType() {
        return name;
    }

    public String getName() {
        if (isBlack()) {
            return name.getBlack();
        }
        return name.getWhite();
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public abstract boolean isMovableRoute(List<Position> routeFromStartToEnd);
}
