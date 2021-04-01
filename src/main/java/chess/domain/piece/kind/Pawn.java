package chess.domain.piece.kind;

import static chess.domain.piece.Color.*;
import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Point;
import chess.domain.board.PositionValue;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Pawn extends Piece {
    public static final int INITIAL_BLACK_PAWN_ROW = 1;
    public static final int INITIAL_WHITE_PAWN_ROW = 6;
    private static final int DEFAULT_PAWN_SCORE = 1;
    private static final int INITIAL_POSSIBLE_DISTANCE_OF_PAWN = 4;
    private static final String PAWN_NAME = "p";

    private static final List<Direction> whitePawnDirection = Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
    private static final List<Direction> blackPawnDirection = Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);

    public Pawn(Color color) {
        super(PAWN_NAME, color);
        this.image = makeImage();
    }

    private String makeImage() {
        if (this.color.isSameAs(Color.BLACK)) {
            return "black_pawn.png";
        }
        return "white_pawn.png";
    }

    @Override
    public void checkCorrectDistance(Point sourcePoint, Point targetPoint, Piece target) {
        int distance = sourcePoint.calculateDistance(targetPoint);
        if (distance == MOVE_STRAIGHT_ONE_SQUARE && target.isEmptyPiece()) {
            return;
        }
        if (distance == MOVE_DIAGONAL_ONE_SQUARE && !target.isEmptyPiece()) {
            return;
        }
        if (distance == INITIAL_POSSIBLE_DISTANCE_OF_PAWN && target.isEmptyPiece()
            && (isInitialBlackPawn(sourcePoint) || isInitialWhitePawn(sourcePoint))) {
            return;
        }
        throw new IllegalArgumentException(IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
    }

    @Override
    public void checkCorrectDirection(Direction direction) {
        if (this.color.equals(WHITE) && !whitePawnDirection.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        if (this.color.equals(BLACK) && !blackPawnDirection.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    private boolean isInitialWhitePawn(Point point) {
        return this.color.equals(WHITE) && point.isSameRow(new PositionValue(INITIAL_WHITE_PAWN_ROW));
    }

    private boolean isInitialBlackPawn(Point point) {
        return this.color.equals(BLACK) && point.isSameRow(new PositionValue(INITIAL_BLACK_PAWN_ROW));
    }

    @Override
    public double score() {
        return DEFAULT_PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
