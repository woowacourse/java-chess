package chess.infrastructure.persistence.dao;

import chess.infrastructure.persistence.entity.ChessGameEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static chess.infrastructure.persistence.JdbcConnectionUtil.connection;

public class ChessGameDao {

    private final JdbcTemplate template = new JdbcTemplate();

    public void save(final ChessGameEntity chessGameEntity) {
        final String sql = "INSERT INTO chess_game(id,  turn, winner) VALUES (?, ?, ?)";
        final Long id = getId();
        template.executeUpdate(sql, id.toString(), chessGameEntity.turn(), chessGameEntity.winner());
        chessGameEntity.setId(id);
    }

    private Long getId() {
        final String query = "SELECT COUNT(*) FROM chess_game";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException();
            }
            return resultSet.getLong(1) + 1;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ChessGameEntity> findById(final Long id) {
        final String query = "SELECT * FROM chess_game where id = ?";
        return template.findOne(query, resultSet -> new ChessGameEntity(
                resultSet.getObject(1, Long.class),
                resultSet.getString(2),
                resultSet.getString(3)
        ), id.toString());
    }

    public void update(final ChessGameEntity chessGameEntity) {
        final String sql = "UPDATE chess_game SET turn = ?, winner = ? where id = ?";
        template.executeUpdate(sql,
                chessGameEntity.turn(),
                chessGameEntity.winner(),
                chessGameEntity.id().toString()
        );
    }

    public void deleteAll() {
        final String sql = "DELETE FROM chess_game";
        template.executeUpdate(sql);
    }
}
