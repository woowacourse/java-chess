package chess.repository;

import chess.domain.ChessGame;
import java.util.Optional;

public interface GameRepository {

    void save(ChessGame game);
    Optional<ChessGame> findById(Long id);
}
