package chess.domain.game;

import java.util.Optional;

public interface ChessGameRepository {

    ChessGame save(final ChessGame chessGame);

    Optional<ChessGame> findById(Long id);

    void update(final ChessGame chessGame);
}
