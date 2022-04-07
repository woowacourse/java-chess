package chess.dao;

import chess.dto.board.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    public void save(final int file, final int rank, final String symbol, final String background, final int boardId,
                     final Connection connection) {
        try {
            final String sql = "insert into piece (`file`, `rank`, `symbol`, `board_id`) values (?, ?, ?, ?)";
            final PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, file);
            statement.setInt(2, rank);
            statement.setString(3, symbol);
            statement.setInt(4, boardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PieceDto> findPieceByBoardId(final int boardId, final Connection connection) throws SQLException {
        final String sql = "select * from piece where board_id = ?";

        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, boardId);

        final ResultSet resultSet = statement.executeQuery();
        final List<PieceDto> pieces = new ArrayList<>();

        while (resultSet.next()) {
            final int file = resultSet.getInt("file");
            final int rank = resultSet.getInt("rank");
            final String symbol = resultSet.getString("symbol");

            PieceDto pieceDto = new PieceDto(symbol, file, rank);
            pieces.add(pieceDto);
        }
        return pieces;
    }
}
