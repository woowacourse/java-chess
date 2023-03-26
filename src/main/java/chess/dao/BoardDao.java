package chess.dao;

import chess.domain.ChessGame;
import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.dto.BoardDto;
import chess.dto.PieceRenderer;
import chess.dto.SquareRenderer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            return null;
        }
    }

    // TODO: 2023-03-24 서비스 객체를 만들어야 하나 ? DTO로 전달 받아야하기 떄문에...?
    public void save(ChessGame chessGame) {
        String query = "INSERT INTO board VALUES(?, ?, ?, ?)";
        Chessboard board = chessGame.getChessboard();
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            for (Square square : board.getBoardMap().keySet()) {
                String source = SquareRenderer.render(square);
                String piece = PieceRenderer.render(board.getPieceAt(square));

                prepareStatement.setString(1, null); // auto_increment
                prepareStatement.setString(2, source);
                prepareStatement.setString(3, piece);
                prepareStatement.setString(4, chessGame.getRoomName());
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<BoardDto> findAllByRoomName(String roomName) {
        String query = "SELECT * FROM board WHERE room_name = ?";
        List<BoardDto> result = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, roomName);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new BoardDto(
                        resultSet.getString("source"),
                        resultSet.getString("piece"),
                        resultSet.getString("room_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return result;
    }

    public void update(ChessGame chessGame) {
        String query = "UPDATE board SET piece = ? WHERE source = ? AND room_name = ?";

        Chessboard board = chessGame.getChessboard();
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            for (Square square : board.getBoardMap().keySet()) {
                String source = SquareRenderer.render(square);
                String piece = PieceRenderer.render(board.getPieceAt(square));

                prepareStatement.setString(1, piece);
                prepareStatement.setString(2, source);
                prepareStatement.setString(3, chessGame.getRoomName());
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteAllByRoomName(String roomName) {
        String query = "DELETE FROM board WHERE room_name = ?";

        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, roomName);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
