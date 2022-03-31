package chess.model.piece;

import chess.model.Direction;
import chess.model.Position;
import chess.model.Team;

import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private static final String BLACK_NAME = "K";
    private static final String WHITE_NAME = "k";
    private static final double SCORE = 0D;
    private static final List<Direction> directions = Direction.all();

    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return directions.contains(Direction.of(source, target))
                && source.isOneStepAway(target);
    }

    @Override
    public String getName() {
        if (Team.BLACK.equals(team)) {
            return BLACK_NAME;
        }
        return WHITE_NAME;
    }

    public List<Position> getIntervalPosition(Position source, Position target) {
        return Collections.emptyList();
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
