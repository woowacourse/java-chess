package chess.domain.dao;

import chess.domain.util.DbConnection;
import chess.domain.dto.GameDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDaoImpl implements GameDao {

    private final Connection connection;

    public GameDaoImpl() {
        this(DbConnection.getConnection());
    }

    public GameDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long save(GameDto gameDto) {
        final String sql = "INSERT INTO chess_game(game_state, game_turn) values (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql, new String[]{"id"});
            statement.setString(1, gameDto.getState());
            statement.setString(2, gameDto.getTurn());
            statement.execute();

            Long id = null;
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
