package chess.dao;

import chess.model.board.Board;
import chess.model.state.State;
import chess.service.converter.StateToStringConverter;
import chess.service.converter.StringToStateConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StateDaoImpl implements StateDao {

    private final DataSource dataSource;

    public StateDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(State state) {
        final String sql = "insert into state (name) values (?)";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            String stateName = StateToStringConverter.convert(state);
            statement.setString(1, stateName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 저장 실패");
        }
    }

    public State find(Board board) {
        final String sql = "select name from state";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            String stateName = resultSet.getString("name");
            return StringToStateConverter.convert(stateName, board);
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 조회 실패");
        }
    }

    @Override
    public void delete() {
        final String sql = "delete from state";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 삭제 실패");
        }
    }

    @Override
    public void update(State now, State next) {
        final String sql = "update state set name = ? where name = ?";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            String nextStateName = StateToStringConverter.convert(next);
            String nowStateName = StateToStringConverter.convert(now);
            statement.setString(1, nextStateName);
            statement.setString(2, nowStateName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 최신화 실패");
        }
    }
}
