package chess.dao;

import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class PieceDao {
    private final DBConnection dbConnection = new DBConnection();

    public void save(Game game, int gameID) {
        Map<Square, Piece> board = game.getBoard();

        Set<Entry<Square, Piece>> entries = board.entrySet();

        for (Entry<Square, Piece> squarePieceEntry : entries) {
            String type = squarePieceEntry.getValue().getPieceType().name();
            String file = squarePieceEntry.getKey().getFile().name();
            String rank = squarePieceEntry.getKey().getRank().name();
            String team = squarePieceEntry.getValue().getTeam().name();

            final var query = "INSERT INTO piece(game_id, piece_type, piece_file, piece_rank, piece_team) VALUES(?,?,?,?,?)";
            try (var connection = dbConnection.getConnection();
                 var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, gameID);
                preparedStatement.setString(2, type);
                preparedStatement.setString(3, file);
                preparedStatement.setString(4, rank);
                preparedStatement.setString(5, team);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<PieceDto> select(int gameID) {
        final var query = "SELECT * FROM piece WHERE game_id = ?";
        try (var connection = dbConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameID);
            var resultSet = preparedStatement.executeQuery();
            List<PieceDto> pieceDtos = new ArrayList<>();
            while (resultSet.next()) {
                PieceDto pieceDto = new PieceDto(
                        resultSet.getString("piece_file"),
                        resultSet.getString("piece_rank"),
                        resultSet.getString("piece_type"),
                        resultSet.getString("piece_team")
                );
                pieceDtos.add(pieceDto);
            }
            return pieceDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        final var query = "DELETE FROM piece";
        try (var connection = dbConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
