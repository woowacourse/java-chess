package chess.domain.dao;

import chess.domain.dto.TurnDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    private static final String INITIAL_TURN_COLOR = "WHITE";
    private static final int SINGLE_BOARD_NUMBER  = 1;

    private DBConnector dbConnector = new DBConnector();

    public void initTurn() {
        String query = "INSERT INTO turn(board_id, turn_color) VALUES(?, WHITE)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            try {
                pstmt.setInt(1, SINGLE_BOARD_NUMBER);
                pstmt.executeUpdate();
            } catch (SQLException existedBoardException) {
                updateTurn(INITIAL_TURN_COLOR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTurn(String color) {
        String query = "UPDATE turn SET turn_color = ? WHERE board_id = ?";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, color);
            pstmt.setInt(2, SINGLE_BOARD_NUMBER);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TurnDTO loadTurnDTO(int boardNumber) {
        String query = "SELECT * FROM turn WHERE (board_id = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SINGLE_BOARD_NUMBER);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return new TurnDTO(
                rs.getString("turn_color")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
