package chess.dao;

import chess.domain.state.GameState;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
    private static final String TABLE_ID = "id";
    private static final String CHESSGAME_STATE = "state";
    private static final String SQL_STATEMENT_EXCEPTION = "[ERROR] SQL 문을 실행할 수 없습니다.";
    private static final String DATA_NOT_EXISTS_EXCEPTION = "[ERROR] 요청된 데이터가 존재하지 않습니다.";

    public void save(GameState gameState) {
        final String sql = "insert into chessgame (state) values (?)";
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameState.getClass().getSimpleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public Integer findId() {
        final String sql = "select id from chessgame";
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(TABLE_ID);
            }
            throw new IllegalArgumentException(DATA_NOT_EXISTS_EXCEPTION);
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public String findStateName() {
        final String sql = "select state from chessgame";
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(CHESSGAME_STATE);
            }
            throw new IllegalArgumentException(DATA_NOT_EXISTS_EXCEPTION);
        } catch (SQLException e) {
            throw new IllegalArgumentException(SQL_STATEMENT_EXCEPTION);
        }
    }

    public void update(GameState gameState) {
        final String sql = "update chessgame set state = ?";
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameState.getClass().getSimpleName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        final String sql = "delete from chessgame";
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
