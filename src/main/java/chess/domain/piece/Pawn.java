package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MoveRule;
import chess.domain.Position;
import chess.domain.Team;

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

    private boolean whitePawn(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(Direction.WHITE_PAWN_DIRECTION, direction);
        validDistance(source.distance(target, direction), firstMoveDistance());
        return true;
    }

    private int firstMoveDistance() {
        return first ? LIMIT_DISTANCE_FIRST : LIMIT_DISTANCE_ONE_UNIT;
    }

    private boolean blackPawn(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(Direction.BLACK_PAWN_DIRECTION, direction);
        validDistance(source.distance(target, direction), firstMoveDistance());
        return true;
    }

    @Override
    public void changeFirstState() {
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