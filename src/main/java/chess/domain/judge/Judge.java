package chess.domain.judge;

import chess.domain.piece.Side;

public interface Judge {
    double calculateScore(final Side side);

    boolean isGameOver();

    boolean isDraw();

    Side winner();
}
