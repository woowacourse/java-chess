package domain.piece;

import domain.board.Board;
import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final String NAME = "p";
    private static final Score SCORE = new Score(1);

    public Pawn(boolean isBlack) {
        super(NAME, isBlack, SCORE);
    }

    public static Map<Position, Piece> createInitialPawns() {
        Map<Position, Piece> initialPawns = new HashMap<>();
        putPawns(initialPawns, 1, true);
        putPawns(initialPawns, 6, false);
        return initialPawns;
    }

    private static void putPawns(Map<Position, Piece> pawns, int row, boolean isBlack) {
        for (int column = 0; column < 8; column++) {
            pawns.put(new Position(row, column), new Pawn(isBlack));
        }
    }

    private List<Direction> findDirections() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    private boolean isForwardOneStepMovable(Map<Position, Piece> board, Direction direction, Position source,
        Position target) {
        return board.get(target).isEmpty() && source.sum(direction).equals(target);
    }

    private boolean isDiagonalMovable(Map<Position, Piece> board, List<Direction> directions, Position source,
        Position target) {
        return !board.get(target).isEmpty() && directions.stream()
            .anyMatch(direction -> source.sum(direction).equals(target));
    }

    private boolean isForwardTwoStepMovable(Map<Position, Piece> board, Direction direction, Position source,
        Position target) {
        if (!createInitialPawns().containsKey(source) || !board.get(target).isEmpty()) {
            return false;
        }
        Position firstStep = source.sum(direction);
        if (!board.get(target).isEmpty()) {
            return false;
        }
        Position secondStep = firstStep.sum(direction);
        return secondStep.equals(target);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position source, Position target) {
        List<Direction> directions = new ArrayList<>(findDirections());
        Direction forwardDirection = directions.remove(0);

        return isForwardOneStepMovable(board, forwardDirection, source, target)
            || isForwardTwoStepMovable(board, forwardDirection, source, target)
            || isDiagonalMovable(board, directions, source, target);
    }
}
