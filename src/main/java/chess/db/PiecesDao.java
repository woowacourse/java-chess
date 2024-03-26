package chess.db;

import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PiecesDao {
    private static final String TABLE = "pieces";

    private final Supplier<Connection> connector;

    public PiecesDao(Supplier<Connection> connector) {
        this.connector = connector;
    }

    public void create(PieceDto pieceDto) {
        try (final Connection connection = connector.get()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + TABLE + " VALUES (?, ?, ?)");
            statement.setInt(1, pieceDto.file());
            statement.setInt(2, pieceDto.rank());
            statement.setString(3, pieceDto.type());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating piece", e);
        }
    }

    public List<PieceDto> findAll() {
        try (final Connection connection = connector.get()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE);
            ResultSet resultSet = statement.executeQuery();

            List<PieceDto> pieces = new ArrayList<>();
            while (resultSet.next()) {
                int file = resultSet.getInt("board_file");
                int rank = resultSet.getInt("board_rank");
                String type = resultSet.getString("type");
                pieces.add(new PieceDto(file, rank, type));
            }
            return pieces;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding pieces", e);
        }
    }

    public void deleteAll() {
        try (Connection connection = connector.get()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting pieces", e);
        }
    }

    public int count() {
        try (final Connection connection = connector.get()) {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM " + TABLE);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error counting piece", e);
        }
    }
}
