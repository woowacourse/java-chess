package chess.domain.piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.domain.ConnectionUtils;
import chess.domain.board.BoardDTO;
import chess.domain.position.MovePositionDTO;

public class PieceDAO {
    public void insert(Long chessId, BoardDTO boardDTO) {
        String query = "INSERT INTO piece (chess_id, position, color, name) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (PieceDTO pieceDTO : boardDTO.getPieceDTOS()) {
                String position = pieceDTO.getPosition();
                String color = pieceDTO.getColor();
                String name = pieceDTO.getName();

                pstmt.setLong(1, chessId);
                pstmt.setString(2, position);
                pstmt.setString(3, color);
                pstmt.setString(4, name);
                pstmt.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void move(Long chessId, MovePositionDTO movePositionDTO) {
        updateSourcePosition(chessId, movePositionDTO);
        updateTargetPosition(movePositionDTO);
    }

    private void updateTargetPosition(MovePositionDTO movePositionDTO) {
        String query = "UPDATE piece SET color = 'BLANK', name = 'BLANK' WHERE position = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movePositionDTO.getSource());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateSourcePosition(Long chessId, MovePositionDTO movePositionDTO) {
        String query =
                "UPDATE piece, (SELECT color, name FROM piece WHERE position = (?)) AS source " +
                        "SET piece.color = source.color, piece.name = source.name " +
                        "WHERE position = (?) AND chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movePositionDTO.getSource());
            pstmt.setString(2, movePositionDTO.getTarget());
            pstmt.setLong(3, chessId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Long chessId) {
        String query = "DELETE FROM piece WHERE chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
