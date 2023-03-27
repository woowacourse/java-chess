package chess.repository.dao;

import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import java.util.List;

public interface ChessGameDao {

    List<ChessGameDto> findAll();

    void save(final ChessGame chessGame);

    int findLastInsertId();

    ChessGameDto findById(final int id);

    void update(final ChessGame chessGame);
}
