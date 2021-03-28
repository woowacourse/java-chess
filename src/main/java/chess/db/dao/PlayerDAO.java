package chess.db.dao;


import static chess.db.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import chess.beforedb.domain.player.type.TeamColor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
    private final ChessGameDAO chessRoomDAO = new ChessGameDAO();

    public PlayerEntity save(PlayerEntity playerEntity) throws SQLException {
        ResultSet generatedKeys = getResultSet(playerEntity);
        if (generatedKeys.next()) {
            return new PlayerEntity(
                generatedKeys.getLong(1),
                playerEntity.getTeamColor(),
                playerEntity.getChessGameEntity());
        }
        throw new SQLException("playerEntity를 save()할 수 없습니다.");
    }

    private ResultSet getResultSet(PlayerEntity playerEntity) throws SQLException {
        String query = "INSERT INTO player (chess_game_id, team_color) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setLong(1, playerEntity.getChessGameEntity().getId());
        pstmt.setString(2, playerEntity.getTeamColor().getValue());
        pstmt.executeUpdate();
        return pstmt.getGeneratedKeys();
    }

    public PlayerEntity findByIdAndChessGame(Long id, ChessGameEntity chessGameEntity)
        throws SQLException {
        ResultSet rs = getResultSet(id, chessGameEntity);
        if (!rs.next()) {
            return null;
        }
        return new PlayerEntity(
            rs.getLong("id"),
            TeamColor.of(rs.getString("team_color")),
            chessGameEntity);
    }

    private ResultSet getResultSet(Long id, ChessGameEntity chessGameEntity) throws SQLException {
        String query = "SELECT * FROM player WHERE id = ? AND chess_game_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, id);
        pstmt.setLong(2, chessGameEntity.getId());
        return pstmt.executeQuery();
    }

    public void delete(PlayerEntity playerEntity) throws SQLException {
        String query = "DELETE FROM player WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        pstmt.executeUpdate();
    }

    public void deleteAll() throws SQLException {
        String query = "DELETE FROM player";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
