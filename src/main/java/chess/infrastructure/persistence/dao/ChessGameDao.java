package chess.infrastructure.persistence.dao;

import chess.infrastructure.persistence.entity.ChessGameEntity;

import java.util.Optional;

public class ChessGameDao {

    private final JdbcTemplate template;

    public ChessGameDao(final JdbcTemplate template) {
        this.template = template;
    }

    public void save(final ChessGameEntity chessGameEntity) {
        final String sql = "INSERT INTO chess_game(state, turn, winner) VALUES (?, ?, ?)";
        final Long id = template.saveAndGetId(sql,
                chessGameEntity.state(),
                chessGameEntity.turn(),
                chessGameEntity.winner());
        chessGameEntity.setId(id);
    }

    public Optional<ChessGameEntity> findById(final Long id) {
        final String query = "SELECT * FROM chess_game where id = ?";
        return template.findOne(query, resultSet -> new ChessGameEntity(
                resultSet.getObject(1, Long.class),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        ), id.toString());
    }

    public void update(final ChessGameEntity chessGameEntity) {
        final String sql = "UPDATE chess_game SET turn = ?, state = ?, winner = ? where id = ?";
        template.executeUpdate(sql,
                chessGameEntity.turn(),
                chessGameEntity.state(),
                chessGameEntity.winner(),
                chessGameEntity.id().toString()
        );
    }

    public void deleteAll() {
        final String sql = "DELETE FROM chess_game";
        template.executeUpdate(sql);
    }
}
