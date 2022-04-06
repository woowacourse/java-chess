package chess.dao;

import chess.piece.detail.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbBoardDao implements BoardDao {

    @Override
    public int save(final Color color) {
        final String sql = "insert into board (turn) values (?)";
        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, color.name());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            validResultSet(resultSet);
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Color findById(final int id) {
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

    private void validResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new SQLException("쿼리문 실행 결과가 존재하지 않습니다.");
        }
    }
}
