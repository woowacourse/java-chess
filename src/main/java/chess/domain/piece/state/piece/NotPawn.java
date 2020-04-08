package chess.domain.piece.state.piece;

import chess.domain.board.Board;
import chess.domain.piece.score.Score;

public abstract class NotPawn extends Initialized {
    public NotPawn(InitializedBuilder builder) {
        super(builder);
    }


    @Override
    public Score calculateScore(Board board) {
        return score;
    }
}
