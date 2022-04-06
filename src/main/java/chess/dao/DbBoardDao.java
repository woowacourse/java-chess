package chess.dao;

import chess.piece.detail.Color;
import java.sql.*;

public class DbBoardDao implements BoardDao {

    @Override
    public void save(final Color color) {
        final String sql = "insert into board (turn) values (?)";
        try (Connection connection = DBConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, color.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Color findById(final int id) {
        final String sql = "select * from board where id = ?";
        try (Connection connection = DBConnector.getConnection()){
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
}
