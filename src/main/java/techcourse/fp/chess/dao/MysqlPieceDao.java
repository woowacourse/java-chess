package techcourse.fp.chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceFactory;
import techcourse.fp.chess.domain.piece.PieceType;

public class MysqlPieceDao implements PieceDao {

    @Override
    public void save(final Map<Position, Piece> board, final int chessGameId) {
        final String query = "INSERT INTO piece(chess_game_id, piece_file, piece_rank, color, type) VALUES (?, ?, ?, ?, ?);";

        try (final Connection connection = ConnectionProvider.getConnection()) {

            for (Position position : board.keySet()) {
                final PreparedStatement ps = connection.prepareStatement(query);

                final Piece piece = board.get(position);

                ps.setInt(1, chessGameId);
                ps.setString(2, position.getFile().name());
                ps.setInt(3, position.getRank().getOrder());
                ps.setString(4, piece.getColor().name());
                ps.setString(5, piece.getPieceType().name());

                ps.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Map<Position, Piece> findByGameId(final int chessGameId) {
        try (final Connection connection = ConnectionProvider.getConnection()) {
            final String query = "SELECT piece_file, piece_rank, color, type " +
                    "FROM piece " +
                    "where chess_game_id = ?";

            final PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, chessGameId);
            final ResultSet resultSet = ps.executeQuery();

            Map<Position, Piece> board = new HashMap<>();

            while (resultSet.next()) {
                final String file = resultSet.getString("piece_file");
                final int rank = resultSet.getInt("piece_rank");
                final String color = resultSet.getString("color");
                final String type = resultSet.getString("type");

                final Piece piece = PieceFactory.generate(Color.createByName(color), PieceType.createByName(type));

                board.put(Position.createByName(file, rank), piece);
            }

            return board;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
