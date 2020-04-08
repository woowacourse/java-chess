package chess.model.repository;

import chess.model.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import util.NullChecker;

public class ChessGameDao extends ChessDB {

    private final static ChessGameDao INSTANCE = new ChessGameDao();

    private ChessGameDao() {
    }

    public static ChessGameDao getInstance() {
        return INSTANCE;
    }


    public void insert(String gameId, Color gameTurn, Map<Color, String> userNames)
        throws SQLException {
        String query = "INSERT INTO CHESS_GAME_TB(GAME_ID, TURN_NM, PROCEEDING_YN, BLACK_USER_NM, WHITE_USER_NM) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.setString(2, gameTurn.getName());
            pstmt.setString(3, "Y");
            pstmt.setString(4, userNames.get(Color.BLACK));
            pstmt.setString(5, userNames.get(Color.WHITE));
            pstmt.executeUpdate();
        }
    }

    public void updateProceedN(String gameId) throws SQLException {
        String query = "UPDATE CHESS_GAME_TB SET PROCEEDING_YN = ? WHERE GAME_ID = ? AND PROCEEDING_YN = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "N");
            pstmt.setString(2, gameId);
            pstmt.setString(3, "Y");
            pstmt.executeUpdate();
        }
    }

    public void updateTurn(String gameId, Color gameTurn) throws SQLException {
        String query = "UPDATE CHESS_GAME_TB SET TURN_NM = ? WHERE GAME_ID = ? AND PROCEEDING_YN = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameTurn.getName());
            pstmt.setString(2, gameId);
            pstmt.setString(3, "Y");
            pstmt.executeUpdate();
        }
    }

    public void delete(String gameId) throws SQLException {
        String query = "DELETE FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        }
    }

    public int getGameNumberLatest(String roomNumberDate)
        throws SQLException, IllegalAccessException {
        validationRoomId(roomNumberDate);
        String query = "SELECT GAME_ID FROM CHESS_GAME_TB WHERE GAME_ID LIKE ? ORDER BY GAME_ID DESC";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, roomNumberDate + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return 0;
                }
                return Integer.parseInt(rs.getString("GAME_ID").substring(13));
            }
        }
    }

    private void validationRoomId(String roomNumberDate) throws IllegalAccessException {
        NullChecker.validateNotNull(roomNumberDate);
        if (roomNumberDate.length() != 13) {
            throw new IllegalAccessException("인자 잘못됨");
        }
    }

    public Optional<Color> getGameTurn(String gameId) throws SQLException {
        String query = "SELECT TURN_NM FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.ofNullable(Color.of(rs.getString("TURN_NM")));
            }
        }
    }

    public Map<Color, String> getUserNames(String gameId) throws SQLException {
        String query = "SELECT BLACK_USER_NM, WHITE_USER_NM FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameId);
            Map<Color, String> userNames = new HashMap<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return userNames;
                }
                userNames.put(Color.BLACK, rs.getString("BLACK_USER_NM"));
                userNames.put(Color.WHITE, rs.getString("WHITE_USER_NM"));
            }
            return Collections.unmodifiableMap(userNames);
        }
    }

    public Optional<Boolean> isProceeding(String gameId) throws SQLException {
        String query = "SELECT PROCEEDING_YN FROM CHESS_GAME_TB WHERE GAME_ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(rs.getString("PROCEEDING_YN").equalsIgnoreCase("Y"));
            }
        }
    }

}
