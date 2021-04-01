package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.DAO.closeConnection;
import static chess.dao.DAO.getConnection;

public class ChessGameDAO {

    public void updateChessGameState() throws SQLException {
        String query = "UPDATE chessgame SET is_end = ? WHERE is_end = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setBoolean(1, true);
        pstmt.setBoolean(2, false);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }

    public void addMoveCommand(String argument) throws SQLException {
        String query = "INSERT INTO chessgame VALUES (?, DEFAULT, CURRENT_TIMESTAMP)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, argument);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }

    public void deleteMoveCommand(String argument) throws SQLException {
        String query = "DELETE FROM chessgame WHERE command = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, argument);
        pstmt.executeUpdate();
        closeConnection(getConnection());
    }

    public List<String> getRunningGameMove() throws SQLException {
        String query = "SELECT command FROM chessgame WHERE is_end = false ORDER BY date_time ASC";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<String> moves = new ArrayList<>();
        while (rs.next()) {
            String move = rs.getString("command");
            moves.add(move);
        }
        return moves;
    }
}
