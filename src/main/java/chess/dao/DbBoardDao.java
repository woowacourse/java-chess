package chess.dao;

import chess.dto.BoardData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbBoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAll(int gameId, List<BoardData> boardDatas) {
        deleteAll(gameId);
        saveAll(gameId, boardDatas);
    }

    public void deleteAll(int gameId) {
        final Connection connection = getConnection();
        final String sql = "delete from board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void saveAll(int gameId, List<BoardData> boardDatas) {
        for (BoardData boardData : boardDatas) {
            save(gameId, boardData);
        }
    }

    private void save(int gameId, BoardData boardData) {
        final Connection connection = getConnection();
        final String sql = "insert into board (game_id, type, y, x) values (?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.setString(2, boardData.getChessPieceType());
            statement.setInt(3, boardData.getColumn());
            statement.setInt(4, boardData.getRow());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<BoardData> findAll(int gameId) {
        List<BoardData> boardDatas = new ArrayList<>();
        final Connection connection = getConnection();
        final String sql = "select * from board where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                boardDatas.add(BoardData.of(resultSet.getString("type"),
                        resultSet.getInt("y"), resultSet.getInt("x")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return boardDatas;
    }
}
