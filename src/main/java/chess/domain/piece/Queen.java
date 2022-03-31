package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Queen extends MultiStepPiece {

    private static final double QUEEN_SCORE = 9;

    public Queen(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return QUEEN_SCORE;
    }

    @Override
    public List<Position> findPath(Position destination) {
        return super.findPath(destination, Direction.everyDirection());
    }
}
