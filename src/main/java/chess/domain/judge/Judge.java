package chess.domain.judge;

import chess.domain.piece.Side;

import java.sql.SQLException;
import java.util.Optional;

public interface Judge {
    double calculateScore(final Side side) throws SQLException;

    boolean isGameOver() throws SQLException;

    Optional<Side> winner() throws SQLException;

}
