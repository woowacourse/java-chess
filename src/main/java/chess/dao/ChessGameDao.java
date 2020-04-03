package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.game.state.Finished;
import chess.domain.game.state.Playing;
import chess.domain.game.state.Ready;
import chess.domain.game.state.State;
import chess.dto.BoardDto;
import chess.dto.TurnDto;

public class ChessGameDao {
    private static Connection con = null;

    public static Connection getConnection() {
        if (con == null) {
            String server = "localhost:3306"; // MySQL 서버 주소
            String database = "chess_game"; // MySQL DATABASE 이름
            String option = "?useSSL=false&serverTimezone=UTC";
            String userName = "root"; //  MySQL 서버 아이디
            String password = "thsl1020"; // MySQL 서버 비밀번호

            // 드라이버 로딩
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
                e.printStackTrace();
            }

            // 드라이버 연결
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
                System.out.println("정상적으로 연결되었습니다.");
            } catch (SQLException e) {
                System.err.println("연결 오류:" + e.getMessage());
                e.printStackTrace();
            }
            return con;
        }
        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public ChessGame create() throws SQLException {
        String query = "INSERT INTO chess_game(state) VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query, new String[] {"id"});
        pstmt.setString(1, "READY");
        pstmt.executeUpdate();
        ResultSet resultSet = pstmt.getGeneratedKeys();
        if (!resultSet.next()) {
            return null;
        }
        int id = resultSet.getInt(1);
        return new ChessGame(id, new Ready());
    }

    public List<Integer> selectAll() throws SQLException {
        String query = "SELECT id FROM chess_game";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getInt("id"));
        }
        return ids;
    }

    public ChessGame findById(int id) throws SQLException {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        State state = new Ready();
        if (rs.getString("state").equals("PLAYING")) {
            state = new Playing(Board.from(rs.getString("board")), Turn.from(rs.getString("turn")));
        }
        if (rs.getString("state").equals("FINISHED")) {
            state = new Finished(Board.from(rs.getString("board")), Turn.from(rs.getString("turn")));
        }
        return new ChessGame(id, state);
    }

    public void update(ChessGame chessGame) throws SQLException {
        String query = "UPDATE chess_game SET state=?,board=?,turn=? WHERE id=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        BoardDto boardDto = new BoardDto(chessGame.board());
        TurnDto turnDto = new TurnDto(chessGame.turn());
        String state = chessGame.getState().toString();
        pstmt.setString(1, state);
        pstmt.setString(2, String.join("", boardDto.getBoard()));
        pstmt.setString(3, turnDto.getTurn().getColor().toString());
        pstmt.setInt(4, chessGame.getId());
        pstmt.executeUpdate();
    }
}
