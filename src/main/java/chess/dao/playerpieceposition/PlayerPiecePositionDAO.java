package chess.dao.playerpieceposition;

import chess.dao.SQLQuery;
import chess.dao.entity.GamePiecePositionEntity;
import chess.dao.entity.PiecePositionEntity;
import chess.domain.piece.Piece;
import chess.domain.position.PiecePosition;
import chess.domain.position.Position;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPiecePositionDAO implements PlayerPiecePositionRepository {

    @Override
    public void save(Long playerId, PiecePosition piecePosition) throws SQLException {
        String query = "INSERT INTO player_piece_position (player_id, piece_id, position_id) VALUES (?, ?, ?)";
        SQLQuery.insert(query, playerId, piecePosition.getPieceId(), piecePosition.getPositionId());
    }

    @Override
    public Map<Position, Piece> findAllByGameId(Long gameId) throws SQLException {
        String query = "SELECT piece_id, position_id "
            + "FROM player_piece_position "
            + "INNER JOIN "
            + "(SELECT player.id AS player_id FROM player WHERE chess_game_id = ?) "
            + "AS players_id_of_selected_game "
            + "ON player_piece_position.player_id = players_id_of_selected_game.player_id;";
        ResultSet resultSet = SQLQuery.select(query, gameId);
        Map<Position, Piece> results = new HashMap<>();
        while (resultSet.next()) {
            Piece piece = Piece.of(resultSet.getLong("piece_id"));
            Position position = Position.of(resultSet.getLong("position_id"));
            results.put(position, piece);
        }
        return results;
    }

    @Override
    public List<PiecePositionEntity> findAllByPlayerId(Long playerId) throws SQLException {
        String query =
            "SELECT piece.name AS piece_name, piece.color AS piece_color, position.file_value AS file_value, position.rank_value AS rank_value "
                + "FROM player_piece_position "
                + "INNER JOIN piece ON player_piece_position.piece_id = piece.id "
                + "INNER JOIN position ON player_piece_position.position_id = position.id "
                + "WHERE player_id = ?";
        ResultSet resultSet = SQLQuery.select(query, playerId);
        List<PiecePositionEntity> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(new PiecePositionEntity(
                resultSet.getString("piece_name"),
                resultSet.getString("piece_color"),
                resultSet.getString("file_value"),
                resultSet.getString("rank_value")));
        }
        return results;
    }

    @Override
    public GamePiecePositionEntity findGamePiecePositionByGameIdAndPositionId(Long gameId, Long positionId) throws SQLException {
        String query = "SELECT id, position_id FROM player_piece_position "
            + "INNER JOIN "
            + "(SELECT player.id AS player_id FROM player WHERE chess_game_id = ?) "
            + "AS players "
            + "ON player_piece_position.player_id = players.player_id "
            + "WHERE player_piece_position.position_id = ?;";
        ResultSet resultSet = SQLQuery.select(query, gameId, positionId);
        if (!resultSet.next()) {
            return null;
        }
        Long foundPlayerPiecePositionId = resultSet.getLong("id");
        Long foundPositionId = resultSet.getLong("position_id");
        return new GamePiecePositionEntity(foundPlayerPiecePositionId, foundPositionId);
    }

    @Override
    public void updatePiecePosition(GamePiecePositionEntity gamePiecePositionEntity) throws SQLException {
        String query = "UPDATE player_piece_position SET position_id = ? WHERE id = ?";
        SQLQuery.updateOrRemove(query, gamePiecePositionEntity.getPositionId(), gamePiecePositionEntity.getPlayerPiecePositionId());
    }

    @Override
    public void removePiecePositionOfGame(GamePiecePositionEntity gamePiecePositionEntity) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE id = ?";
        SQLQuery.updateOrRemove(query, gamePiecePositionEntity.getPlayerPiecePositionId());
    }

    @Override
    public void removeAllByPlayer(Long playerId) throws SQLException {
        String query = "DELETE FROM player_piece_position WHERE player_id = ?";
        SQLQuery.updateOrRemove(query, playerId);
    }

    @Override
    public void removeAll() throws SQLException {
        String query = "DELETE FROM player_piece_position";
        SQLQuery.updateOrRemove(query);
    }
}
