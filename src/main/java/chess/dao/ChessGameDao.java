package chess.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameDto;

public interface ChessGameDao {

    void save(final ChessGameDto chessGameDto);

    ChessGame findById(final int id);
}
