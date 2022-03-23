package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class PawnMoveStrategy {

    private final static List<MovePattern> BLACK_PAWN_MOVE_PATTERN = List.of(
            MovePattern.SOUTH,
            MovePattern.SOUTHEAST,
            MovePattern.SOUTHWEST,
            MovePattern.PAWN_START_MOVE_BLACK
    );
    private final static List<MovePattern> WHITE_PAWN_MOVE_PATTERN = List.of(
            MovePattern.NORTH,
            MovePattern.NORTHEAST,
            MovePattern.NORTHWEST,
            MovePattern.PAWN_START_MOVE_WHITE
    );


    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Piece sourcePiece = board.getPiece(source);
        final Piece targetPiece = board.getPiece(target);

        final Distance distance = new Distance(source.subtractRow(target), source.subtractColumn(target));
        final int horizon = distance.getHorizon();
        final int vertical = distance.getVertical();

        Color color = sourcePiece.getColor();
        MovePattern movePattern = MovePattern.of(horizon, vertical);
        if (color == Color.BLACK) {
            if (!BLACK_PAWN_MOVE_PATTERN.contains(movePattern)) {
                return false;
            }
            // 2칸이동
            if (movePattern == MovePattern.PAWN_START_MOVE_BLACK) {
                if (!source.isPawnStartPosition(color)) {
                    return false;
                }
                return board.getPiece(source.move(0, -1)).isBlank() && targetPiece.isBlank();
            }
            // 1칸 전진
            if (movePattern == MovePattern.SOUTH) {
                return targetPiece.isBlank();
            }
            // 대각이동
            if (movePattern == MovePattern.SOUTHEAST || movePattern == MovePattern.SOUTHWEST) {
                return !targetPiece.isBlank() && targetPiece.getColor() == Color.WHITE;
            }
        }

        return true;
    }
}
