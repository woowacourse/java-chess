package domain.piece;

import dao.Movement;
import domain.Turn;
import domain.point.Direction;
import domain.point.Point;
import util.ExceptionMessages;

import java.util.List;
import java.util.Map;

public class Empty extends Piece {

    public static final int SCORE = 0;

    public Empty() {
        super(null);
    }

    @Override
    public boolean canMove(Movement movement, Map<Point, Piece> status, Turn turn) {
        throw new IllegalArgumentException(ExceptionMessages.TARGET_PIECE_NOT_FOUND);
    }

    @Override
    protected List<Point> findSpecializedPoints(Movement movement, Map<Point, Piece> status) {
        return List.of();
    }

    @Override
    public Map<Direction, Integer> getMovableDirectionAndRange() {
        return null;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public float getScore(List<Piece> line) {
        return SCORE;
    }

    @Override
    protected String getInitial() {
        return ".";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
