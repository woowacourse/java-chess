package chess.domain.piece;

import chess.domain.Color;
import java.util.List;

public final class Pawn extends Piece {
    //TODO 첫수에만 2칸전진 가능, 상대기물 있을 시 대각 전진 이동 가능, 앙파상 처리 필요
    // 왼쪽 오른쪽 이동과 후진은 금지 처리 완료임
    private static final List<List<Integer>> MOVABLE_DISTANCES = List.of(
            List.of(0, 1));

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean movable(List<Integer> distances, Piece target) {
        boolean isMovableDistance = MOVABLE_DISTANCES.contains(distances);
        boolean isOpponent = isOpponent(target);

        return isMovableDistance && isOpponent;
    }
}
