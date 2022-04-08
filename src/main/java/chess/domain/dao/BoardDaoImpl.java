package chess.domain.dao;

import chess.domain.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDaoImpl implements BoardDao {
    @Override
    public void save(Color turn) {
        final String sql = decideSql();

        executeSave(turn, sql);
    }

    private String decideSql() {
        if (isBoardExist()) {
            return "update board set turn = ?";
        }
        return "insert into board (id, turn) values (1, ?)";
    }

    private boolean isBoardExist() {
        final String sql = "select id from board where id = 1";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }

    private void executeSave(Color turn, String sql) {
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, turn.ordinal());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }

    @Override
    public Color findTurn() {
        final String sql = "select turn from board";
        return executeFindTurn(sql);
    }

    private Color executeFindTurn(String sql) {
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException();
            }
            return Color.from(resultSet.getInt("turn"));
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }

    @Override
    public void deleteBoard() {
        final String sql = "delete from board where id = 1";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다. 다시 시도해주세요.");
        }
    }
}
