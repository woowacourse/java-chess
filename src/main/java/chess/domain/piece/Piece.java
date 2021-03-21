package chess.domain.piece;

public abstract class Piece {
    protected Position position;
    protected String name;
    protected Color color;
    protected Score score;

    public Piece(Position position, String name, Color color) {
        this(position, name, color, new Score(0));
    }

    public Piece(Position position, String name, Color color, Score score) {
        this.position = position;
        this.name = name;
        this.color = color;
        this.score = score;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public abstract void move(Position target, CurrentPieces currentPieces);

    public Color getColor() {
        return color;
    }

    public Score getScore() {
        return score;
    }

    public void validateSameColor(Piece piece) {
        if (this.isSameColor(piece)) {
            throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
        }
    }

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }
}
