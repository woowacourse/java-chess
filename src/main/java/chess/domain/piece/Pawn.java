package chess.domain.piece;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.DirectionDecider;
import java.util.List;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import java.util.Map;

public class Pawn extends Piece {

    private static final String INVALID_DISTANCE_PAWN = "Pawn이 갈 수 없는 거리입니다.";
    private static final String PAWN_ONLY_DIAGONAL_CATCH = "폰은 본인 진행 방향 대각선에 있는 적만 잡을 수 있습니다.";
    private static final String CANNOT_MOVE_DIAGONAL_NOT_ENEMY = "폰은 적이 없으면 대각선으로 갈 수 없습니다.";

    private static final int BLACK_PAWN_INITIAL_ROW = 7;
    private static final int WHITE_PAWN_INITIAL_ROW = 2;

    private static final int PAWN_INITIAL_DISTANCE = 2;
    private static final int PAWN_BASIC_DISTANCE = 1;

    private static final List<Direction> BLACK_DIRECTIONS = List.of(BasicDirection.SOUTH, DiagonalDirection.SOUTH_EAST, DiagonalDirection.SOUTH_WEST);
    private static final List<Direction> WHITE_DIRECTIONS = List.of(BasicDirection.NORTH, DiagonalDirection.NORTH_EAST, DiagonalDirection.NORTH_WEST);

    private static final Pawn whitePawn = new Pawn(Color.WHITE);
    private static final Pawn blackPawn = new Pawn(Color.BLACK);


    public Pawn(Color color) {
        super(color);
    }

    public static Pawn createWhite() {
        return whitePawn;
    }

    public static Pawn createBlack() {
        return blackPawn;
    }

    @Override
    protected Direction matchDirection(Position from, Position to) {
        Direction findDirection = matchDirectionByColor(from, to);
        if (checkFirstMove(from, to, findDirection) || checkMove(from, to)) {
            return findDirection;
        }
        throw new IllegalArgumentException(INVALID_DISTANCE_PAWN);
    }

    private Direction matchDirectionByColor(Position from, Position to) {
        if (this.color == Color.WHITE) {
            return DirectionDecider.generateUnitPosition(WHITE_DIRECTIONS, from, to);
        }
        return DirectionDecider.generateUnitPosition(BLACK_DIRECTIONS, from, to);
    }

    private boolean checkFirstMove(Position from, Position to, Direction direction) {
        if (this.color == Color.BLACK) {
            return from.isSameRow(BLACK_PAWN_INITIAL_ROW) && !direction.isDiagonal()
                    && from.canReachUnderThreshold(to, PAWN_INITIAL_DISTANCE);
        }
        return from.isSameRow(WHITE_PAWN_INITIAL_ROW) && !direction.isDiagonal()
                && from.canReachUnderThreshold(to, PAWN_INITIAL_DISTANCE);
    }

    private boolean checkMove(Position from, Position to) {
        return from.canReachUnderThreshold(to, PAWN_BASIC_DISTANCE);
    }

    @Override
    public void validateMove(Position from, Position to, Map<Position, Piece> pieces) {
        Direction direction = matchDirection(from, to);
        List<Position> path = from.backtrackPath(to);
        isNotBlocked(pieces, path);
        doesSatisfyPawnMoveCondition(to, pieces, direction);
    }

    private void doesSatisfyPawnMoveCondition(Position to, Map<Position, Piece> pieces, Direction direction) {
        if (pieces.containsKey(to)) {
            validateSameColor(pieces.get(to));
            validateNotDiagonal(direction);
            return;
        }
        validateDiagonal(direction);
    }

    private void validateDiagonal(Direction direction) {
        if (direction.isDiagonal()) {
            throw new IllegalArgumentException(PAWN_ONLY_DIAGONAL_CATCH);
        }
    }

    private void validateNotDiagonal(Direction direction) {
        if (!direction.isDiagonal()) {
            throw new IllegalArgumentException(CANNOT_MOVE_DIAGONAL_NOT_ENEMY);
        }
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public String getImage() {
        if (this.color == Color.WHITE) {
            return "white_pawn.png";
        }
        return "black_pawn.png";
    }

    @Override
    public double getScore() {
        return 1.0;
    }

    @Override
    public String getName() {
        return "Pawn";
    }
}
