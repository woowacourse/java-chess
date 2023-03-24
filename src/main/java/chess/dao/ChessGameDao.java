package chess.dao;

import chess.dao.dto.ChessGameDto;
import chess.domain.game.ChessGame;
import java.util.List;

public interface ChessGameDao {

    void save(final ChessGame chessGame);

    List<ChessGameDto> findAll();
}
