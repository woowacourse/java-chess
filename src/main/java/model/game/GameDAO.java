package model.game;

import model.board.Position;
import service.LogVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GameDAO {
    private static final Queue<LogVO> temp = new LinkedList<>();

    public static boolean holdAndWriteLog(Turn turn, Position from, Position to) {
        temp.add(new LogVO(turn, from, to));
        try {
            while (!temp.isEmpty()) {
                writeLog(temp.poll());
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void writeLog(LogVO log) throws SQLException {
        try (Connection con = DAO.connect();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO chess_log VALUES (?, ?, ?)")) {
            pstmt.setInt(1, log.turn().count());
            pstmt.setString(2, log.from().toString());
            pstmt.setString(3, log.to().toString());
            pstmt.executeUpdate();
        }
    }

    public static List<LogVO> retrieveLog() throws SQLException {
        try (Connection con = DAO.connect();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM chess_log");
             ResultSet result = pstmt.executeQuery()) {
            List<LogVO> log = new ArrayList<>();
            while (result.next()) {
                log.add(new LogVO(result.getInt(1), result.getString(2), result.getString(3)));
            }
            return log;
        }
    }

    public static void eraseLog() throws SQLException {
        try (Connection con = DAO.connect();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM chess_log")) {
            pstmt.executeUpdate();
        }
    }
}