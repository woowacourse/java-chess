package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MoveRule;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.IllegalTargetException;
import chess.domain.exceptions.InvalidDirectionException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Pawn extends Piece {
    private static final String NAME = "p";
    private static final int SCORE = 1;
    private static final int LIMIT_DISTANCE_FIRST = 2;

    private boolean first;

    public Pawn(Team team) {
        super(team);
        first = true;
    }

    @Override
    protected MoveRule setMoveRule() {
        if (Team.isSameTeam(this.getTeam(), Team.BLACK)) {
            return this::blackPawn;
        }
        return this::whitePawn;
    }

    private boolean whitePawn(Position source, Position target, Optional<Team> optionalTargetPieceTeam) {
        Direction direction = source.direction(target);
        validDirection(Direction.WHITE_PAWN_DIRECTION, direction);
        validSameTeamCatch(optionalTargetPieceTeam);
        pawnCatchValidation(direction, optionalTargetPieceTeam);
        validDistance(source.distance(target, direction), firstMoveDistance());
        changeFirstState();
        return true;
    }

    private int firstMoveDistance() {
        return first ? LIMIT_DISTANCE_FIRST : LIMIT_DISTANCE_ONE_UNIT;
    }

    private boolean blackPawn(Position source, Position target, Optional<Team> optionalTargetPieceTeam) {
        Direction direction = source.direction(target);
        validDirection(Direction.BLACK_PAWN_DIRECTION, direction);
        validDistance(source.distance(target, direction), firstMoveDistance());
        validSameTeamCatch(optionalTargetPieceTeam);
        pawnCatchValidation(direction, optionalTargetPieceTeam);
        changeFirstState();
        return true;
    }

    private void pawnCatchValidation(Direction direction, Optional<Team> optionalTargetPieceTeam) {
        if (!canMoveStraight(direction, optionalTargetPieceTeam) && !canMoveDiagonal(direction, optionalTargetPieceTeam)){
            throw new InvalidDirectionException("폰이 이동할 수 없는 방햡입니다.");
        }
    }

    private boolean canMoveStraight(final Direction direction, final Optional<Team> optionalTargetPieceTeam) {
        return !direction.isDiagonal() && !optionalTargetPieceTeam.isPresent();
    }

    private boolean canMoveDiagonal(final Direction direction, final Optional<Team> optionalTargetPieceTeam) {
        Team targetPieceTeam = optionalTargetPieceTeam.orElseThrow(() -> new InvalidDirectionException("폰이 이동할 수 없는 방햡입니다."));
        return direction.isDiagonal() && Team.isSameTeam(getEnemy(), targetPieceTeam);
    }

    private void changeFirstState() {
        this.first = false;
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