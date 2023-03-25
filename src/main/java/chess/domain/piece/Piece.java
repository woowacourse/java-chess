package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;

    protected Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public String getName() {
        if (isBlack()) {
            return pieceType.getBlackName();
        }
        return pieceType.getWhiteName();
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return pieceType.getScore();
    }

    public abstract boolean isMovableRoute(List<Position> routeFromStartToEnd);
}
