package chess.domain.piece;

import chess.domain.board.*;
import chess.domain.utils.MoveValidator;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {

    private static final String NAME = "pawn";
    public static final double POINT = 1;
    public static final int BLACK_PAWN_START_LINE = 7;
    public static final int WHITE_PAWN_START_LINE = 2;
    public static final int MOVE_FIRST_RANGE = 2;
    public static final int MOVE_DEFAULT_RANGE = 1;
    private static final int DIAGONAL_MOVE_RANGE = 2;

    public Pawn(Team team) {
        super(team, NAME);
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
    public List<Position> generate(Path path, boolean target) {
        final Direction direction = path.computeDirection();
        final Strategy strategy = strategy();
        strategy.moveTowards(direction); // 전략 상 갈 수 있는 방향인지 확인함.
        final int distance = path.computeDistance();

        if (path.isDiagonal()) { // 대각 방향이면서 목적지에 상대팀의 말이 존재하는 경우
            // 이동가능한지 확인 -> 불가능하면 터뜨린다.
            MoveValidator.validateDiagonalMove(distance, Pawn.DIAGONAL_MOVE_RANGE, target);
            // 이동 가능하면 그냥 빈 배열을 전달한다.
            return new ArrayList<>();
        }

        // 2칸 초과인지 확인.
        MoveValidator.validateStraightMove(distance);
        if (distance == Pawn.MOVE_FIRST_RANGE) {
            MoveValidator.validatePawnLocation(path.source());
        }
        return super.generatePaths(path, direction, distance + 2);
    }

}
