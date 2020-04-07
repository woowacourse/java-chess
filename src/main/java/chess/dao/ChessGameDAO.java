package chess.dao;

import chess.domain.piece.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class ChessGameDAO {

    public void insert(String gameId, Color gameTurn, Map<Color, String> userNames)
        throws SQLException {
        String query = "INSERT INTO CHESS_GAME_TB(GAME_ID, TURN_NM, PROCEEDING_YN, BLACK_USER_NM, WHITE_USER_NM) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.setString(2, gameTurn.getName());
        pstmt.setString(3, "Y");
        pstmt.setString(4, userNames.get(Color.BLACK));
        pstmt.setString(5, userNames.get(Color.WHITE));
        pstmt.executeUpdate();
    }

    public void updateProceedN(String gameId) throws SQLException {
        String query = "UPDATE CHESS_GAME_TB SET PROCEEDING_YN = ? WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, "N");
        pstmt.setString(2, gameId);
        pstmt.executeUpdate();
    }

    public void updateTurn(String gameId, Color gameTurn) throws SQLException {
        String query = "UPDATE CHESS_GAME_TB SET TURN_NM = ? WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameTurn.getName());
        pstmt.setString(2, gameId);
        pstmt.executeUpdate();
    }

    public void delete(String gameId) throws SQLException {
        String query = "DELETE FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        pstmt.executeUpdate();
    }

    public int getGameNumberLatest(String roomId) throws SQLException, IllegalAccessException {
        validationRoomId(roomId);
        String query = "SELECT GAME_ID FROM CHESS_GAME_TB WHERE GAME_ID LIKE ? ORDER BY GAME_ID DESC";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String roomNumberDate = String.format("%s-%s-", roomId, formatDate);
        pstmt.setString(1, roomNumberDate + "%");
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return 0;
        }
        return Integer.parseInt(rs.getString("GAME_ID").substring(13));
    }

    private void validationRoomId(String roomId) throws IllegalAccessException {
        NullChecker.validateNotNull(roomId);
        if (roomId.length() != 3) {
            throw new IllegalAccessException("인자 잘못됨");
        }
    }

    public Color getGameTurn(String gameId) throws SQLException, IllegalAccessException {
        String query = "SELECT TURN_NM FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            throw new IllegalAccessException("인자 없음");
        }
        return Color.of(rs.getString("TURN_NM"));
    }

    public Map<Color, String> getUserNames(String gameId)
        throws SQLException, IllegalAccessException {
        String query = "SELECT BLACK_USER_NM, WHITE_USER_NM FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();

        Map<Color, String> userNames = new HashMap<>();
        if (!rs.next()) {
            throw new IllegalAccessException("인자 없음");
        }
        userNames.put(Color.BLACK, rs.getString("BLACK_USER_NM"));
        userNames.put(Color.WHITE, rs.getString("WHITE_USER_NM"));

        return Collections.unmodifiableMap(userNames);
    }

    public boolean isProceeding(String gameId) throws SQLException, IllegalAccessException {
        String query = "SELECT PROCEEDING_YN FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        PreparedStatement pstmt = ChessDB.getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            throw new IllegalAccessException("인자 없음");
        }
        return rs.getString("PROCEEDING_YN").equalsIgnoreCase("Y");
    }

}
