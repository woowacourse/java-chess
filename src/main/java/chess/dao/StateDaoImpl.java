package chess.dao;

import chess.dao.converter.StateToStringConverter;
import chess.dao.converter.StringToStateConverter;
import chess.model.board.Board;
import chess.model.state.State;
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
        if (hasState()) {
            String nowStateName = getStateName();
            update(nowStateName, state);
            return;
        }
        insert(state);
    }

    private String getStateName() {
        final String sql = "select name from state";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 조회 실패");
        }
    }

    private void insert(State state) {
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

    private boolean hasState() {
        final String sql = "select count(*) as cnt from state";
        int count = 0;
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            count = resultSet.getInt("cnt");
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 조회 실패");
        }
        return count == 1;
    }

    private void update(String nowStateName, State next) {
        final String sql = "update state set name = ? where name = ?";
        try (final Connection connection = dataSource.connection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            String nextStateName = StateToStringConverter.convert(next);
            statement.setString(1, nextStateName);
            statement.setString(2, nowStateName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 최신화 실패");
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
}
