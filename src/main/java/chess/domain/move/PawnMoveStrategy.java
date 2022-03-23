package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class PawnMoveStrategy {

    private final static List<MovePattern> BLACK_MOVE_PATTERN = List.of(
            MovePattern.SOUTH,
            MovePattern.SOUTHEAST,
            MovePattern.SOUTHWEST,
            MovePattern.PAWN_START_MOVE_BLACK
    );
    private final static List<MovePattern> WHITE_MOVE_PATTERN = List.of(
            MovePattern.NORTH,
            MovePattern.NORTHEAST,
            MovePattern.NORTHWEST,
            MovePattern.PAWN_START_MOVE_WHITE
    );

    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = new Distance(source.subtractRow(target), source.subtractColumn(target));
        final int horizon = distance.getHorizon();
        final int vertical = distance.getVertical();

        final Color color = board.getPiece(source).getColor();
        final MovePattern movePattern = MovePattern.of(horizon, vertical);
        final Piece targetPiece = board.getPiece(target);

        if (color == Color.BLACK) {
            return isBlackMovePattern(movePattern, board, source, targetPiece, color);
        }
        return isWhiteMovePattern(movePattern, board, source, targetPiece, color);
    }

    private boolean isBlackMovePattern(final MovePattern movePattern, final Board board, final Position source, final Piece targetPiece, final Color color) {
        if (!BLACK_MOVE_PATTERN.contains(movePattern)) {
            return false;
        }
        if (movePattern == MovePattern.PAWN_START_MOVE_BLACK) {
            return isStartMove(board, source, targetPiece, color);
        }
        if (movePattern == MovePattern.SOUTH) {
            return targetPiece.isBlank();
        }
        if (movePattern == MovePattern.SOUTHEAST || movePattern == MovePattern.SOUTHWEST) {
            return isCatchable(targetPiece, color);
        }
        return false;
    }

    private boolean isWhiteMovePattern(final MovePattern movePattern, final Board board, final Position source, final Piece targetPiece, final Color color) {
        if (!WHITE_MOVE_PATTERN.contains(movePattern)) {
            return false;
        }
        if (movePattern == MovePattern.PAWN_START_MOVE_WHITE) {
            return isStartMove(board, source, targetPiece, color);
        }
        if (movePattern == MovePattern.NORTH) {
            return targetPiece.isBlank();
        }
        if (movePattern == MovePattern.NORTHEAST || movePattern == MovePattern.NORTHWEST) {
            return isCatchable(targetPiece, color);
        }
        return false;
    }

    private boolean isStartMove(final Board board, final Position source, final Piece targetPiece, final Color color) {
        if (!source.isPawnStartPosition(color)) {
            return false;
        }
        return board.getPiece(source.move(0, findForward(color))).isBlank() && targetPiece.isBlank();
    }

    private int findForward(final Color color) {
        if (color == Color.BLACK) {
            return -1;
        }
        return 1;
    }

    private boolean isCatchable(final Piece targetPiece, final Color color) {
        return !targetPiece.isBlank() && targetPiece.getColor() == oppositeColor(color);
    }

    private Color oppositeColor(final Color color) {
        if (color == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
