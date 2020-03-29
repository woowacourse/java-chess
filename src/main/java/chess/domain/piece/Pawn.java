package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;
import chess.domain.piece.direction.DirectionsType;

import java.util.ArrayList;
import java.util.List;

import static chess.util.NullValidator.validateNull;

public class Pawn extends Piece {
    private static final String NAME = "p";
    public static final double PAWN_SCORE = 1;
    public static final double PAWN_HALF_SCORE = 0.5;
    private static final int NO_DISTANCE = 0;
    private static final int RIGHT_MAX_DISTANCE = 1;
    private static final int LEFT_MAX_DISTANCE = -1;
    private static final int BLACK_PAWN_FORWARD_MIN_DISTANCE = -1;
    private static final int BLACK_PAWN_FORWARD_MAX_DISTANCE = -2;
    private static final int WHITE_PAWN_FORWARD_MIN_DISTANCE = 1;
    private static final int WHITE_PAWN_FORWARD_MAX_DISTANCE = 2;

    public Pawn(PieceColor pieceColor, Position position) {
        super(NAME, PAWN_SCORE, pieceColor, position);
    }

    @Override
    public List<Position> getPathTo(Position targetPosition) {
        validateNull(targetPosition);
        validateEqualPosition(targetPosition);
        Direction direction = getDirection(targetPosition);
        validateDirection(getDirection(targetPosition));
        validateDistance(targetPosition);

        if (this.position.isYPointEqualsSeven() && direction.isSouth()) {
            return createPath(targetPosition, direction);
        }
        if (this.position.isYPointEqualsTwo() && direction.isNorth()) {
            return createPath(targetPosition, direction);
        }

        List<Position> path = new ArrayList<>();
        path.add(targetPosition);
        return path;
    }

    protected void validateDirection(Direction direction) {
        if (pieceColor.isBlack()) {
            validateDirectionAsPieceColor("BLACK_PAWN", direction);
        }
        if (pieceColor.isWhite()) {
            validateDirectionAsPieceColor("WHITE_PAWN", direction);
        }
    }

    private void validateDirectionAsPieceColor(String pieceName, Direction direction) {
        DirectionsType directionsType = DirectionsType.valueOf(pieceName);
        if (!directionsType.contains(direction)) {
            throw new IllegalArgumentException("폰이 이동할 수 없는 방향입니다.");
        }
    }

    @Override
    protected void validateDistance(Position targetPosition) {
        int xPointGap = this.position.getXPointGap(targetPosition);
        int yPointGap = this.position.getYPointGap(targetPosition);

        if (pieceColor.isBlack()) {
            validateBlackPawnDistance(xPointGap, yPointGap);
        }
        if (pieceColor.isWhite()) {
            validateWhitePawnDistance(xPointGap, yPointGap);
        }
    }

    private boolean isInvalidXPointGap(int xPointGap) {
        return !(xPointGap == NO_DISTANCE || xPointGap == LEFT_MAX_DISTANCE || xPointGap == RIGHT_MAX_DISTANCE);
    }

    private void validateBlackPawnDistance(int xPointGap, int yPointGap) {
        if (this.position.isYPointEqualsSeven()) {
            if (isInvalidXPointGap(xPointGap) || !(yPointGap == BLACK_PAWN_FORWARD_MIN_DISTANCE || yPointGap == BLACK_PAWN_FORWARD_MAX_DISTANCE)) {
                throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
            }
        } else {
            if (isInvalidXPointGap(xPointGap) || yPointGap != BLACK_PAWN_FORWARD_MIN_DISTANCE) {
                throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
            }
        }
    }

    private void validateWhitePawnDistance(int xPointGap, int yPointGap) {
        if (this.position.isYPointEqualsTwo()) {
            if (isInvalidXPointGap(xPointGap) || !(yPointGap == WHITE_PAWN_FORWARD_MIN_DISTANCE || yPointGap == WHITE_PAWN_FORWARD_MAX_DISTANCE)) {
                throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
            }
        } else {
            if (isInvalidXPointGap(xPointGap) || yPointGap != WHITE_PAWN_FORWARD_MIN_DISTANCE) {
                throw new IllegalArgumentException("폰이 이동할 수 없는 위치입니다.");
            }
        }
    }
}
