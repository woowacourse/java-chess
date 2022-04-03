package chess.dao;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import chess.dto.ChessPieceMapper;
import java.sql.Connection;
import java.sql.DriverManager;
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

    public ChessPieceDto findByPosition(final Position position) {
        final String sql = "SELECT * FROM ChessBoard WHERE Position = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, position.getValue());

            try (final ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return ChessPieceDto.of(
                        resultSet.getString("Position"),
                        resultSet.getString("ChessPiece"),
                        resultSet.getString("Color")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChessPieceDto> findAll() {
        final String sql = "SELECT * FROM ChessBoard";

        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {

            final List<ChessPieceDto> dtos = new ArrayList<>();
            while (resultSet.next()) {
                dtos.add(ChessPieceDto.of(
                        resultSet.getString("Position"),
                        resultSet.getString("ChessPiece"),
                        resultSet.getString("Color")
                ));
            }
            return dtos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int deleteByPosition(final Position position) {
        final String sql = "DELETE FROM ChessBoard WHERE Position = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getValue());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteAll() {
        final String sql = "DELETE FROM ChessBoard";

        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int save(final Position position, final ChessPiece chessPiece) {
        final String sql = "INSERT INTO ChessBoard(Position, ChessPiece, Color) VALUES (?, ?, ?)";

        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, position.getValue());
            statement.setString(2, ChessPieceMapper.toPieceType(chessPiece));
            statement.setString(3, chessPiece.color().getValue());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int saveAll(final Map<Position, ChessPiece> pieceByPosition) {
        String sql = "INSERT INTO ChessBoard(Position, ChessPiece, Color) VALUES ";
        sql += IntStream.range(0, pieceByPosition.size())
                .mapToObj(i -> "(?, ?, ?)")
                .collect(Collectors.joining(", "));

        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            int count = 1;
            for (final Entry<Position, ChessPiece> entry : pieceByPosition.entrySet()) {
                final Position position = entry.getKey();
                final ChessPiece chessPiece = entry.getValue();

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

    private Connection getConnection() {
        final String url = "jdbc:mysql://localhost:3306/chess";
        final String userName = "root";
        final String password = "root";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
