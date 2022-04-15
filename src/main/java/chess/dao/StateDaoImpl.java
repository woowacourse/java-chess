package chess.dao;

import chess.dao.converter.StateToStringConverter;
import chess.dao.converter.StringToStateConverter;
import chess.model.board.Board;
import chess.model.state.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        return executeResult(sql, "name");
    }

    private boolean hasState() {
        final String sql = "select count(*) as cnt from state";
        String count = executeResult(sql, "cnt");
        return count.equals("1");
    }

    private void insert(State state) {
        final String sql = "insert into state (name) values (?)";
        String stateName = StateToStringConverter.convert(state);
        executeStatement(sql, List.of(stateName));
    }

    private void update(String nowStateName, State next) {
        final String sql = "update state set name = ? where name = ?";
        String nextStateName = StateToStringConverter.convert(next);
        executeStatement(sql, List.of(nextStateName, nowStateName));
    }

    public State find(Board board) {
        final String sql = "select name from state";
        String stateName = executeResult(sql, "name");
        return StringToStateConverter.convert(stateName, board);
    }

    @Override
    public void delete() {
        final String sql = "delete from state";
        executeStatement(sql, new ArrayList<>());
    }

    private void executeStatement(String sql, List<String> conditions) {
        try (final PreparedStatement statement = dataSource.connection().prepareStatement(sql)) {
            setQuery(statement, conditions);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 삭제 실패");
        }
    }

    private void setQuery(PreparedStatement statement, List<String> conditions) throws SQLException {
        for (int index = 0; index < conditions.size(); index++) {
            statement.setString(index + 1, conditions.get(index));
        }
    }

    private String executeResult(String sql, String column) {
        try (final PreparedStatement statement = dataSource.connection().prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getString(column);
        } catch (SQLException e) {
            throw new RuntimeException("[ERROR] 데이터 조회 실패");
        }
    }
}
