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

    public void save(int boardId, PieceDto pieceDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into piece (board_id, position , color, role) values (?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.setString(2, pieceDto.getPosition());
            statement.setString(3, pieceDto.getColor());
            statement.setString(4, pieceDto.getRole());
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAll(int boardId, Map<Position, Piece> pieces) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into piece (board_id, position, color, role) values (?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
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
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<PieceDto> findOne(int boardId, String position) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select * from piece where board_id = ? and position = ?";
        Optional<PieceDto> pieceDto = Optional.ofNullable(null);
        try (final PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, boardId);
            statement.setString(2, position);
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

    public List<PieceDto> findAll(int boardId) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select * from piece where board_id = ?";
        List<PieceDto> pieceDtos = new ArrayList<>();
        try (final PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, boardId);
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

    public void updateOne(int boardId, String position, PieceDto pieceDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "update piece set color = ?, role = ? where board_id = ? and position = ?";
        try (final PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, pieceDto.getColor());
            statement.setString(2, pieceDto.getRole());
            statement.setInt(3, boardId);
            statement.setString(4, position);
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(int boardId, String position) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "delete from piece where board_id = ? and position = ?";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.setString(2, position);
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(int boardId) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "delete from piece where board_id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            statement.executeUpdate();
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
