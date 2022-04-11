package chess.dao;

import chess.db.DBConnector;
import chess.domain.position.Position;
import chess.dto.PieceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDaoImpl implements PieceDao {

    private final DBConnector dbConnector;

    public PieceDaoImpl(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public void remove(Position position) {
        final String sql = "delete from piece where position = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll() {
        final String sql = "delete from piece";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll(List<PieceDto> pieceDtos) {
        String sql = "insert into piece (position, type, color) values (?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (PieceDto pieceDto : pieceDtos) {
                statement.setString(1, pieceDto.getPosition());
                statement.setString(2, pieceDto.getType());
                statement.setString(3, pieceDto.getColor());
                statement.addBatch();
                statement.clearParameters();
            }
            statement.executeBatch();
            statement.clearBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(PieceDto pieceDto) {
        final String sql = "insert into piece (position, type, color) values (?, ?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceDto.getPosition());
            statement.setString(2, pieceDto.getType());
            statement.setString(3, pieceDto.getColor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PieceDto> findAll() {
        final String sql = "select * from piece";
        List<PieceDto> pieces = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pieces.add(
                        PieceDto.of(
                                resultSet.getString("position"),
                                resultSet.getString("color"),
                                resultSet.getString("type")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    @Override
    public void update(Position source, Position target) {
        final String sql = "update piece set position = ? where position = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, target.getName());
            statement.setString(2, source.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
