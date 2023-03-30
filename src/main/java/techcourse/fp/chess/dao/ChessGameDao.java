package techcourse.fp.chess.dao;

import java.util.List;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Turn;
import techcourse.fp.chess.dto.response.ChessGameInfo;

public interface ChessGameDao {

    int save(final String name, final Color turn);

    Turn findTurn(final long id);

    List<ChessGameInfo> findInfos();
}
