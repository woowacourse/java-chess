package chess.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameDTO;
import com.google.gson.Gson;

import java.sql.*;

public class GameDAOImpl implements GameDAO {
    private Gson gson = new Gson();

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    @Override
    public void create(final ChessGame chessGame) {
        String query = "INSERT INTO game(data) VALUES (?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, gson.toJson(ChessGameDTO.from(chessGame)));
            pstmt.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new IllegalArgumentException("게임 생성을 실패 하였습니다.");
        }
    }

    @Override
    public ChessGame readFromId(final String id) {
        String query = "SELECT * FROM game WHERE game_id = ?";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String chessData = rs.getString("data");
            ChessGameDTO chessGameDTO = gson.fromJson(chessData, ChessGameDTO.class);
            return chessGameDTO.toChessGame();
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new IllegalArgumentException("게임 정보를 읽어오지 못했습니다.");
        }
    }

    @Override
    public void update(final String id, final ChessGame chessGame) {
        String query = "UPDATE game SET data = ? WHERE game_id = ?";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            ChessGameDTO chessGameDTO = ChessGameDTO.from(chessGame);
            pstmt.setString(1, gson.toJson(chessGameDTO));
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalArgumentException("게임 정보를 갱신 하지 못했습니다.");
        }
    }

    @Override
    public int getRecentGameId() {
        String query = "SELECT * FROM game ORDER BY game_id DESC LIMIT 1";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery(query);
            rs.next();
            return rs.getInt("game_id");
        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new IllegalArgumentException("게임 아이디를 가져오지 못했습니다.");
        }
    }
}
