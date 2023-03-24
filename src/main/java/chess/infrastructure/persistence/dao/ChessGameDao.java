package chess.infrastructure.persistence.dao;

import chess.infrastructure.persistence.entity.ChessGameEntity;

import java.util.Optional;

public class ChessGameDao {

    private final JdbcTemplate template;

    public ChessGameDao(final JdbcTemplate template) {
        this.template = template;
    }

    public void save(final ChessGameEntity chessGameEntity) {
        final String sql = "INSERT INTO chess_game(state, turn) VALUES (?, ?)";
        final Long id = template.executeUpdate(sql,
                chessGameEntity.state(),
                chessGameEntity.turn());
        chessGameEntity.setId(id);
    }

    public Optional<ChessGameEntity> findById(final Long id) {
        final String query = "SELECT * FROM chess_game where id = ?";
        return template.findOne(query, resultSet -> new ChessGameEntity(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3)
        ), id.toString());
    }

    public void update(final ChessGameEntity chessGameEntity) {
        final String sql = "UPDATE chess_game SET turn = ?, state = ? where id = ?";
        template.executeUpdate(sql,
                chessGameEntity.turn(),
                chessGameEntity.state(),
                chessGameEntity.id().toString()
        );
    }

    public void deleteAll() {
        final String sql = "DELETE FROM chess_game";
        template.executeUpdate(sql);
    }
}
