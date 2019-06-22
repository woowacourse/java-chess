package chess.domain.pieces;

import chess.domain.Point;

import java.util.List;

public abstract class Piece {
    protected final Color color;

    Piece(Color color) {
        this.color = color;
    }

    abstract public List<Point> move(Point source, Point target);

    abstract public List<Point> attack(Point source, Point target);
}
