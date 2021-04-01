package chess.dao;

import chess.dto.ChessGameDto;
import chess.dto.SquareDto;
import chess.dto.UserIdsDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ChessDao {

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
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
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void createGame(ChessGameDto chessGameDto, UserIdsDto userIdsDto) throws SQLException {
        String query = "INSERT INTO game(`state`, white_user, black_user) VALUES (?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, chessGameDto.getState());
        pstmt.setString(2, userIdsDto.getWhiteUserId());
        pstmt.setString(3, userIdsDto.getBlackUserId());
        pstmt.executeUpdate();

        String selectQuery = "SELECT game_id FROM game WHERE white_user=? AND black_user=? ORDER BY game_id DESC";
        PreparedStatement selectPstmt = getConnection().prepareStatement(selectQuery);
        selectPstmt.setString(1, userIdsDto.getWhiteUserId());
        selectPstmt.setString(2, userIdsDto.getBlackUserId());
        ResultSet rs = selectPstmt.executeQuery();

        if(rs.next()) {
            createBoard(chessGameDto, rs.getString("game_id"));
        }
    }

    public void createBoard(ChessGameDto chessGameDto, String game_id) throws SQLException {
        String query = "INSERT INTO board("
            + "a8, b8, c8, d8, e8, f8, g8, h8,"
            + "a7, b7, c7, d7, e7, f7, g7, h7,"
            + "a6, b6, c6, d6, e6, f6, g6, h6,"
            + "a5, b5, c5, d5, e5, f5, g5, h5,"
            + "a4, b4, c4, d4, e4, f4, g4, h4,"
            + "a3, b3, c3, d3, e3, f3, g3, h3,"
            + "a2, b2, c2, d2, e2, f2, g2, h2,"
            + "a1, b1, c1, d1, e1, f1, g1, h1,"
            + "game_id) VALUES ("
            + "?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        List<String> pieces = pieces(chessGameDto);

        for (int i = 0; i < 64; i++) {
            pstmt.setString(i + 1, pieces.get(i));
        }

        pstmt.setString(65, game_id);

        pstmt.executeUpdate();
    }

    private List<String> pieces(ChessGameDto chessGameDto) {
        return chessGameDto.getSquareDtos().stream().map(SquareDto::getPiece)
            .collect(Collectors.toList());
    }
}
