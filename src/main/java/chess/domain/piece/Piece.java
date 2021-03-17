package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Piece {
    private Position position;
    private final boolean isBlack;
    private final List<Direction> directions;
    private final int stepRange;

    public Piece(final boolean isBlack, final char x, final char y, final List<Direction> directions, final int stepRange) {
        this.position = new Position(x, y);
        this.isBlack = isBlack;
        this.directions = new ArrayList<>(directions);
        this.stepRange = stepRange;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position getPosition() {
        return position;
    }

    public List<Direction> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public int getStepRange() {
        return stepRange;
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }

    public boolean isSameColor(Piece other) {
        return this.isBlack == other.isBlack;
    }

    abstract char getName();
}