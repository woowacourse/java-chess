package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domain.PieceNameConverter;
import domain.piece.Piece;

public class ChessDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

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

    public void save(Map<String, String> chessBoard) {
        final var query = "INSERT INTO chess_board VALUES(?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (Map.Entry<String, String> squareAndPiece : chessBoard.entrySet()) {
                preparedStatement.setString(1, squareAndPiece.getKey());
                preparedStatement.setString(2, squareAndPiece.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> read() {
        final var query = "SELECT * FROM chess_board";
        try (final var connection = getConnection();
        final var preparedStatement = connection.prepareStatement(query)) {
            HashMap<String, String> board = new HashMap<>();
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                board.put(resultSet.getString(1), resultSet.getString(2));
            }
            return board;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String select(String square) {
        final var query = "SELECT piece FROM chess_board WHERE square = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(Map<String, String> chessBoard) {
        final var query = "UPDATE chess_board SET piece = ? WHERE square = ?";
        for (Map.Entry<String, String> squareAndPiece : chessBoard.entrySet()) {
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, squareAndPiece.getValue());
                preparedStatement.setString(2, squareAndPiece.getKey());
                preparedStatement.execute();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete() {
        final var query = "DELETE FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasData() {
        final var query = "SELECT * FROM chess_board WHERE EXISTS (SELECT * FROM chess_board)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveCamp(String camp) {
        final var query = "INSERT INTO camp VALUES(?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, camp);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String selectCamp() {
        final var query = "SELECT * FROM camp";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("camp");
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateCamp(String camp) {
        final var query = "UPDATE camp SET camp = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, camp);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
