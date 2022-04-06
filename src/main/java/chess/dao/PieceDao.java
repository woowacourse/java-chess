package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PieceDao {

    public void save(final int file, final int rank, final String symbol, final String background, final int boardId,
                     final Connection connection) {
        try {
            final String sql = "insert into piece (`file`, `rank`, `symbol`, `background`, `board_id`) values (?, ?, ?, ?, ?)";
            final PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, file);
            statement.setInt(2, rank);
            statement.setString(3, symbol);
            statement.setString(4, background);
            statement.setInt(5, boardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
