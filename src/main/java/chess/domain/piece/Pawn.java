package chess.domain.piece;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.DirectionDecider;
import java.util.List;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public class Pawn extends Piece {

    private static final String INVALID_DIRECTION_PAWN = "Pawn이 갈 수 없는 방향입니다.";
    private static final String INVALID_DISTANCE_PAWN = "Pawn이 갈 수 없는 거리입니다.";
    private static final double PAWN_SCORE = 1.0;

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
    public Direction matchDirection(Position from, Position to) {
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
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return PAWN_SCORE;
    }
}
