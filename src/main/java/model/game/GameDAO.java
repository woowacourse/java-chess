package model.game;

import model.board.Position;
import model.game.exception.FailedToCreateLogTableException;
import model.game.exception.FailedToEraseLogException;
import model.game.exception.FailedToRetrieveLogException;
import model.game.exception.FailedToWriteLogException;
import service.LogVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameDAO {
    private static final String server = "localhost";
    private static final String database = "woowa";
    private static final String userName = "donut";
    private static final String password = "qwer1234";

    private static final Queue<LogVO> buffer = new LinkedList<>();

    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false",
                    userName,
                    password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static List<LogVO> retrieveLog() {
        createLogTable();
        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT turn, position_src, position_dest FROM chess_log"
             );
             ResultSet result = pstmt.executeQuery()) {
            List<LogVO> log = new ArrayList<>();
            while (result.next()) {
                log.add(new LogVO(result.getInt(1), result.getString(2), result.getString(3)));
            }
            return log;
        } catch (SQLException e) {
            throw new FailedToRetrieveLogException(e);
        }
    }

    private static void createLogTable() {
        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS chess_log("
                             + "turn INT UNSIGNED NOT NULL PRIMARY KEY,"
                             + "position_src VARCHAR(2) NOT NULL,"
                             + "position_dest VARCHAR(2) NOT NULL" +
                             ");"
             )
        ) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new FailedToCreateLogTableException(e);
        }
    }

    public static boolean holdAndWriteLog(final Turn turn, final Position src, final Position dest) {
        buffer.add(new LogVO(turn, src, dest));
        try {
            while (!buffer.isEmpty()) {
                writeLog(buffer.poll());
            }
            return true;
        } catch (FailedToWriteLogException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void writeLog(final LogVO log) {
        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO chess_log VALUES (?, ?, ?)")) {
            pstmt.setInt(1, log.turn().count());
            pstmt.setString(2, log.src().toString());
            pstmt.setString(3, log.dest().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new FailedToWriteLogException(e);
        }
    }

    public static void eraseLog() {
        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM chess_log")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new FailedToEraseLogException(e);
        }
    }
}