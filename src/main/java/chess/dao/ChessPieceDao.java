package chess.dao;

import chess.domain.position.Position;
import chess.dto.ChessPieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
