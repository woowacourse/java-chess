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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessPieceDao {

    public ChessPieceDto findByPosition(final Position position) throws SQLException {
        final String sql = "SELECT * FROM ChessBoard WHERE Position = ?";

        final Connection connection = getConnection();
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, position.getValue());
        final ResultSet resultSet = statement.executeQuery();

        try (connection; statement; resultSet) {
            if (!resultSet.next()) {
                return null;
            }
            return ChessPieceDto.of(
                    resultSet.getString("Position"),
                    resultSet.getString("ChessPiece"),
                    resultSet.getString("Color")
            );
        }
    }

    public List<ChessPieceDto> findAll() throws SQLException {
        final String sql = "SELECT * FROM ChessBoard";

        final Connection connection = getConnection();
        final PreparedStatement statement = connection.prepareStatement(sql);
        final ResultSet resultSet = statement.executeQuery();

        try (connection; statement; resultSet) {
            final List<ChessPieceDto> dtos = new ArrayList<>();
            while (resultSet.next()) {
                dtos.add(ChessPieceDto.of(
                        resultSet.getString("Position"),
                        resultSet.getString("ChessPiece"),
                        resultSet.getString("Color")
                ));
            }
            return dtos;
        }
    }

    public int deleteByPosition(final Position position) throws SQLException {
        final String sql = "DELETE FROM ChessBoard WHERE Position = ?";

        final Connection connection = getConnection();
        final PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, position.getValue());
        try (connection; statement) {
            return statement.executeUpdate();
        }
    }

    public int save(final Position position, final ChessPiece chessPiece) throws SQLException {
        final String sql = "INSERT INTO ChessBoard(Position, ChessPiece, Color) VALUES (?, ?, ?)";

        final Connection connection = getConnection();
        final PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, position.getValue());
        statement.setString(2, ChessPieceMapper.toPieceType(chessPiece));
        statement.setString(3, chessPiece.color().getValue());

        try (connection; statement) {
            return statement.executeUpdate();
        }
    }

    public int saveAll(final Map<Position, ChessPiece> pieceByPosition) throws SQLException {
        String sql = "INSERT INTO ChessBoard(Position, ChessPiece, Color) VALUES ";
        sql += IntStream.range(0, pieceByPosition.size())
                .mapToObj(i -> "(?, ?, ?)")
                .collect(Collectors.joining(", "));

        final Connection connection = getConnection();
        final PreparedStatement statement = connection.prepareStatement(sql);

        int count = 1;
        for (final Entry<Position, ChessPiece> entry : pieceByPosition.entrySet()) {
            final Position position = entry.getKey();
            final ChessPiece chessPiece = entry.getValue();

            statement.setString(count++, position.getValue());
            statement.setString(count++, ChessPieceMapper.toPieceType(chessPiece));
            statement.setString(count++, chessPiece.color().getValue());
        }

        try (connection; statement) {
            return statement.executeUpdate();
        }
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
