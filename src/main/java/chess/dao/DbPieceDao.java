package chess.dao;

import chess.dto.PieceDto;
import chess.piece.detail.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbPieceDao implements PieceDao {

    @Override
    public void save(final int boardId, final PieceDto pieceDto) {
        final String sql = "insert into piece (board_id, name, position, color) values (?, ?, ?, ?)";

        try (Connection connection = DbConnector.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, boardId);
            statement.setString(2, pieceDto.getName());
            statement.setString(3, pieceDto.getPosition());
            statement.setString(4, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
