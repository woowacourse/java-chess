package chess.dao;

import static chess.dao.setting.DBConnection.getConnection;

import chess.dao.entity.GamePiecePositionEntity;
import chess.dao.entity.PiecePositionEntity;
import chess.domain.piece.Piece;
import chess.domain.position.PiecePosition;
import chess.domain.position.Position;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPiecePositionDAO {

    public void save(Long playerId, PiecePosition piecePosition) throws SQLException {
        String query = "INSERT INTO player_piece_position (player_id, piece_id, position_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerId);
        pstmt.setLong(2, piecePosition.getPieceId());
        pstmt.setLong(3, piecePosition.getPositionId());
        pstmt.executeUpdate();
    }

    public Map<Position, Piece> findAllByGameId(Long gameId) throws SQLException {
        ResultSet rs = getResultSetToFindAllByGameId(gameId);
        Map<Position, Piece> results = new HashMap<>();
        while (rs.next()) {
            Piece piece = Piece.of(rs.getLong("piece_id"));
            Position position = Position.of(rs.getLong("position_id"));
            results.put(position, piece);
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

    public List<PiecePositionEntity> findAllByPlayerId(Long playerId) throws SQLException {
        ResultSet rs = getResultSetOfPlayerIds(playerId);
        List<PiecePositionEntity> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new PiecePositionEntity(
                rs.getString("piece_name"),
                rs.getString("piece_color"),
                rs.getString("file_value"),
                rs.getString("rank_value")));
        }
        return results;
    }

    private ResultSet getResultSetOfPlayerIds(Long playerId) throws SQLException {
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

    public GamePiecePositionEntity findGamePiecePositionByGameIdAndPositionId(Long gameId,
        Long positionId) throws SQLException {
        ResultSet rs = getResultSetOfFindGamePiecePosition(gameId, positionId);
        if (!rs.next()) {
            return null;
        }
        Long foundPlayerPiecePositionId = rs.getLong("id");
        Long foundPositionId = rs.getLong("position_id");
        return new GamePiecePositionEntity(foundPlayerPiecePositionId, foundPositionId);
    }

    private ResultSet getResultSetOfFindGamePiecePosition(Long gameId, Long positionId)
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

    public void updatePiecePosition(GamePiecePositionEntity gamePiecePositionEntity)
        throws SQLException {
        String query = "UPDATE player_piece_position SET position_id = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gamePiecePositionEntity.getPositionId());
        pstmt.setLong(2, gamePiecePositionEntity.getPlayerPiecePositionId());
        pstmt.executeUpdate();
    }

    public void removePiecePositionOfGame(GamePiecePositionEntity gamePiecePositionEntity)
        throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gamePiecePositionEntity.getPlayerPiecePositionId());
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
