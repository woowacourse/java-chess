package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final String SYMBOL = "N";
    private static final float SCORE = 2.5f;

    public Knight(Team team) {
        super(team, SYMBOL, SCORE);
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        findDirection(source, destination);
        return List.of();
    }

    @Override
    protected List<Direction> getDirections() {
        return Arrays.asList(Direction.NNE, Direction.NNW, Direction.SSE, Direction.SSW,
                Direction.EEN, Direction.EES, Direction.WWN, Direction.WWS);
    }
}