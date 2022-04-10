package chess.web.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.web.DBConnector;
import chess.web.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PieceDao {

    private static final String ERROR_DB_FAILED = "[ERROR] DB 연결에 문제가 발생했습니다.";

    public void save(int boardId, String target, PieceDto pieceDto) {
        final String sql = "insert into piece (board_id, position , color, role) values (?, ?, ?, ?)";

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.setString(2, target);
            statement.setString(3, pieceDto.getColor());
            statement.setString(4, pieceDto.getRole());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
    }

    public void saveAll(int boardId, Map<Position, Piece> pieces) {
        final String sql = "insert into piece (board_id, position, color, role) values (?, ?, ?, ?)";

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Position position : pieces.keySet()) {
                PieceDto piece = PieceDto.from(position, pieces.get(position));
                statement.setInt(1, boardId);
                statement.setString(2, piece.getPosition());
                statement.setString(3, piece.getColor());
                statement.setString(4, piece.getRole());
                statement.addBatch();
                statement.clearParameters();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
    }

    public Optional<PieceDto> findOne(int boardId, String position) {
        final String sql = "select * from piece where board_id = ? and position = ?";
        Optional<PieceDto> pieceDto = Optional.ofNullable(null);

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.setString(2, position);
            try (final ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pieceDto = Optional.of(new PieceDto(resultSet.getString("position"),
                            resultSet.getString("color"),
                            resultSet.getString("role")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
        return pieceDto;
    }

    public List<PieceDto> findAll(int boardId) {
        final String sql = "select * from piece where board_id = ?";
        List<PieceDto> pieceDtos = new ArrayList<>();

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            try (final ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    pieceDtos.add(new PieceDto(
                            resultSet.getString("position"),
                            resultSet.getString("color"),
                            resultSet.getString("role")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
        return pieceDtos;
    }

    public void updateOne(int boardId, String position, PieceDto pieceDto) {
        final String sql = "update piece set color = ?, role = ? where board_id = ? and position = ?";

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceDto.getColor());
            statement.setString(2, pieceDto.getRole());
            statement.setInt(3, boardId);
            statement.setString(4, position);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
    }

    public void deleteOne(int boardId, String position) {
        final String sql = "delete from piece where board_id = ? and position = ?";

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.setString(2, position);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
    }

    public void deleteAll(int boardId) {
        final String sql = "delete from piece where board_id = ?";

        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(ERROR_DB_FAILED);
        }
    }

}
