package chess.model.piece;

import chess.model.Direction;
import chess.model.Position;
import chess.model.Team;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final String BLACK_NAME = "R";
    private static final String WHITE_NAME = "r";
    private static final double SCORE = 5D;
    private static final List<Direction> directions = Direction.linear();

    public Rook(Team team) {
        super(team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return directions.contains(Direction.of(source, target));
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    @Override
    public List<Position> getIntervalPosition(Position source, Position target) {
        Direction direction = Direction.of(source, target);
        List<Position> positions = new ArrayList<>();
        Position next = source;

        while (!next.equals(target)) {
            next = next.getNext(direction);
            positions.add(next);
        }
        positions.remove(target);
        return positions;
    }
}
