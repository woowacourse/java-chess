package chess.dao;

import chess.piece.detail.Color;
import java.sql.*;

public class DbBoardDao implements BoardDao {

    @Override
    public Color load() {
        final String sql = "select turn from board";
        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            validateResultSet(resultSet);
            return Color.valueOf(resultSet.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findLastlyUsedBoard() {
        final String sql = "select * from board order by id desc limit 1";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            validateResultSet(resultSet);

            return resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateById(final int boardId, final Color turn) {
        final String sql = "update board set turn = ? where id = ?";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, turn.name());
            statement.setInt(2, boardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(final int boardId) {
        final String sql = "delete from board where id = ?";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, boardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int save(final Color color) {
        final String sql = "insert into board (turn) values (?)";
        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, color.name());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            validateResultSet(resultSet);
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Color findTurnById(final int id) {
        final String sql = "select * from board where id = ?";
        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            return Color.valueOf(resultSet.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void validateResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException("쿼리문 실행 결과가 존재하지 않습니다.");
        }
    }
}
