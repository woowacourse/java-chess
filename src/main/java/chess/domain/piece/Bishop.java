package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MoveRule;
import chess.domain.Position;
import chess.domain.Team;

import java.util.Optional;

public class Bishop extends Piece {
    private static final String NAME = "b";
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(team);
    }

    @Override
    protected MoveRule setMoveRule() {
        return this::bishop;
    }

    private boolean bishop(Position source, Position target, Optional<Team> optionalTargetPieceTeam) {
        Direction direction = source.direction(target);
        validDirection(Direction.DIAGONAL_DIRECTION, direction);
        validSameTeamCatch(optionalTargetPieceTeam);
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
