package techcourse.fp.chess.dao;

import techcourse.fp.chess.domain.ChessGame;
import techcourse.fp.chess.dto.request.ChessGameRequest;

public interface ChessGameDao {

    void save(ChessGameRequest chessGameRequest);

    ChessGame findById(long id);
}
