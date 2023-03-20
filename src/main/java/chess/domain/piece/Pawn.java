package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final int DIRECTION_DOWN = 1;
    private static final int DIRECTION_UP = -1;
    public static final int KING_MAX_MOVE_DISTANCE = 2;
    public static final int KING_SINGLE_MOVE_DISTANCE = 1;

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
        int columnDistance = source.calculateColumnDistance(target.getColumn());

        if (isSingleMoveAble(source, target, isEmptyTargetPiece, columnDistance)) {
            result.add(createMove(source, KING_SINGLE_MOVE_DISTANCE));
        }
        if (isDoubleMoveAble(source, target, isEmptyTargetPiece, columnDistance)) {
            result.add(createMove(source, KING_MAX_MOVE_DISTANCE));
        }
        if (isDiagonalMoveAble(source, target) && !isEmptyTargetPiece) {
            return List.of(target);
        }

        if (!result.contains(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }

        return result;
    }

    private boolean isDoubleMoveAble(final Position source, final Position target, final boolean isEmptyTargetPiece, final int columnDistance) {
        return source.isSameRow(target.getRow()) &&
                isEmptyTargetPiece &&
                columnDistance == KING_MAX_MOVE_DISTANCE && isStartPosition(source);
    }

    private static boolean isSingleMoveAble(final Position source, final Position target, final boolean isEmptyTargetPiece, final int columnDistance) {
        return source.isSameRow(target.getRow()) &&
                isEmptyTargetPiece &&
                columnDistance <= KING_MAX_MOVE_DISTANCE;
    }

    private void validateInvalidColor(final Piece targetPiece) {
        if (targetPiece.isSameColor(color)) {
            throw new IllegalArgumentException("같은 색깔의 기물을 선택할 수 없습니다.");
        }
    }

    private boolean isDiagonalMoveAble(final Position source, final Position target) {
        int diagonalPositionDistance = 1;
        return source.calculateRowDistance(target.getRow()) == diagonalPositionDistance &&
                source.calculateColumnDistance(target.getColumn()) == diagonalPositionDistance;
    }

    private int directionDecider() {
        if (Color.WHITE == color) {
            return DIRECTION_UP;
        }
        return DIRECTION_DOWN;
    }

    private boolean isStartPosition(final Position source) {
        final Column blackColorStartIndex = Column.COLUMN_7;
        final Column whiteColorStartIndex = Column.COLUMN_2;
        return source.isSameColumn(blackColorStartIndex) || source.isSameColumn(whiteColorStartIndex);
    }

    private Position createMove(final Position source, final int count) {
        final int pawnDirection = directionDecider();
        return Position.of(source.getRow(), source.getColumn().move(count * pawnDirection));
    }
}
