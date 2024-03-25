package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
    private static final String tableName = "pieces";
    private final ConnectionManager connectionManager;

    private PieceDao(final ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public PieceDao() {
        this(new ConnectionManager());
    }

    public void add(final PieceDto piece) {
        final var query = "INSERT INTO " + tableName + " VALUES(?, ?, ?, ?)";
        try (final var connection = connectionManager.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.boardFile());
            preparedStatement.setString(2, piece.boardRank());
            preparedStatement.setString(3, piece.color());
            preparedStatement.setString(4, piece.type());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PieceDto find(final String file, final String rank) {
        final var query = "SELECT * FROM " + tableName + " WHERE board_file = ? and board_rank = ?";
        try (final var connection = connectionManager.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, file);
            preparedStatement.setString(2, rank);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new PieceDto(file,
                        rank,
                        resultSet.getString("color"),
                        resultSet.getString("type")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<PieceDto> findAll() {
        final List<PieceDto> pieces = new ArrayList<>();
        final String query = "SELECT * FROM " + tableName;
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String file = resultSet.getString("board_file");
                String rank = resultSet.getString("board_rank");
                String color = resultSet.getString("color");
                String type = resultSet.getString("type");
                pieces.add(new PieceDto(file, rank, color, type));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return pieces;
    }

    public void deleteAll() {
        final var query = "DELETE FROM " + tableName;
        try (final var connection = connectionManager.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int count() {
        final var query = "SELECT COUNT(*) AS count FROM " + tableName;
        try (final var connection = connectionManager.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
