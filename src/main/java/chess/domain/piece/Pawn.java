package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Pawn extends Piece {
    //TODO 상대기물 있을 시 대각 전진 이동 가능, 앙파상 처리 필요
    // 왼쪽 오른쪽 이동과 후진은 금지 처리 완료임
    private List<List<Integer>> MOVABLE_DISTANCES = List.of(
            List.of(0, 1), List.of(0, 2));

    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        boolean movable = isOpponent(target) && MOVABLE_DISTANCES.contains(distances);
        if (isFirstMove && movable) {
            isFirstMove = false;
            MOVABLE_DISTANCES = List.of(List.of(0, 1));
        }

        return movable;
    }
}
