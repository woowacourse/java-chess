package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.domain.player.Record;
import chess.domain.player.Result;

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
        String query = "insert into player (username, password, win, lose, draw) values (?, ?, ?, ?, ?);";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, player.getUsername());
        statement.setString(2, player.getPassword());
        statement.setInt(3, new Record().get(Result.WIN));
        statement.setInt(4, new Record().get(Result.LOSE));
        statement.setInt(5, new Record().get(Result.DRAW));
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
        throw new SQLException();
    }

    public Map<Integer, Map<Side, Player>> getPlayerContexts() throws SQLException {
        String query = "select id, white, black from game";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        Map<Integer, Map<Side, Player>> playerContexts = new HashMap<>();
        while (resultSet.next()) {
            Map<Side, Player> playerContext = new HashMap<>();
            playerContext.put(Side.WHITE, getPlayerById(resultSet.getInt("white")));
            playerContext.put(Side.BLACK, getPlayerById(resultSet.getInt("black")));
            playerContexts.put(resultSet.getInt("id"), playerContext);
        }
        closeConnection(connection);
        return playerContexts;
    }

    public Map<Side, Player> getPlayersByGameId(int gameId) throws SQLException {
        String query = "select white, black from game where id = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, gameId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Map<Side, Player> result = new HashMap<>();
            result.put(Side.WHITE, getPlayerById(resultSet.getInt("white")));
            result.put(Side.BLACK, getPlayerById(resultSet.getInt("black")));
            closeConnection(connection);
            return result;
        }
        closeConnection(connection);
        throw new SQLException();
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
