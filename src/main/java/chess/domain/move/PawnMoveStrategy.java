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
        Piece piece = board.getPiece(source);
        Color color = piece.getColor();

        Distance distance = new Distance(source.subtractRow(target), source.subtractColumn(target));
        int horizon = distance.getHorizon();
        int vertical = distance.getVertical();
        MovePattern movePattern = MovePattern.of(horizon, vertical);

        if (color == Color.BLACK) {
            if (!BLACK_PAWN_MOVE_PATTERN.contains(movePattern)) {
                throw new IllegalStateException("[ERROR] 이동할 수 없습니다.");
            }
            if (movePattern == MovePattern.PAWN_START_MOVE_BLACK) {
                if (!source.isPawnStartPosition(color)) {
                    throw new IllegalStateException("[ERROR] 이동할 수 없습니다.");
                }
            }
        }

        // 색깔 & 처음이면 거리가 버티컬 2인지
        // 색깔 & 처음 아니면 거리가 버티컬 1인지
        // 색깔 & 대각일경우 거리가 ++ or --
        // 타겟이 없는지
        // 대각 타겟일 경우 타겟이 존재하는지

        return true;
    }
}
