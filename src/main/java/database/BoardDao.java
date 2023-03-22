package database;

import chess.domain.board.*;
import chess.domain.piece.Piece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public final class BoardDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호
    private static final int BOARD_SIZE = 8;

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void saveBoard(Board board) {

        List<Squares> squares = board.getSquares();

        List<List<Piece>> collect = squares.stream()
                .map(Squares::getPieces)
                .collect(Collectors.toList());

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Piece piece = collect.get(i).get(j);
                final var rank = Rank.from(i + 1);
                final var file = File.from(j + 1);
                final var position = new Position(file, rank);
                final var color = piece.getColor();

                final var query = "INSERT INTO board VALUES(?, ?, ?)";
                try (final var connection = getConnection();
                     final var preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, position.toString());
                    preparedStatement.setString(2, piece.getName());
                    preparedStatement.setString(3, color.name());
                    preparedStatement.executeUpdate();
                } catch (final SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
