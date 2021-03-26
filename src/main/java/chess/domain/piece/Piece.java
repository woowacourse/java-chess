package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;

public abstract class Piece {
    private static final String SAME_COLOR_ERROR = "[ERROR] taget에 같은 편 말이 있습니다.";

    protected Position position;
    protected final String name;
    protected final Color color;
    protected final Score score;

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
            throw new IllegalArgumentException(SAME_COLOR_ERROR);
        }
    }

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }
}
