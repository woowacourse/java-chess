package chess.dao.player;


import chess.dao.SQLQuery;
import chess.domain.player.type.TeamColor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO implements PlayerRepository {

    @Override
    public void save(TeamColor[] teamColors, Long gameId) throws SQLException {
        String query = "INSERT INTO player (team_color, chess_game_id) VALUES (?, ?), (?, ?)";
        SQLQuery.insert(query, teamColors[0].getValue(), gameId, teamColors[1].getValue(), gameId);
    }

    @Override
    public Long findIdByGameIdAndTeamColor(Long gameId, TeamColor teamColor) throws SQLException {
        String query = "SELECT id FROM player WHERE chess_game_id = ? AND team_color = ?";
        ResultSet resultSet = SQLQuery.select(query, gameId, teamColor.getValue());
        if (!resultSet.next()) {
            return null;
        }
        return resultSet.getLong("id");
    }

    @Override
    public void removeAllByChessGame(Long gameId) throws SQLException {
        String query = "DELETE FROM player WHERE chess_game_id = ?";
        SQLQuery.updateOrRemove(query, gameId);
    }

    @Override
    public void removeAll() throws SQLException {
        String query = "DELETE FROM player";
        SQLQuery.updateOrRemove(query);
    }
}
