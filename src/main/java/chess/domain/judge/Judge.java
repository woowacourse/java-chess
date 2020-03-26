package chess.domain.judge;

import java.util.Optional;

import chess.domain.piece.Side;

public interface Judge {
    double calculateScore(final Side side);

    boolean isGameOver();

    Optional<Side> winner();

}
