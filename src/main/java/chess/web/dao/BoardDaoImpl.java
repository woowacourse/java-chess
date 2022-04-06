package chess.web.dao;

import chess.web.dto.BoardDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDaoImpl implements BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private final Connection connection;

    public BoardDaoImpl() {
        connection = getConnection();
    }

    @Override
    public void save(BoardDto boardDto) {
        final String sql = "insert into board (state) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, boardDto.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(BoardDto boardDto) {
        final String sql = "update board state=? where state=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, boardDto.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String selectState() {
        final String sql = "select state from board";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return toBoardState(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private String toBoardState(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException();
        }
        return resultSet.getString("state");
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
