package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.Board;
import chess.domain.Pieces;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceRule;
import chess.exception.DataAccessException;

public class BoardDao {
    JdbcTemplate template = new JdbcTemplate();

    public Board find() {
        try {
            RowMapper rm = new RowMapper() {
                Map<Position, Piece> positionPairs = new HashMap<>();

                @Override
                public Object mapRow(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        String type = rs.getString("type");
                        String position = rs.getString("position");
                        String team = rs.getString("team");
                        positionPairs.put(new Position(position),
                            PieceRule.makeNewPiece(type.charAt(0), position, team));
                    }
                    return new Board(new Pieces(positionPairs));
                }
            };
            final String sql = "SELECT type, position, team FROM piece";
            return (Board)template.executeQuery(sql, rm);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public Piece findByPosition(String position) {
        try {
            PreparedStatementSetter pss = statement -> statement.setString(1, position);
            RowMapper rm = rs -> PieceRule.makeNewPiece(
                rs.getString("type").charAt(0),
                rs.getString("position"),
                rs.getString("team")
            );
            String query = "SELECT * FROM piece WHERE position = ?";
            return (Piece)template.executeQueryWithPss(query, pss, rm);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public void editPiece(String position, String newPosition) {
        try {
            PreparedStatementSetter pss = statement -> {
                statement.setString(1, newPosition);
                statement.setString(2, position);
            };
            final String query = "UPDATE piece SET position = ? WHERE position = ?";
            template.executeUpdate(query, pss);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public void save(Board board) {
        removeAll();
        for (Piece alivePiece : board.getPieces().getAlivePieces()) {
            savePiece(alivePiece);
        }
    }

    private void removeAll() {
        try {
            final String sql = "DELETE FROM piece";
            template.executeUpdateWithoutPss(sql);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    public void removePiece(String position) {
        try {
            PreparedStatementSetter pss = statement -> statement.setString(1, position);
            String query = "DELETE FROM piece WHERE position = ?";
            template.executeUpdate(query, pss);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    private void savePiece(Piece piece) {
        try {
            PreparedStatementSetter pss = statement -> {
                statement.setString(1, piece.getPosition());
                statement.setString(2, piece.toString());
                statement.setString(3, piece.getTeam().getName());
            };
            final String sql = "INSERT INTO piece(position, type, team) VALUES (?, ?, ?)";
            template.executeUpdate(sql, pss);
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }
}