package web.dao;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;

import java.sql.*;

public class ChessGameDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "chess_db";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void addCommand(String command, int roomId) throws SQLException {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO chessGame(command, room_id) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, command);
            pstmt.setInt(2, roomId);
            pstmt.executeUpdate();
        }
    }

    public ChessGame findByRoomId(int roomId) throws SQLException {
        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        chessGame.start();
        Commands commands = Commands.initCommands(chessGame);

        try (Connection con = getConnection()) {
            String query = "SELECT command FROM chessGame WHERE room_id = ? ORDER BY command_id";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, roomId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String move = rs.getString("command");
                Command command = commands.matchedCommand(move);
                command.execution(move);
            }
        }

        return chessGame;
    }
}
