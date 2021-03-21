package chess.domain.player;

import chess.domain.State;
import chess.domain.piece.Piece;
import java.util.List;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Score calculateScore() {
        // 리스트를 돌면서
        Score sum = Score.ZERO;
        for (Piece piece : pieces) {
            // 살았는지 확인
            if (piece.getState() == State.ALIVE) {
                // 살았으면 각가의 점수를 더해준다.
                sum = sum.add(piece.getScore());
            }
        }

        return sum;
    }


}
