package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    public static final int PAWN_MAX_MOVE_DISTANCE = 2;
    public static final int PAWN_SINGLE_MOVE_DISTANCE = 1;

    private Pawn(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Pawn from(final Color color) {
        return new Pawn(PieceType.PAWN, color);
    }

    @Override
    public List<Position> findMoveAblePositions(final Position source, final Position target, final Piece targetPiece) {
        validateInvalidColor(targetPiece);

        final List<Position> result = new ArrayList<>();

        boolean isEmptyTargetPiece = targetPiece.isSamePieceType(PieceType.EMPTY);
        int columnDistance = source.calculateColumnDistance(target);

        if (isSingleMoveAble(source, target, isEmptyTargetPiece, columnDistance)) {
            result.add(createMove(source, PAWN_SINGLE_MOVE_DISTANCE));
        }
        if (isDoubleMoveAble(source, target, isEmptyTargetPiece, columnDistance)) {
            result.add(createMove(source, PAWN_MAX_MOVE_DISTANCE));
        }
        if (isDiagonalMoveAble(source, target) && !isEmptyTargetPiece) {
            return List.of(target);
        }

        if (!result.contains(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }

        return result;
    }

    private static boolean isSingleMoveAble(final Position source, final Position target, final boolean isEmptyTargetPiece, final int columnDistance) {
        return source.isSameRow(target) &&
                isEmptyTargetPiece &&
                columnDistance <= PAWN_MAX_MOVE_DISTANCE;
    }

    private Position createMove(final Position source, final int count) {
        final int pawnDirection = color.getDirection();
        return source.move(0, count * pawnDirection);
    }

    private boolean isDoubleMoveAble(final Position source, final Position target, final boolean isEmptyTargetPiece, final int columnDistance) {
        return source.isSameRow(target) &&
                isEmptyTargetPiece &&
                columnDistance == PAWN_MAX_MOVE_DISTANCE && isStartPosition(source);
    }

    private boolean isStartPosition(final Position source) {
        final Column blackColorStartIndex = Column.COLUMN_7;
        final Column whiteColorStartIndex = Column.COLUMN_2;
        return source.isSameColumn(blackColorStartIndex) || source.isSameColumn(whiteColorStartIndex);
    }

    private boolean isDiagonalMoveAble(final Position source, final Position target) {
        int diagonalPositionDistance = 1;
        return source.calculateRowDistance(target) == diagonalPositionDistance &&
                source.calculateColumnDistance(target) == diagonalPositionDistance;
    }
}
