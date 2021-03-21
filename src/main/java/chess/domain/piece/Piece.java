package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Name;

public abstract class Piece {
    protected Position position;
    protected Name name;
    protected Color color;
    protected Score score;

    public Piece(Position position, Name name, Color color) {
        this(position, name, color, Score.ZERO);
    }

    public Piece(Position position, Name name, Color color, Score score) {
        this.position = position;
        this.name = name;
        this.color = color;
        this.score = score;
    }

    public abstract void move(Position target, Pieces pieces);

    public abstract void checkMoveRule(Position target);

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name.nameByColor(color);
    }

    public Score getScore() {
        return score;
    }

    public boolean isSameTeam(Piece targetPiece) {
        return this.color.same(targetPiece.color);
    }

    public boolean isSameTeam(Color anotherColor) {
        return this.color.same(anotherColor);
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }
}
