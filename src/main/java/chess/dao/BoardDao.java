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

public class BoardDao {

    public Board find() throws SQLException {
        RowMapper rm = new RowMapper() {
            Map<Position, Piece> positionPairs = new HashMap<>();

            @Override
            public Object mapRow(ResultSet rs) throws SQLException {
                while (rs.next()) {
                    String type = rs.getString("type");
                    String position = rs.getString("position");
                    String team = rs.getString("team");
                    positionPairs.put(new Position(position), PieceRule.makeNewPiece(type.charAt(0), position, team));
                }
                return new Board(new Pieces(positionPairs));
            }
        };

        JdbcTemplate template = new JdbcTemplate();
        final String sql = "SELECT type, position, team FROM piece";
        return (Board)template.executeQuery(sql, rm);
    }

    public void save(Board board) throws SQLException {
        removeAll();
        for (Piece alivePiece : board.getPieces().getAlivePieces()) {
            savePiece(alivePiece);
        }
    }

    private void removeAll() throws SQLException {
        JdbcTemplate template = new JdbcTemplate();
        String sql = "DELETE FROM piece";
        template.executeUpdateWithoutPss(sql);
    }

    private void savePiece(Piece piece) throws SQLException {
        PreparedStatementSetter pss = statement -> {
            statement.setString(1, piece.getPosition());
            statement.setString(2, piece.toString());
            statement.setString(3, piece.getTeam().getName());
        };
        JdbcTemplate template = new JdbcTemplate();
        String sql = "INSERT INTO piece(position, type, team) VALUES (?, ?, ?)";
        template.executeUpdate(sql, pss);
    }
}