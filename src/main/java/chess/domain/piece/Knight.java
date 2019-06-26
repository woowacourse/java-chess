package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MoveRule;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;

import java.util.Optional;

public class Knight extends Piece {
    private static final double SCORE = 2.5;
    private static final String NAME = "n";
    private static final int LIMIT_DISTANCE_KNIGHT = 3;

    public Knight(Team team) {
        super(team);
    }

    private static void validNightDirection(final Direction direction) {
        if (Direction.ALL_DIRECTION.stream().anyMatch(movable -> movable == direction)) {
            throw new InvalidDirectionException("움직일 수 있는 방향이 아닙니다.");
        }
    }

    @Override
    protected MoveRule setMoveRule() {
        return this::knight;
    }

    private boolean knight(Position source, Position target, Optional<Team> optionalTargetPieceTeam) {
        Direction direction = source.direction(target);
        validNightDirection(direction);
        validSameTeamCatch(optionalTargetPieceTeam);
        validDistance(source.distance(target), LIMIT_DISTANCE_KNIGHT);
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
