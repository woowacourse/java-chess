package chess.controller.dao;

import chess.controller.dto.ResponseDto;
import chess.controller.dto.Tile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        Connection connection = getConnection();
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
        pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
        pstmt.executeUpdate();
        pstmt.close();

        createChessBoard(connection, responseDto);
    }

    public void createChessBoard(Connection connection, ResponseDto responseDto) throws SQLException {
        String query = "INSERT INTO chessboard VALUES ((SELECT MAX(game_id) FROM game),?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        for (Tile tile : responseDto.getChessBoardDto().getTiles()) {
            pstmt.setString(1, tile.getPosition());
            pstmt.setString(2, tile.getPiece());
            pstmt.executeUpdate();
        }
    }

    public void pieceMove(ResponseDto responseDto) throws SQLException {
        Connection connection = getConnection();
        updateGameWithMove(responseDto, connection);
        deleteChessBoardWithMove(connection);
        createChessBoard(connection, responseDto);
    }

    private void deleteChessBoardWithMove(Connection connection) throws SQLException {
        String query = "DELETE FROM chessboard WHERE room = (SELECT MAX(game_id) FROM game )";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }

    private void updateGameWithMove(ResponseDto responseDto, Connection connection) throws SQLException {
        String query = "UPDATE game SET turn = ?, white_score = ?, black_score = ? WHERE game_id = ( select MAX(game_id) from (select game_id from game) as t)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
        pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
        pstmt.executeUpdate();
    }
}
