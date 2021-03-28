package chess.db.dao;

import static chess.db.dao.DBConnection.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.db.domain.board.PiecePositionFromDB;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePosition;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerPiecePositionDAO {
    public PlayerPiecePosition save(PlayerPiecePosition playerPiecePositionEntity)
        throws SQLException {
        ResultSet generatedKeys = getResultSet(playerPiecePositionEntity);
        if (generatedKeys.next()) {
            return new PlayerPiecePosition(
                generatedKeys.getLong(1),
                playerPiecePositionEntity.getPlayerEntity(),
                playerPiecePositionEntity.getPieceEntity(),
                playerPiecePositionEntity.getPositionEntity());
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

    public PlayerPiecePosition findByPlayerAndPieceAndPosition(PlayerEntity playerEntity,
        PieceEntity pieceEntity, PositionEntity positionEntity) throws SQLException {
        ResultSet rs = getResultSet(playerEntity, pieceEntity, positionEntity);
        if (!rs.next()) {
            return null;
        }
        return new PlayerPiecePosition(
            rs.getLong("id"),
            playerEntity,
            pieceEntity,
            positionEntity);
    }

    private ResultSet getResultSet(PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity)
        throws SQLException {
        String query = "SELECT * FROM player_piece_position WHERE player_id = ? AND piece_id = ? AND position_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        pstmt.setLong(2, pieceEntity.getId());
        pstmt.setLong(3, positionEntity.getId());
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

    public List<PiecePositionFromDB> findAllByPlayer(PlayerEntity playerEntity)
        throws SQLException {
        ResultSet rs = getResultSet(playerEntity);
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

    private ResultSet getResultSet(PlayerEntity playerEntity) throws SQLException {
        String query =
            "SELECT piece.name AS piece_name, piece.color AS piece_color, position.file_value AS file_value, position.rank_value AS rank_value "
                + "FROM player_piece_position "
                + "INNER JOIN piece ON player_piece_position.piece_id = piece.id "
                + "INNER JOIN position ON player_piece_position.position_id = position.id "
                + "WHERE player_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        return pstmt.executeQuery();
    }

    public PieceWithColorType findByChessGameIdAndFileAndRank(Long gameId, Long positionId)
        throws SQLException {
        ResultSet rs = getResultSet(gameId, positionId);
        if (!rs.next()) {
            return null;
        }
        String pieceName = rs.getString("piece_name");
        String pieceColor = rs.getString("piece_color");
        return PieceWithColorType.of(pieceName, pieceColor);
    }

    private ResultSet getResultSet(Long gameId, Long positionId) throws SQLException {
        String query = "SELECT name AS piece_name, color AS piece_color FROM piece "
            + "INNER JOIN "
            + "(SELECT piece_id FROM player_piece_position "
            + "INNER JOIN "
            + "(SELECT player.id AS player_id FROM player WHERE chess_game_id = ?) AS players "
            + "ON player_piece_position.player_id = players.player_id "
            + "WHERE player_piece_position.position_id = ?) "
            + "AS piece_id_of_selected_game_and_selected_position "
            + "ON piece.id = piece_id_of_selected_game_and_selected_position.piece_id";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, gameId);
        pstmt.setLong(2, positionId);
        return pstmt.executeQuery();
    }

    public PlayerPiecePosition updatePiecePosition(PlayerPiecePosition playerPiecePositionEntity)
        throws SQLException {
        String query = "UPDATE player_piece_position SET position_id = ? WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerPiecePositionEntity.getPositionEntity().getId());
        pstmt.setLong(2, playerPiecePositionEntity.getId());
        pstmt.executeUpdate();
        return playerPiecePositionEntity;
    }


    public void removePiece(PlayerPiecePosition piecesPositionEntity) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, piecesPositionEntity.getId());
        pstmt.executeUpdate();
    }

    public void removeAll() throws SQLException {
        String query = "DELETE FROM player_piece_position";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void removeAllByPlayer(PlayerEntity playerEntity) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE player_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setLong(1, playerEntity.getId());
        pstmt.executeUpdate();
    }
}
