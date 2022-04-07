package chess.web.dao;

import chess.domain.state.StateType;
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
    public void save(StateType stateType) {
        final String sql = "insert into board (state) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, stateType.getNotation());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 상태를 저장할 수 없습니다.");
        }
    }

    @Override
    public void update(StateType stateType) {
        final String sql = "update board set state=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, stateType.getNotation());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 상태를 수정할 수 없습니다.");
        }
    }

    @Override
    public StateType selectState() {
        final String sql = "select state from board order by id desc limit 1";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return toBoardState(resultSet);
        } catch (SQLException e) {
            throw new IllegalArgumentException("상태를 가져올 수 없습니다.");
        }
    }

    private StateType toBoardState(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        return StateType.from(resultSet.getString("state"));
    }

    @Override
    public void deleteAll() {
        final String sql = "delete from board";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("해당 기물을 삭제할 수 없습니다.");
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 연결할 수 없습니다.");
        }
        return connection;
    }
}
