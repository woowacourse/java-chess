package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MoveRule;
import chess.domain.Position;
import chess.domain.Team;

import java.util.Optional;

public class King extends Piece {
    private static final String NAME = "k";
    private static final double SCORE = 0;

    public King(Team team) {
        super(team);
    }

    @Override
    protected MoveRule setMoveRule() {
        return this::king;
    }

    private boolean king(Position source, Position target, Optional<Team> optionalTargetPieceTeam) {
        Direction direction = source.direction(target);
        validDirection(Direction.ALL_DIRECTION, direction);
        validSameTeamCatch(optionalTargetPieceTeam);
        validDistance(source.distance(target, direction), LIMIT_DISTANCE_ONE_UNIT);
        return true;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
