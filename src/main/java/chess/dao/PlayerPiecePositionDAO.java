package chess.dao;

import static chess.dao.setting.DBConnection.getConnection;

import chess.dao.entity.GamePiecePosition;
import chess.dao.entity.PiecePositionFromDB;
import chess.dao.entity.PositionEntity;
import chess.domain.piece.PieceEntity;
import chess.domain.position.PiecePositionNew;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPiecePositionDAO {

    public void save(Long playerId, PiecePositionNew piecePosition) throws SQLException {
        String query = "INSERT INTO player_piece_position (player_id, piece_id, position_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerId);
        pstmt.setLong(2, piecePosition.getPieceId());
        pstmt.setLong(3, piecePosition.getPositionId());
        pstmt.executeUpdate();
    }

    public Map<PositionEntity, PieceEntity> findAllByGameId(Long gameId) throws SQLException {
        ResultSet rs = getResultSetToFindAllByGameId(gameId);
        Map<PositionEntity, PieceEntity> results = new HashMap<>();
        while (rs.next()) {
            PieceEntity pieceEntity = PieceEntity.of(rs.getLong("piece_id"));
            PositionEntity positionEntity = PositionEntity.of(rs.getLong("position_id"));
            results.put(positionEntity, pieceEntity);
        }
        return results;
    }

    private ResultSet getResultSetToFindAllByGameId(Long gameId) throws SQLException {
        String query = "SELECT piece_id, position_id FROM player_piece_position "
            + "INNER JOIN (SELECT player.id AS player_id FROM player WHERE chess_game_id = ?) "
            + "AS players_id_of_selected_game ON player_piece_position.player_id = players_id_of_selected_game.player_id;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gameId);
        return pstmt.executeQuery();
    }

    public List<PiecePositionFromDB> findAllByPlayerId(Long playerId) throws SQLException {
        ResultSet rs = getResultSet(playerId);
        List<PiecePositionFromDB> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new PiecePositionFromDB(
                rs.getString("piece_name"),
                rs.getString("piece_color"),
                rs.getString("file_value"),
                rs.getString("rank_value")));
        }
        return results;
    }

    private ResultSet getResultSet(Long playerId) throws SQLException {
        String query =
            "SELECT piece.name AS piece_name, piece.color AS piece_color, position.file_value AS file_value, position.rank_value AS rank_value "
                + "FROM player_piece_position "
                + "INNER JOIN piece ON player_piece_position.piece_id = piece.id "
                + "INNER JOIN position ON player_piece_position.position_id = position.id "
                + "WHERE player_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerId);
        return pstmt.executeQuery();
    }

    public GamePiecePosition findGamePiecePositionByGameIdAndPositionId(Long gameId,
        Long positionId) throws SQLException {
        ResultSet rs = getResultSetToFindPiecePosition(gameId, positionId);
        if (!rs.next()) {
            return null;
        }
        Long foundPlayerPiecePositionId = rs.getLong("id");
        Long foundPositionId = rs.getLong("position_id");
        return new GamePiecePosition(foundPlayerPiecePositionId, foundPositionId);
    }

    private ResultSet getResultSetToFindPiecePosition(Long gameId, Long positionId)
        throws SQLException {
        String query = "SELECT id, position_id FROM player_piece_position "
            + "INNER JOIN (SELECT player.id AS player_id FROM player WHERE chess_game_id = ?) AS players "
            + "ON player_piece_position.player_id = players.player_id "
            + "WHERE player_piece_position.position_id = ?;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gameId);
        pstmt.setLong(2, positionId);
        return pstmt.executeQuery();
    }

    public void updatePiecePosition(GamePiecePosition gamePiecePosition) throws SQLException {
        String query = "UPDATE player_piece_position SET position_id = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gamePiecePosition.getPositionId());
        pstmt.setLong(2, gamePiecePosition.getPlayerPiecePositionId());
        pstmt.executeUpdate();
    }

    public void removePiecePositionOfGame(GamePiecePosition gamePiecePosition) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gamePiecePosition.getPlayerPiecePositionId());
        pstmt.executeUpdate();
    }

    public void removeAllByPlayer(Long playerId) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE player_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerId);
        pstmt.executeUpdate();
    }

    public void removeAll() throws SQLException {
        String query = "DELETE FROM player_piece_position";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}
