package chess.domain.piece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chess.domain.ConnectionUtils;
import chess.domain.board.BoardDTO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionVO;

public class PieceDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PieceDAO.class);

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

    public void move(Long chessId, MovePosition movePosition) {
        MovePositionVO movePositionVO = MovePositionVO.from(movePosition);
        updateTargetPosition(chessId, movePositionVO);
        updateSourcePosition(movePositionVO);
    }

    private void updateSourcePosition(MovePositionVO movePositionVO) {
        String query = "UPDATE piece SET color = 'BLANK', name = 'BLANK' WHERE position = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movePositionVO.getSource());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateTargetPosition(Long chessId, MovePositionVO movePositionVO) {
        String query =
                "UPDATE piece AS target, (SELECT color, name FROM piece WHERE position = (?) AND chess_id = (?)) AS source " +
                        "SET target.color = source.color, target.name = source.name " +
                        "WHERE target.position = (?) AND target.chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movePositionVO.getSource());
            pstmt.setLong(2, chessId);
            pstmt.setString(3, movePositionVO.getTarget());
            pstmt.setLong(4, chessId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
