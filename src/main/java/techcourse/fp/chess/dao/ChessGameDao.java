package techcourse.fp.chess.dao;

import java.util.List;
import techcourse.fp.chess.domain.ChessGame;
import techcourse.fp.chess.dto.request.ChessGameRequest;
import techcourse.fp.chess.dto.response.ChessGameInfo;

public interface ChessGameDao {

    void save(ChessGameRequest chessGameRequest);

    ChessGame findById(long id);

    List<ChessGameInfo> findInfos();
}
