package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;
import java.util.List;

public class Rook extends MultiStepPiece {

    private static final double ROOK_SCORE = 5;

    public Rook(Team team, Position position) {
        super(team, position);
    }

    @Override
    public double getScore() {
        return ROOK_SCORE;
    }

    @Override
    public List<Position> findPath(Position destination) {
        return super.findPath(destination, Direction.linearDirection());
    }
}
