package chess.domain.piece;

import chess.domain.*;

public class Pawn extends Piece {
    private static final String NAME = "p";
    private static final int SCORE = 1;
    private static final int LIMIT_DISTANCE_ONE = 1;

    public Pawn(Team team) {
        super(team, Team.pawnMoveRule(team));

    }

    // TODO: 이동 Rule 각 Piece 구현체들로 옮기기
    public boolean whitePawn(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(Direction.WHITE_PAWN_DIRECTION, direction);
        validDistance(source.distance(target, direction), LIMIT_DISTANCE_ONE);
        return true;
    }

    public boolean blackPawn(Position source, Position target) {
        Direction direction = source.direction(target);
        validDirection(Direction.BLACK_PAWN_DIRECTION, direction);
        validDistance(source.distance(target, direction), LIMIT_DISTANCE_ONE);
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
