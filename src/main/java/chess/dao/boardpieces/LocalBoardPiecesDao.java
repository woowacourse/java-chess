package chess.dao.boardpieces;

import chess.domain.Camp;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class LocalBoardPiecesDao implements BoardPiecesDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Map<Position, Piece>> find(final int boardId) {
        final String sql = "SELECT p.position_file, p.position_rank, p.piece_type, p.piece_camp "
                + "FROM board_pieces as p, board_statuses as s "
                + "WHERE p.board_id = ? "
                + "AND p.board_id = s.board_id "
                + "AND s.is_over = 'N'";

        Map<Position, Piece> piecesByPosition = new HashMap<>();
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, boardId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int file = resultSet.getInt(1);
                int rank = resultSet.getInt(2);
                PieceType pieceType = PieceType.valueOf(resultSet.getString(3));
                Camp pieceCamp = Camp.valueOf(resultSet.getString(4));
                piecesByPosition.put(Position.of(file, rank), pieceType.createPiece(pieceCamp));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (piecesByPosition.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(piecesByPosition);
    }

    @Override
    public void insertOrUpdate(final int boardId, final Map<Position, Piece> piecesByPosition) {
        for (Entry<Position, Piece> square : piecesByPosition.entrySet()) {
            insertOrUpdatePiece(boardId, square.getKey(), square.getValue());
        }
    }

    private void insertOrUpdatePiece(final int boardId, final Position position, final Piece piece) {
        final String sql = "INSERT INTO board_pieces "
                + "(board_id, position_file, position_rank, piece_type, piece_camp) "
                + "VALUES (?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "position_file = ?, position_rank = ?, piece_type = ?, piece_camp = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, boardId);
            preparedStatement.setInt(2, position.getFile());
            preparedStatement.setInt(3, position.getRank());
            preparedStatement.setString(4, piece.getType().name());
            preparedStatement.setString(5, piece.getColor().name());
            preparedStatement.setInt(6, position.getFile());
            preparedStatement.setInt(7, position.getRank());
            preparedStatement.setString(8, piece.getType().name());
            preparedStatement.setString(9, piece.getColor().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int boardId) {
        final String sql = "DELETE FROM board_pieces WHERE board_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, boardId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
