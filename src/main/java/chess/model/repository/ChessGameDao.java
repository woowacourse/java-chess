package chess.model.repository;

import static chess.model.repository.connector.ChessMySqlConnector.getConnection;

import chess.model.domain.piece.Color;
import chess.model.repository.template.JdbcTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessGameDao {


    private final static ChessGameDao INSTANCE = new ChessGameDao();

    private ChessGameDao() {
    }

    public static ChessGameDao getInstance() {
        return INSTANCE;
    }

    public int insert(int roomId, Color gameTurn, Map<Color, String> userNames)
        throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameterUpdate(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, roomId);
                pstmt.setString(2, gameTurn.getName());
                pstmt.setString(3, userNames.get(Color.BLACK));
                pstmt.setString(4, userNames.get(Color.WHITE));
                pstmt.executeUpdate();
            }
        };
        String query = "INSERT INTO CHESS_GAME_TB(ROOM_ID, TURN_NM, BLACK_USER_NM, WHITE_USER_NM) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.executeUpdateWithGeneratedKey(query);
    }

    public void updateProceedN(int gameId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameterUpdate(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, gameId);
                pstmt.executeUpdate();
            }
        };
        String query = "UPDATE CHESS_GAME_TB SET PROCEEDING_YN = 'N' WHERE ID = ?";
        jdbcTemplate.executeUpdate(query);
    }

    public void updateTurn(int gameId, Color gameTurn) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameterUpdate(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, gameTurn.getName());
                pstmt.setInt(2, gameId);
                pstmt.executeUpdate();
            }
        };
        String query = "UPDATE CHESS_GAME_TB SET TURN_NM = ? WHERE ID = ? AND PROCEEDING_YN = 'Y'";
        jdbcTemplate.executeUpdate(query);
    }

    public void delete(int gameId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameterUpdate(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, gameId);
                pstmt.executeUpdate();
            }
        };
        String query = "DELETE FROM CHESS_GAME_TB WHERE ID = ?";
        jdbcTemplate.executeUpdate(query);
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

    public void updateProceedNByRoomId(int roomId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameterUpdate(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, roomId);
                pstmt.executeUpdate();
            }
        };
        StringBuilder query = new StringBuilder();
        query.append("UPDATE CHESS_GAME_TB");
        query.append("   SET PROCEEDING_YN = 'N'");
        query.append(" WHERE ID IN ( ");
        query.append("SELECT ID ");
        query.append("FROM ( ");
        query.append("SELECT GAME.ID ");
        query.append("  FROM CHESS_GAME_TB AS GAME ");
        query.append("  JOIN ROOM_TB AS ROOM ");
        query.append(" WHERE GAME.ROOM_ID = ROOM.ID ");
        query.append("   AND ROOM.ID = ?) AS ID_TB)");
        jdbcTemplate.executeUpdate(query.toString());
    }

    public List<String> getUsers() throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT(BLACK_USER_NM) AS NM ");
        query.append("FROM CHESS_GAME_TB ");
        query.append("WHERE PROCEEDING_YN = 'N' ");
        query.append("UNION ALL ");
        query.append("SELECT DISTINCT(WHITE_USER_NM) ");
        query.append("FROM CHESS_GAME_TB ");
        query.append("WHERE PROCEEDING_YN = 'N' ");
        query.append("ORDER BY NM ");
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            ResultSet rs = pstmt.executeQuery()) {
            List<String> users = new ArrayList<>();
            while (rs.next()) {
                users.add(rs.getString("NM"));
            }
            return users;
        }
    }
}
