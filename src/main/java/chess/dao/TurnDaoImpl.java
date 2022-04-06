package chess.dao;

import chess.domain.state.StateType;
import chess.dao.dto.TurnDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TurnDaoImpl implements TurnDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Override
    public void save(TurnDto turnDto) {
        String sql = "insert into turn (turn_type) values (?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            StateType stateType = turnDto.getTurn();
            statement.setString(1, stateType.getValue());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("적절하지 않은 턴 정보입니다.");
        }
    }

    @Override
    public Optional<TurnDto> findLastTurn() {
        String sql = "select * from turn order by id desc limit 1";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            String turnType = resultSet.getString("turn_type");
            return Optional.of(new TurnDto(StateType.of(turnType)));
        } catch (SQLException e) {
            throw new IllegalArgumentException("현재 턴 정보가 존재하지 않습니다.");
        }
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
