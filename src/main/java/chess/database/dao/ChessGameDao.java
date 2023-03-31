package chess.database.dao;

import chess.domain.piece.info.Team;
import chess.domain.position.Path;
import java.sql.Connection;
import java.util.List;

public interface ChessGameDao {

    int findRunningGameId(final Connection connection);

    List<Path> findAllHistoryById(final int gameId, final Connection connection);

    void insertGame(final Connection connection);

    void insertMoveHistory(final int gameId, final String source, final String destination,
        final Connection connection);

    void updateStateToFinished(final int gameId, final Team team, final Connection connection);

    int selectLastInsertedID(final Connection connection);
}
