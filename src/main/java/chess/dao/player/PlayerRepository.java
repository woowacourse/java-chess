package chess.dao.player;

import chess.domain.player.type.TeamColor;
import java.sql.SQLException;

public interface PlayerRepository {
    void save(TeamColor[] teamColors, Long gameId) throws SQLException;

    Long findIdByGameIdAndTeamColor(Long gameId, TeamColor teamColor) throws SQLException;

    void removeAllByChessGame(Long gameId) throws SQLException;

    void removeAll() throws SQLException;
}
