package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final int DIRECTION_DOWN = 1;
    private static final int DIRECTION_UP = -1;

    private Pawn(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Pawn from(final Color color) {
        return new Pawn(PieceType.PAWN, color);
    }

    @Override
    public List<Position> findMoveAblePositions(final Position source, final Position target) {
        return createMovablePositions(source, target);
    }

    private List<Position> createMovablePositions(final Position source, final Position target) {
        final List<Position> result = new ArrayList<>();
        if (source.isSameRow(target.getRow()) && source.calculateColumnDistance(target.getColumn()) <= 2) {
            result.add(createMove(source, 1));
        }
        if (source.isSameRow(target.getRow()) && source.calculateColumnDistance(target.getColumn()) == 2 && isStartPosition(source)) {
            result.add(createMove(source, 2));
            return result;
        }
        if (isDiagonalMove(source, target)) {
            return List.of(target);
        }
        if (!result.contains(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 위치 입니다.");
        }
        return result;
    }

    private static boolean isDiagonalMove(final Position source, final Position target) {
        return source.calculateRowDistance(target.getRow()) == 1 && source.calculateColumnDistance(target.getColumn()) == 1;
    }

    private int directionDecider() {
        if (Color.WHITE == color) {
            return DIRECTION_UP;
        }
        return DIRECTION_DOWN;
    }

    private boolean isStartPosition(final Position source) {
        final int blackColorStartIndex = 1;
        final int whiteColorStartIndex = 6;
        return source.getColumn() == blackColorStartIndex || source.getColumn() == whiteColorStartIndex;
    }

    private Position createMove(final Position source, final int count) {
        final int pawnDirection = directionDecider();
        return Position.of(source.getRow(), source.getColumn() + count * pawnDirection);
    }
}
