package chess.controller.dao;

import chess.controller.dto.ChessBoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private static final String SERVER_URL = "jdbc:mysql://localhost:13306/chess?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(SERVER_URL, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

//    public int save(ChessBoardDto chessBoardDto) {
//        final var query = "INSERT INTO movement(source, destination) VALUES(?, ?)";
//        try (
//            final var connection = getConnection();
//            final var preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, chessBoardDto.getLines());
//            preparedStatement.setString(2, chessBoardDto.destinationCoordinate());
//            return preparedStatement.executeUpdate();
//        } catch (final SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
