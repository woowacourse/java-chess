package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.List;

public abstract class ChessPiece {

    private final Color color;
    private final String name;

    protected ChessPiece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public abstract List<Position> getInitWhitePosition();

    public abstract List<Position> getInitBlackPosition();

    public abstract void checkMovable(Position from, Position to);

    public abstract double getScore();

    public abstract String convertToImageName();

    public boolean isSameColorPiece(ChessPiece chessPiece) {
        return this.color == chessPiece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public String getName() {
        return name;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public Color getColor() {
        return color;
    }
}
