package chess.dao;

import chess.domain.state.turn.State;

import java.sql.*;

public class ChessGameDao {

    public State loadState() {
        final String sql = "select state from chessgame";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();){
            String state = getRawState(resultSet);
            return TurnMapper.getStateByName(state);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private String getRawState(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new IllegalStateException("[ERROR] 저장된 정보가 없습니다.");
        }

        return resultSet.getString(1);
    }

    public void saveState(State state) {
        final String sql = "insert into chessgame(state) values (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, state.toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void deleteState() {
        final String sql = "delete from chessgame";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        final String password = "root";
        final String userName = "root";
        final String database = "chess";
        final String option = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        return DriverManager.getConnection("jdbc:mysql://localhost:3308/" + database + option,
                userName, password);
    }
}
