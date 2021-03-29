package chess.db.dao;

import static chess.db.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.position.type.File;
import chess.db.domain.board.PiecePositionFromDB;
import chess.db.domain.board.PiecePositionNew;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerPiecePosition;
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

    public PlayerPiecePosition save(PlayerPiecePosition playerPiecePosition)
        throws SQLException {
        ResultSet generatedKeys = getResultSet(playerPiecePosition);
        if (generatedKeys.next()) {
            return new PlayerPiecePosition(
                generatedKeys.getLong(1),
                playerPiecePosition.getPlayerEntity(),
                playerPiecePosition.getPieceEntity(),
                playerPiecePosition.getPositionEntity());
        }
        throw new SQLException("PiecePositionEntity를 save()할 수 없습니다.");
    }

    private ResultSet getResultSet(PlayerPiecePosition playerPiecePositionEntity)
        throws SQLException {
        String query = "INSERT INTO player_piece_position (player_id, piece_id, position_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, RETURN_GENERATED_KEYS);
        pstmt.setLong(1, playerPiecePositionEntity.getPlayerEntity().getId());
        pstmt.setLong(2, playerPiecePositionEntity.getPieceEntity().getId());
        pstmt.setLong(3, playerPiecePositionEntity.getPositionEntity().getId());
        pstmt.executeUpdate();
        return pstmt.getGeneratedKeys();
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
        ResultSet rs = pstmt.executeQuery();
        return rs;
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

    public PieceWithColorType findPieceWithColorTypeByChessGameIdAndFileAndRank(Long gameId,
        Long positionId)
        throws SQLException {
        ResultSet rs = getResultSetToFindPieceWithColorType(gameId, positionId);
        if (!rs.next()) {
            return null;
        }
        String pieceName = rs.getString("piece_name");
        String pieceColor = rs.getString("piece_color");
        return PieceWithColorType.of(pieceName, pieceColor);
    }

    private ResultSet getResultSetToFindPieceWithColorType(Long gameId, Long positionId)
        throws SQLException {
        String query = "SELECT name AS piece_name, color AS piece_color FROM piece "
            + "INNER JOIN (SELECT piece_id FROM player_piece_position "
            + "INNER JOIN (SELECT player.id AS player_id FROM player WHERE chess_game_id = ?) "
            + "AS players_id_of_selected_game ON player_piece_position.player_id = players_id_of_selected_game.player_id "
            + "AND player_piece_position.position_id = ?) "
            + "AS player_piece_position_of_selected_game "
            + "ON player_piece_position_of_selected_game.piece_id = piece.id";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gameId);
        pstmt.setLong(2, positionId);
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

    public void removeAll() throws SQLException {
        String query = "DELETE FROM player_piece_position";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void removeAllByPlayer(Long playerId) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE player_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerId);
        pstmt.executeUpdate();
    }

    public void removePiecePositionOfGame(GamePiecePosition gamePiecePosition) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gamePiecePosition.getPlayerPiecePositionId());
        pstmt.executeUpdate();
    }
}
