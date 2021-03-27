package chess.db.dao;


import static chess.db.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.db.entity.ChessGameEntity;
import chess.domain.player.type.TeamColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDAO {

    public ChessGameEntity save(ChessGameEntity chessRoomEntity) throws SQLException {
        String query = "INSERT INTO chess_game (title, current_turn_team_color) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setString(1, chessRoomEntity.getTitle());
        pstmt.setString(2, chessRoomEntity.getCurrentTurnTeamColor().getValue());
        pstmt.executeUpdate();
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            chessRoomEntity.setId(generatedKeys.getLong(1));
        }
        return chessRoomEntity;
    }

    public ChessGameEntity findById(Long id) throws SQLException {
        ResultSet rs = getResultSet(id);
        if (rs == null) {
            return null;
        }
        return new ChessGameEntity(
            rs.getLong("id"),
            rs.getString("title"),
            TeamColor.of(rs.getString("current_turn_team_color")));
    }

    private ResultSet getResultSet(Long id) throws SQLException {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return rs;
    }

    public ChessGameEntity updateCurrentTurnTeamColor(ChessGameEntity chessGameEntity)
        throws SQLException {
        String query = "UPDATE chess_game SET current_turn_team_color = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chessGameEntity.getCurrentTurnTeamColor().getValue());
        pstmt.setLong(2, chessGameEntity.getId());
        pstmt.executeUpdate();
        return chessGameEntity;
    }

    public void delete(ChessGameEntity chessGameEntity) throws SQLException {
        String query = "DELETE FROM chess_game WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, chessGameEntity.getId());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM chess_game";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
