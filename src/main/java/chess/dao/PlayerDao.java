package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import chess.domain.player.Player;
import chess.domain.player.Record;

public class PlayerDao implements JdbcTemplateDao {

    public void addInitialPlayers() throws SQLException {
        // 플레이어 가입 및 로그인 구현 전 오류 방지를 위한 쿼리
        String query = "insert ignore into player (id, username, password) values (1, 'hodol', 'password'), (2, 'pobi', 'password')";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        closeConnection(connection);

    }

    public void addPlayer(Player player) throws SQLException {
        String query = "insert into player (username, password) values (?, ?);";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, player.getUsername());
        statement.setString(2, player.getPassword());
        statement.executeUpdate();
        closeConnection(connection);
    }

    public Player getPlayerById(int id) throws SQLException {
        String query = "select * from player where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Record record = Record.of(resultSet.getInt("win"), resultSet.getInt("lose"), resultSet.getInt("draw"));
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            Player player = new Player(username, password, record);
            player.setRecord(record);
            player.setId(resultSet.getInt("id"));
            closeConnection(connection);
            return player;
        }
        closeConnection(connection);
        return null;
    }

    public void increaseWin(int id) throws SQLException {
        String query = "update player set win = win + 1 where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        closeConnection(connection);
    }

    public void increaseLose(int id) throws SQLException {
        String query = "update player set lose = lose + 1 where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        closeConnection(connection);
    }

    public void increaseDraw(int id) throws SQLException {
        String query = "update player set draw = draw + 1 where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
        closeConnection(connection);
    }
}
