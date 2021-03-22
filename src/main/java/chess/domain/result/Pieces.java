package chess.domain.result;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.state.State;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Score calculateScore() {
        Score sum = Score.ZERO;
        Map<Character, Integer> pawnCount = new HashMap<>();

        for (Piece piece : pieces) {
            sum = sumValue(piece, pawnCount, sum);
        }
        for (Integer numPawn : pawnCount.values()) {
            sum = adjustPawnScore(numPawn, sum);
        }
        return sum;
    }

    private Score adjustPawnScore(Integer numPawn, Score sum) {
        if (numPawn > 1) {
            sum = sum.add(new Score(-0.5 * numPawn));
        }
        return sum;
    }

    private Score sumValue(Piece piece, Map<Character, Integer> pawnCount, Score sum) {
        if (piece.getState() == State.ALIVE) {
            sum = sum.add(piece.getScore());
        }
        if (piece.getState() == State.ALIVE && piece instanceof Pawn) {
            pawnCount
                .put(piece.getColumn(), pawnCount.getOrDefault(piece.getColumn(), 0) + 1);
        }
        return sum;
    }
}
