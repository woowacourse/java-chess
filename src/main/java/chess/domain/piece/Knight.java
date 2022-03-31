package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final String SYMBOL = "N";
    private static final double SCORE = 2.5;

    public Knight(Team team, Position position) {
        super(team, SYMBOL, position, SCORE);
    }

    @Override
    public List<Position> findPath(Position destination) {
        findDirection(destination);
        return List.of();
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    protected List<Direction> getDirections() {
        return Arrays.asList(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
                Direction.EEN, Direction.EES, Direction.WWN, Direction.WWS);
    }
}