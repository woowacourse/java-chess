package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PositionDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessDao {
//    private Connection con;
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "1004"; // MySQL 서버 비밀번호

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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

//    public void addChessRoom(ChessStatusDto chessStatusDto) throws SQLException {
//        Connection con = null;
//        try {
//            con = getConnection();
//            con.setAutoCommit(false);
//            String query1 = "INSERT INTO chess_room (black_score, white_score, turn) VALUES (?, ?, ?)";
//            PreparedStatement pstmt1 = con.prepareStatement(query1);
//            pstmt1.setDouble(1, chessStatusDto.getBlackScore());
//            pstmt1.setDouble(2, chessStatusDto.getWhiteScore());
//            pstmt1.setString(3, chessStatusDto.getTurn().name());
//            pstmt1.executeUpdate();
//
//            String query2 = "INSERT INTO chess_board VALUES (?, ?, ?, ?, LAST_INSERT_ID())";
//            PreparedStatement pstmt2 = con.prepareStatement(query2);
//            Map<Position, Piece> chessBoard = chessStatusDto.getChessBoard();
//            for (Position position : chessBoard.keySet()) {
//                pstmt2.setString(1, String.valueOf(position.getX()));
//                pstmt2.setString(2, String.valueOf(position.getY()));
//                pstmt2.setString(3, chessBoard.get(position).getName());
//                pstmt2.setString(4, chessBoard.get(position).getColor().name());
//                pstmt2.addBatch();
//                pstmt2.clearParameters();
//            }
//            pstmt2.executeBatch();
//            con.commit();
//        }catch (Exception e) {
//            con.rollback();
//        }
//    }

    public int addChessRoom(ChessRoomDto chessRoomDto) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String query1 = "INSERT INTO chess_room (black_score, white_score, turn) VALUES (?, ?, ?)";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setDouble(1, chessRoomDto.getBlackScore());
            pstmt1.setDouble(2, chessRoomDto.getWhiteScore());
            pstmt1.setString(3, chessRoomDto.getTurn());
            pstmt1.executeUpdate();

            String query2 = "INSERT INTO chess_board VALUES (?, ?, ?, ?, LAST_INSERT_ID())";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            Map<PositionDto, PieceDto> chessBoard = chessRoomDto.getChessBoard();
            for (PositionDto positionDto : chessBoard.keySet()) {
                pstmt2.setString(1, String.valueOf(positionDto.getPosition().charAt(0)));
                pstmt2.setString(2, String.valueOf(positionDto.getPosition().charAt(1)));
                pstmt2.setString(3, chessBoard.get(positionDto).getName());
                pstmt2.setString(4, chessBoard.get(positionDto).getColor());
                pstmt2.addBatch();
                pstmt2.clearParameters();
            }
            pstmt2.executeBatch();
            con.commit();
            return 1;
        }catch (Exception e) {
            con.rollback();
            return 0;
        }
    }

    public ChessRoomDto findChessRoomById(int roomNo) throws SQLException {
        String query = "SELECT * FROM chess_room r JOIN chess_board b ON r.room_no = b.room_no WHERE r.room_no = ?;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomNo);
        ResultSet rs = pstmt.executeQuery();
        ChessRoomDto chessRoomDto = new ChessRoomDto();
        if (rs.next()) {
            chessRoomDto.setRoom_no(rs.getInt("room_no"));
            chessRoomDto.setBlackScore(rs.getDouble("black_score"));
            chessRoomDto.setWhiteScore(rs.getDouble("white_score"));
            chessRoomDto.setTurn(rs.getString("turn"));
        }
        Map<PositionDto, PieceDto> chessBoard = new HashMap<>();
        while (rs.next()) {
            PositionDto positionDto = new PositionDto(rs.getString("x_coordinate")
                    + rs.getString("y_coordinate"));
            PieceDto pieceDto = new PieceDto(rs.getString("chess_name"), rs.getString("chess_color"));
            chessBoard.put(positionDto, pieceDto);
        }
        chessRoomDto.setChessBoard(chessBoard);
        return chessRoomDto;
    }
}
