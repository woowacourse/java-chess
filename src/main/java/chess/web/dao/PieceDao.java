package chess.web.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.web.DBConnector;
import chess.web.dto.PieceDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PieceDao {

    public void saveOne(PieceDto pieceDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into piece (position , color, role) values (?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pieceDto.getPosition());
            statement.setString(2, pieceDto.getColor());
            statement.setString(3, pieceDto.getRole());
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAll(Map<Position, Piece> pieces) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into piece (position, color, role) values (?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Position position : pieces.keySet()) {
                PieceDto piece = PieceDto.from(position, pieces.get(position));
                statement.setString(1, piece.getPosition());
                statement.setString(2, piece.getColor());
                statement.setString(3, piece.getRole());
                statement.addBatch();
                statement.clearParameters();
            }
            statement.executeBatch();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<PieceDto> findOne(String position) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select * from piece where position = ?";
        Optional<PieceDto> pieceDto = Optional.ofNullable(null);
        try (final PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, position);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pieceDto = Optional.of(new PieceDto(resultSet.getString("position"),
                        resultSet.getString("color"),
                        resultSet.getString("role")));
            }
            closeResources(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieceDto;
    }

    public List<PieceDto> findAll() {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select * from piece";
        List<PieceDto> pieceDtos = new ArrayList<>();
        try (final PreparedStatement statement = connection.prepareStatement(sql);) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pieceDtos.add(new PieceDto(
                        resultSet.getString("position"),
                        resultSet.getString("color"),
                        resultSet.getString("role")
                ));
            }
            closeResources(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieceDtos;
    }

    public void updateOne(String position, PieceDto pieceDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "update piece set color = ?, role = ? where position = ?";
        try (final PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, pieceDto.getColor());
            statement.setString(2, pieceDto.getRole());
            statement.setString(3, position);
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(String position) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "delete from piece where position = ?";
        try (final PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, position);
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        final Connection connection = DBConnector.getConnection();
        final String sql = "delete from piece";
        try (final Statement statement = connection.createStatement();) {
            statement.execute(sql);
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResources(Statement statement, Connection connection) throws SQLException {
        statement.close();
        connection.close();
    }

    private void closeResources(ResultSet resultSet, Statement statement, Connection connection)
            throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }
}
