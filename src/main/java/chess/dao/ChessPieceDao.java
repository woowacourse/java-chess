package chess.dao;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import chess.dto.ChessPieceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessPieceDao {

    public List<ChessPieceDto> findAllByRoomName(final String roomName) {
        final String sql = "SELECT * FROM chess_piece WHERE room_name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);

            try (final ResultSet resultSet = statement.executeQuery()) {

                final List<ChessPieceDto> dtos = new ArrayList<>();
                while (resultSet.next()) {
                    dtos.add(ChessPieceDto.of(
                            resultSet.getString("position"),
                            resultSet.getString("chess_piece"),
                            resultSet.getString("color")
                    ));
                }
                return dtos;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int deleteByPosition(final String roomName, final Position position) {
        final String sql = "DELETE FROM chess_piece WHERE room_name = ? AND position = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);
            statement.setString(2, position.getValue());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteAllByRoomName(final String roomName) {
        final String sql = "DELETE FROM chess_piece WHERE room_name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int saveAll(final String roomName, final Map<Position, ChessPiece> pieceByPosition) {
        String sql = "INSERT INTO chess_piece (room_name, position, chess_piece, color) VALUES ";
        sql += IntStream.range(0, pieceByPosition.size())
                .mapToObj(i -> "(?, ?, ?, ?)")
                .collect(Collectors.joining(", "));

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            int count = 1;
            for (final Entry<Position, ChessPiece> entry : pieceByPosition.entrySet()) {
                final Position position = entry.getKey();
                final ChessPiece chessPiece = entry.getValue();

                statement.setString(count++, roomName);
                statement.setString(count++, position.getValue());
                statement.setString(count++, ChessPieceMapper.toPieceType(chessPiece));
                statement.setString(count++, chessPiece.color().getValue());
            }

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(final String roomName, final Position from, final Position to) {
        final String sql = "UPDATE chess_piece SET position = ? WHERE room_name = ? AND position = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, to.getValue());
            statement.setString(2, roomName);
            statement.setString(3, from.getValue());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
