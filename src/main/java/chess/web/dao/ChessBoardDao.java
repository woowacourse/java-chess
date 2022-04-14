package chess.web.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.position.Position;
import chess.web.jdbc.JdbcTemplate;
import chess.web.jdbc.SelectJdbcTemplate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {

    public void save(Position position, Piece piece) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement)
                    throws SQLException {
                statement.setString(1, position.toString());
                statement.setString(2, piece.toString());
            }
        };
        final String sql = "insert into board (position, piece) values (?, ?)";
        jdbcTemplate.executeUpdate(sql);
    }

    public void deleteAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                return;
            }
        };
        final String sql = "delete from board";
        jdbcTemplate.executeUpdate(sql);
    }

    public Map<Position, Piece> findAll() {
        final Map<Position, Piece> board = new HashMap<>();
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                return;
            }

            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    String position = resultSet.getString("position");
                    String piece = resultSet.getString("piece");
                    board.put(Position.of(resultSet.getString("position")), PieceFactory.of(position, piece));
                }
                return null;
            }
        };
        final String sql = "select position, piece from board";
        jdbcTemplate.executeQuery(sql);

        return board;
    }
}
