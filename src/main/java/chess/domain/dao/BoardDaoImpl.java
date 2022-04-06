package chess.domain.dao;

import chess.domain.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDaoImpl implements BoardDao {
    @Override
    public int save(Color turn) {
        final String sql = decideSql();

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, turn.ordinal());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
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
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Color getCurrentTurn() {
        final String sql = "select turn from board";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalAccessException("저장된 게임이 없습니다.");
            }
            return Color.from(resultSet.getInt("turn"));
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return Color.NONE;
        }
    }

    @Override
    public void deleteBoard() {
        final String sql = "delete from board where id = 1";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
