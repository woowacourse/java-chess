package chess.domain.chessPiece;

import java.util.List;

public abstract class ChessPiece {

    private final Color color;
    private final String name;

    protected ChessPiece(Color color, String name) {
        this.color = color;
        this.name = color.convertByColor(name);
    }

    public abstract List<String> getInitWhitePosition();

    public abstract List<String> getInitBlackPosition();

    public boolean isBlack() {
        return color.isBlack();
    }

    public String getName() {
        return name;
    }
}
