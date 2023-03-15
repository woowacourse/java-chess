package domain.piece;

public abstract class Piece {

    private final PieceName name;
    private final Color color;

    public Piece(PieceName name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        if (isBlack()) {
            return name.getBlack();
        }
        return name.getWhite();
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }
}
