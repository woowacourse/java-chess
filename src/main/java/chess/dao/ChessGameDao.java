package chess.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameDto;

public interface ChessGameDao {

    int save(final ChessGameDto chessGameDto);

    ChessGame findById(final int id);
}
