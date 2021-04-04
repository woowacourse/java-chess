package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.piece.info.Score;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public abstract class Piece {
    private final Name name;
    private final Color color;
    private final Score score;
    private Position position;

    public Piece(Name name, Color color, Position position) {
        this(name, color, position, Score.ZERO);
    }

    public Piece(Name name, Color color, Position position, Score score) {
        this.name = name;
        this.color = color;
        this.position = position;
        this.score = score;
    }

    public void move(Position target) {
        this.position = target;
    }

    public String name() {
        return name.nameByColor(color);
    }

    public Color color() {
        return color;
    }

    public Position position() {
        return position;
    }

    protected double score() {
        return score.getValue();
    }

    public boolean isSameTeam(Piece targetPiece) {
        return this.color.same(targetPiece.color);
    }

    public boolean isSameTeam(Color anotherColor) {
        return this.color.same(anotherColor);
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public abstract void checkMovable(Piece targetPiece, Direction direction);
}
