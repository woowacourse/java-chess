package chess.domain.dao;

import chess.domain.dto.TurnDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    private static final String INITIAL_TURN_COLOR = "WHITE";

    private DBConnector dbConnector = new DBConnector();

    public boolean isExistedTurn(int index) {
        String query = "SELECT * FROM turn WHERE (board_id = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, index);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void initTurn(int index) {
        if (isExistedTurn(index)){
            updateTurn(INITIAL_TURN_COLOR, index);
        }
        String query = "INSERT INTO turn(board_id, turn_color) VALUES(?, ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, index);
            pstmt.setString(2, INITIAL_TURN_COLOR);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTurn(String color, int index) {
        String query = "UPDATE turn SET turn_color = ? WHERE (board_id = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, color);
            pstmt.setInt(2, index);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TurnDto loadTurnDTO(int index) {
        String query = "SELECT * FROM turn WHERE (board_id = ?)";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, index);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return null;
            return new TurnDto(
                rs.getString("turn_color")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
