package chess.dao;

import chess.dao.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.util.Arrays;

import static chess.dao.sqls.PlayerSql.INSERT_PLAYER;

public class PlayerDao {
    private PlayerDao() {
    }

    private static class PlayerDaoHolder {
        private static final PlayerDao INSTANCE = new PlayerDao();
    }

    public static PlayerDao getInstance() {
        return PlayerDaoHolder.INSTANCE;
    }

    public int insertPlayers(String whitePlayer, String blackPlayer) throws SQLDataException {
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYER)) {
            preparedStatement.setString(1, whitePlayer);
            preparedStatement.setString(2, whitePlayer);
            preparedStatement.addBatch();

            preparedStatement.setString(1, blackPlayer);
            preparedStatement.setString(2, blackPlayer);
            preparedStatement.addBatch();

            return Arrays.stream(preparedStatement.executeBatch()).sum();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLDataException();
        }
    }
}
