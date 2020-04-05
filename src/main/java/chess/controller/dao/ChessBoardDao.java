package chess.controller.dao;

import chess.controller.dto.ResponseDto;
import chess.controller.dto.Tile;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class ChessBoardDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
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

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    // 초기 게임 만드는 메서드
    public void createGame(ResponseDto responseDto) throws SQLException {
        String query = "INSERT INTO game(turn, white_score, black_score) VALUES (?,?,?)";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
        pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
        pstmt.executeUpdate();
        pstmt.close();

        createChessBoard(responseDto);
    }

    // 초기 체스보드 저장 메서드
    public void createChessBoard(ResponseDto responseDto) throws SQLException {
        String query = "INSERT INTO chessboard VALUES ((SELECT MAX(game_id) FROM game ),?,?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        for (Tile tile : responseDto.getChessBoardDto().getTiles()) {
            pstmt.setString(1, tile.getPosition());
            pstmt.setString(2, tile.getPiece());
            pstmt.executeUpdate();
        }
    }

    public void moveChessBoard(Map<Position, Piece> chessBoard) throws SQLException {
        String query = "INSERT INTO chessboard VALUES (?,?)";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        for (Map.Entry<Position, Piece> chessBoardEntry : chessBoard.entrySet()) {
            pstmt.setString(1, "" + chessBoardEntry.getKey().getRow() + chessBoardEntry.getKey().getColumn());
            pstmt.setString(2, chessBoardEntry.getValue().getDisplay());
            pstmt.executeUpdate();
        }
    }
}
