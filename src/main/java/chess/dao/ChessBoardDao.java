package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.PieceNameAndColor;
import chess.web.jdbc.JdbcTemplate;
import chess.web.jdbc.SelectJdbcTemplate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public Optional<PieceNameAndColor> findByPosition(String position) {
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                statement.setString(1, position);
            }

            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                if (!resultSet.next()) {
                    return null;
                }
                return resultSet.getString("piece");
            }
        };
        final String sql = "select position, piece from board where position = ?";
        Object result = jdbcTemplate.executeQuery(sql);

        return Optional.ofNullable(PieceNameAndColor.of(result.toString()));
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

    public Map<String, String> findAll() {
        final Map<String, String> board = new HashMap<>();
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public void setParameters(PreparedStatement statement) throws SQLException {
                return;
            }

            @Override
            public Object mapRow(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    board.put(resultSet.getString("position"), resultSet.getString("piece"));
                }
                return null;
            }
        };
        final String sql = "select position, piece from board";
        jdbcTemplate.executeQuery(sql);

        return board;
    }
}
