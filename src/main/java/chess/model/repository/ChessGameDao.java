package chess.model.repository;

import chess.model.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessGameDao extends ChessDB {

    private final static ChessGameDao INSTANCE = new ChessGameDao();

    private ChessGameDao() {
    }

    public static ChessGameDao getInstance() {
        return INSTANCE;
    }

    public int insert(int roomId, Color gameTurn, Map<Color, String> userNames)
        throws SQLException {
        String query = "INSERT INTO CHESS_GAME_TB(ROOM_ID, TURN_NM, BLACK_USER_NM, WHITE_USER_NM) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, roomId);
            pstmt.setString(2, gameTurn.getName());
            pstmt.setString(3, userNames.get(Color.BLACK));
            pstmt.setString(4, userNames.get(Color.WHITE));
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException();
            }
        }
    }

    public void updateProceedN(int gameId) throws SQLException {
        String query = "UPDATE CHESS_GAME_TB SET PROCEEDING_YN = 'N' WHERE ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
        }
    }

    public void updateTurn(int gameId, Color gameTurn) throws SQLException {
        String query = "UPDATE CHESS_GAME_TB SET TURN_NM = ? WHERE ID = ? AND PROCEEDING_YN = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameTurn.getName());
            pstmt.setInt(2, gameId);
            pstmt.setString(3, "Y");
            pstmt.executeUpdate();
        }
    }

    public void delete(int gameId) throws SQLException {
        String query = "DELETE FROM CHESS_GAME_TB WHERE ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            pstmt.executeUpdate();
        }
    }

    // TODO ROOMID와 연동 - 조인해서 가져오기
    public Optional<Integer> getGameNumberLatest(int roomId) throws SQLException {
        String query = "SELECT ID FROM CHESS_GAME_TB WHERE ID LIKE ? ORDER BY ID DESC";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(rs.getInt("ID"));
            }
        }
    }

    public Optional<Color> getGameTurn(int gameId) throws SQLException {
        String query = "SELECT TURN_NM FROM CHESS_GAME_TB WHERE ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.ofNullable(Color.of(rs.getString("TURN_NM")));
            }
        }
    }

    public Map<Color, String> getUserNames(int gameId) throws SQLException {
        String query = "SELECT BLACK_USER_NM, WHITE_USER_NM FROM CHESS_GAME_TB WHERE ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
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

    public Optional<Boolean> isProceeding(int gameId) throws SQLException {
        String query = "SELECT PROCEEDING_YN FROM CHESS_GAME_TB WHERE ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, gameId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(rs.getString("PROCEEDING_YN").equalsIgnoreCase("Y"));
            }
        }
    }

}
