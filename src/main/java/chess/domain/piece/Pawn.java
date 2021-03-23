package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.utils.MoveValidator;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {

    public static final double POINT = 1;
    public static final int BLACK_PAWN_START_LINE = 7;
    public static final int WHITE_PAWN_START_LINE = 2;
    public static final int MOVE_FIRST_RANGE = 2;
    public static final int MOVE_DEFAULT_RANGE = 1;
    private static final int DIAGONAL_MOVE_RANGE = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public Strategy strategy() {
        if (isBlackTeam()) {
            return new Strategy(Direction.blackPawnDirection(), MOVE_FIRST_RANGE);
        }
        return new Strategy(Direction.whitePawnDirection(), MOVE_FIRST_RANGE);
    }

    @Override
    public String getName() {
        if (isBlackTeam()) {
            return "P";
        }
        return "p";
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public List<Position> generate(Path path) {
        final Direction direction = path.computeDirection();
        final Strategy strategy = strategy();
        strategy.moveTowards(direction); // 전략 상 갈 수 있는 방향인지 확인함.
        final int distance = path.computeDistance();

        if (path.isDiagonal()) { // 대각 방향인 경우
            MoveValidator.validateMoveRange(distance, Pawn.DIAGONAL_MOVE_RANGE);
            return super.generatePaths(path, direction, 0);
        }

        // 2칸 초과인지 확인.
        MoveValidator.validateStraightMove(distance);
        if (distance == Pawn.MOVE_FIRST_RANGE) {
            MoveValidator.validatePawnLocation(path.source());
        }
        return super.generatePaths(path, direction, distance + 2);
    }
}
