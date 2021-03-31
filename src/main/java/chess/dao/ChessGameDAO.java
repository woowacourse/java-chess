package chess.dao;


import chess.dao.entity.ChessGameEntity;
import chess.dao.entity.GameStatusEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDAO {

    public ChessGameEntity save(ChessGameEntity chessRoomEntity) throws SQLException {
        String query = "INSERT INTO chess_game (title, current_turn_team_color) VALUES (?, ?)";
        Long id = SQLQuery.insert(query, chessRoomEntity.getTitle(), chessRoomEntity.getCurrentTurnTeamColor().getValue());
        chessRoomEntity.setId(id);
        return chessRoomEntity;
    }

    public ChessGameEntity findById(Long id) throws SQLException {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        ResultSet resultSet = SQLQuery.select(query, id);
        if (!resultSet.next()) {
            return null;
        }
        return new ChessGameEntity(
            resultSet.getLong("id"),
            resultSet.getString("title"),
            resultSet.getString("current_turn_team_color"));
    }

    public List<ChessGameEntity> findAll() throws SQLException {
        String query = "SELECT * FROM chess_game";
        ResultSet resultSet = SQLQuery.select(query);
        List<ChessGameEntity> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(new ChessGameEntity(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("current_turn_team_color")));
        }
        return results;
    }

    public GameStatusEntity findStatusByGameId(Long gameId) throws SQLException {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        ResultSet resultSet = SQLQuery.select(query, gameId);
        if (!resultSet.next()) {
            return null;
        }
        return new GameStatusEntity(
            resultSet.getString("title"),
            resultSet.getString("current_turn_team_color"));
    }

    public ChessGameEntity updateCurrentTurnTeamColor(ChessGameEntity chessGameEntity) throws SQLException {
        String query = "UPDATE chess_game SET current_turn_team_color = ? WHERE id = ?";
        SQLQuery.updateOrRemove(query, chessGameEntity.getCurrentTurnTeamColor().getValue(), chessGameEntity.getId());
        return chessGameEntity;
    }

    public void remove(Long gameId) throws SQLException {
        String query = "DELETE FROM chess_game WHERE id = ?";
        SQLQuery.updateOrRemove(query, gameId);
    }

    public void removeAll() throws SQLException {
        String query = "DELETE FROM chess_game";
        SQLQuery.updateOrRemove(query);
    }
}
