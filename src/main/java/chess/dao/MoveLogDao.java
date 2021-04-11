package chess.dao;

import chess.db.DBConnection;
import chess.domain.Position;
import chess.util.StringPositionConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoveLogDao {

    private final DBConnection dbConnection = DBConnection.getInstance();

    public void addMoveLog(String boardName, String currentPosition, String targetPosition) {
        String sql = "INSERT INTO move_log(board_name, current_position, target_position) VALUES (?,?,?)";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            pstmt.setString(2, currentPosition);
            pstmt.setString(3, targetPosition);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("DB에 move_log를 추가할 수 없습니다.");
        }
    }

    public Map<Position, Position> selectByBoardName(String boardName) {
        String sql = "SELECT * FROM move_log WHERE board_name = ? ORDER BY id";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            try (ResultSet rs = pstmt.executeQuery()) {
                Map<Position, Position> moveLog = new LinkedHashMap<>();
                while (rs.next()) {
                    String current = rs.getString("current_position");
                    String target = rs.getString("target_position");
                    Position currentPosition = StringPositionConverter.convertToPosition(current);
                    Position targetPosition = StringPositionConverter.convertToPosition(target);
                    moveLog.put(currentPosition, targetPosition);
                }
                return moveLog;
            }
        } catch (SQLException e) {
            System.out.println();
            System.out.println();
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            System.out.println();
            System.out.println();
            throw new IllegalArgumentException("move_log 탐색 실패");
        }
    }

    public void deleteByBoardName(String boardName) {
        String sql = "DELETE FROM move_log WHERE board_name = ?";
        try (PreparedStatement pstmt = dbConnection.connection().prepareStatement(sql)) {
            pstmt.setString(1, boardName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("move_log delete 실패");
        }
    }
}
