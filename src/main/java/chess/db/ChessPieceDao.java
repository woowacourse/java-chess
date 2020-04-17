package chess.db;

import chess.domains.piece.Piece;
import chess.domains.position.Position;
import chess.util.JdbcTemplate;
import chess.util.RowMapper;

import java.sql.SQLException;
import java.util.List;

public class ChessPieceDao implements PieceDao {
    @Override
    public int countSavedPieces(String gameId) throws SQLException {
        String query = "SELECT COUNT(*) FROM board_status WHERE game_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        RowMapper<Integer> rowMapper = rs -> {
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt("count(*)");
        };

        return jdbcTemplate.executeQuery(query, rowMapper, gameId);
    }

    @Override
    public void addInitialPieces(List<ChessPiece> chessPieces) throws SQLException {
        for (ChessPiece piece : chessPieces) {
            addPiece(piece);
        }
    }

    @Override
    public void addPiece(ChessPiece piece) throws SQLException {
        String query = "INSERT INTO board_status (game_id, position, piece) VALUES (?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.executeUpdate(query, piece.getGameId(), piece.getPosition(), piece.getPiece());
    }

    @Override
    public String findPieceNameByPosition(String gameId, Position position) throws SQLException {
        String query = "SELECT piece FROM board_status WHERE game_id = ? AND position = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        RowMapper<String> rowMapper = rs -> {
            if (!rs.next()) {
                return null;
            }
            return rs.getString("piece");
        };

        return jdbcTemplate.executeQuery(query, rowMapper, gameId, position.name());
    }

    @Override
    public void updatePiece(String gameId, Position position, Piece piece) throws SQLException {
        String query = "UPDATE board_status SET piece = ? WHERE game_id = ? AND position = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.executeUpdate(query, piece.name(), gameId, position.name());
    }

    @Override
    public void deleteBoardStatus(String gameId) throws SQLException {
        String query = "DELETE FROM board_status WHERE game_id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.executeUpdate(query, gameId);
    }
}
