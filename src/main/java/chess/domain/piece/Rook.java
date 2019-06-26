package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MoveRule;
import chess.domain.Position;
import chess.domain.Team;

import java.util.Optional;

public class Rook extends Piece {
    private static final String NAME = "r";
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(team);
    }

    @Override
    protected MoveRule setMoveRule() {
        return this::rook;
    }

    private boolean rook(Position source, Position target, Optional<Team> optionalTargetPieceTeam) {
        Direction direction = source.direction(target);
        validDirection(Direction.CROSS_DIRECTION, direction);
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
