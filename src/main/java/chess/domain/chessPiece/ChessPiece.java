package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

public abstract class ChessPiece {

    private final Color color;
    private final String name;

    protected ChessPiece(Color color, String name) {
        this.color = color;
        this.name = color.convertByColor(name);
    }

    public abstract List<Position> getInitWhitePosition();

    public abstract List<Position> getInitBlackPosition();

    public boolean isBlack() {
        return color.isBlack();
    }

    public String getName() {
        return name;
    }
}
