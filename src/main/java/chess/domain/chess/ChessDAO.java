package chess.domain.chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import chess.domain.ConnectionUtils;
import chess.domain.piece.PieceDTO;

public class ChessDAO {
    public Chess findChessById(Long chessId) {
        String query = "SELECT c.status, c.turn FROM chess c WHERE c.chess_id = (?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    final String status = resultSet.getString("status");
                    final String turn = resultSet.getString("turn");
                    final List<PieceDTO> pieceDTOS = findPiecesByChessId(chessId);
                    return Chess.of(pieceDTOS, status, turn);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new IllegalStateException("해당 Id에 해당하는 체스 게임이 존재하지 않습니다.");
    }

    private List<PieceDTO> findPiecesByChessId(Long chessId) {
        List<PieceDTO> pieceDTOS = new ArrayList<>();
        String query = "SELECT position, color, name FROM piece WHERE chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    final String position = resultSet.getString("position");
                    final String color = resultSet.getString("color");
                    final String name = resultSet.getString("name");
                    pieceDTOS.add(new PieceDTO(position, color, name));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pieceDTOS;
    }

    public long insert() {
        String query =
                "INSERT INTO chess (status, turn) VALUES ('RUNNING', 'WHITE')";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new IllegalStateException("체스 게임 생성에 실패했습니다.");
    }

    public void updateChess(Long chessId, String status, String turn) {
        String query =
                "UPDATE chess SET status = (?), turn = (?) WHERE chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setString(2, turn);
            pstmt.setLong(3, chessId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
