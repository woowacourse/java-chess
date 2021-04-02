package chess.domain.position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.domain.ConnectionUtils;

public class MovePositionDAO {
    public void move(Long userId, MovePositionDTO movePositionDTO) throws SQLException {
        String query =
                "INSERT INTO move (chess_id, source, target) " +
                        "SELECT c.chess_id, '" + movePositionDTO.getSource() + "' , '" +
                        movePositionDTO.getTarget() + "' " +
                        "FROM chess c RIGHT JOIN user u on u.user_id = c.user_id " +
                        "WHERE c.user_id = (?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, userId);
            pstmt.executeUpdate();
        }
    }
}
