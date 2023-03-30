package chess.dao;

import chess.dao.dto.ChessGameDto;
import chess.domain.game.ChessGame;
import java.util.List;
import java.util.Optional;

public interface ChessGameDao {

    void save(final ChessGame chessGame);

    Optional<ChessGameDto> findById(final Long id);

    Optional<ChessGameDto> findLatest();

    List<ChessGameDto> findAll();

    void update(final ChessGame chessGame);

    void delete(final Long id);
}
