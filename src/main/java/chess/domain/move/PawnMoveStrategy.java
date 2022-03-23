package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.List;

public class PawnMoveStrategy {

    private final static List<MovePattern> PAWN_MOVE_PATTERN = List.of(MovePattern.NORTH,
            MovePattern.SOUTH,
            MovePattern.NORTHEAST,
            MovePattern.NORTHWEST,
            MovePattern.SOUTHEAST,
            MovePattern.SOUTHWEST,
            MovePattern.PAWN_START_MOVE_WHITE,
            MovePattern.PAWN_START_MOVE_BLACK);


    public boolean isMovable(final Board board, final Position source, final Position target) {
        Piece piece = board.getPiece(source);
        Color color = piece.getColor();

        Distance distance = new Distance(source.subtractRow(target), source.subtractColumn(target));
        int vertical = distance.getVertical();
        int horizon = distance.getHorizon();
        if (color == Color.BLACK) {
             validateStartMoveStrategy(source, color, vertical, horizon);
        }
        // 색깔 & 처음이면 거리가 버티컬 2인지
        // 색깔 & 처음 아니면 거리가 버티컬 1인지
        // 색깔 & 대각일경우 거리가 ++ or --
        // 타겟이 없는지
        // 대각 타겟일 경우 타겟이 존재하는지

        return true;
    }

    private void validateStartMoveStrategy(final Position source, final Color color, final int vertical, final int horizon) {
        if (!(source.isPawnStartPosition(color) && vertical >= -2 && vertical < 0 && horizon == 0)) {
            throw new IllegalStateException("[ERROR] 이동할 수 없습니다.");
        }
    }



}
