package chess.domain.player;

import chess.domain.State;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Score calculateScore() {
        // 리스트를 돌면서
        Score sum = Score.ZERO;
        Map<Character, Integer> pawnCount = new HashMap<>();
        for (Piece piece : pieces) {
            // 살았는지 확인
            if (piece.getState() == State.ALIVE) {
                // 살았으면 각가의 점수를 더해준다.
                sum = sum.add(piece.getScore());
                if (piece instanceof Pawn) {
                    pawnCount
                        .put(piece.getColumn(), pawnCount.getOrDefault(piece.getColumn(), 0) + 1);
                }
            }
        }

        for (Integer numPawn : pawnCount.values()) {
            if (numPawn > 1) {
                sum = sum.add(new Score(-0.5 * numPawn));
            }
        }

        return sum;
    }


}
