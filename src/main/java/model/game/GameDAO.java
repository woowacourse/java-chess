package model.game;

import model.Position;
import service.LogVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameDAO {
    private static final Queue<LogVO> buffer = new LinkedList<>();

    public static List<LogVO> retrieveLog() throws SQLException {
        createLogTable();
        try (Connection con = DAO.connect();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT turn, position_from, position_to FROM chess_log"
             );
             ResultSet result = pstmt.executeQuery()) {
            List<LogVO> log = new ArrayList<>();
            while (result.next()) {
                log.add(new LogVO(result.getInt(1), result.getString(2), result.getString(3)));
            }
            return log;
        }
    }

    private static void createLogTable() throws SQLException {
        try (Connection con = DAO.connect();
             PreparedStatement pstmt = con.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS chess_log("
                             + "turn INT UNSIGNED NOT NULL PRIMARY KEY,"
                             + "position_from VARCHAR(12) NOT NULL,"
                             + "position_to VARCHAR(12) NOT NULL" +
                             ");"
             )
        ) {
            pstmt.executeUpdate();
        }
    }

    static boolean holdAndWriteLog(final Turn turn, final Position from, final Position to) {
        buffer.add(new LogVO(turn, from, to));
        try {
            while (!buffer.isEmpty()) {
                writeLog(buffer.poll());
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void writeLog(final LogVO log) throws SQLException {
        try (Connection con = DAO.connect();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO chess_log VALUES (?, ?, ?)")) {
            pstmt.setInt(1, log.turn().count());
            pstmt.setString(2, log.from().toString());
            pstmt.setString(3, log.to().toString());
            pstmt.executeUpdate();
        }
    }

    public static void eraseLog() throws SQLException {
        try (Connection con = DAO.connect();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM chess_log")) {
            pstmt.executeUpdate();
        }
    }
}