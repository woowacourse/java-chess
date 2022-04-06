package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import chess.Game;

public class GameDaoImpl implements GameDao{

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private final Game game;

    public GameDaoImpl(Game game) {
        this.game = game;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void save() {
        Connection connection = getConnection();
        String sql = "insert into game (id_white_player, id_black_player) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, game.getIdWhitePlayer());
            statement.setString(2, game.getIdBlackPlayer());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
