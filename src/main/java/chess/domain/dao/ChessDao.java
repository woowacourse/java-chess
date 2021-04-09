package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import chess.domain.dto.MoveResultDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PositionDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306";
        String database = "chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "1004";

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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public int addChessRoom(Map<PositionDto, PieceDto> chessBoard, String turn, double blackScore, double whiteScore) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String query1 = "INSERT INTO chess_room VALUES (1, ?, ?, ?)";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setDouble(1, blackScore);
            pstmt1.setDouble(2, whiteScore);
            pstmt1.setString(3, turn);
            pstmt1.executeUpdate();

            String query2 = "INSERT INTO chess_piece VALUES (?, ?, ?, 1)";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            for (PositionDto positionDto : chessBoard.keySet()) {
                pstmt2.setString(1, positionDto.getPosition());
                pstmt2.setString(2, chessBoard.get(positionDto).getName());
                pstmt2.setString(3, chessBoard.get(positionDto).getColor());
                pstmt2.addBatch();
                pstmt2.clearParameters();
            }
            pstmt2.executeBatch();
            con.commit();
            return 1;
        }catch (Exception e) {
            con.rollback();
            return 0;
        }finally {
            closeConnection(con);
        }
    }

    public ChessRoomDto findChessRoomByRoomNo(int roomNo) throws SQLException {
        String query = "SELECT * FROM chess_room r JOIN chess_piece b ON r.room_no = b.room_no WHERE r.room_no = ?;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomNo);
        ResultSet rs = pstmt.executeQuery();
        validateRoomEmpty(rs);
        rs.previous();
        double blackScore = 0;
        double whiteScore = 0;
        String turn = "";
        Map<PositionDto, PieceDto> chessBoard = new HashMap<>();
        while (rs.next()) {
            PositionDto positionDto = new PositionDto(rs.getString("coordinate"));
            PieceDto pieceDto = new PieceDto(rs.getString("piece_name"), rs.getString("piece_color"));
            chessBoard.put(positionDto, pieceDto);
            blackScore = rs.getDouble("black_score");
            whiteScore = rs.getDouble("white_score");
            turn = rs.getString("turn");
        }
        return new ChessRoomDto(roomNo, chessBoard, turn, blackScore, whiteScore);
    }



    private void validateRoomEmpty(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            throw new IllegalArgumentException("[ERROR] 불러올 방이 없습니다.");
        }
    }

    public int updateChessRoom(MoveResultDto moveResultDto, PieceDto targetPiece, PositionDto source, PositionDto target) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String query1 = "UPDATE chess_room SET black_score = ?, white_score = ?, turn = ? where room_no = 1";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setDouble(1, moveResultDto.getBlackScore());
            pstmt1.setDouble(2, moveResultDto.getWhiteScore());
            pstmt1.setString(3, moveResultDto.getTurn());
            pstmt1.executeUpdate();
            updateChessPiece(targetPiece, source, target, con);
            con.commit();
            return 1;
        }catch (Exception e) {
            con.rollback();
            return 0;
        }finally {
            closeConnection(con);
        }
    }

    private void updateChessPiece(PieceDto pieceDto, PositionDto source, PositionDto target, Connection con) throws SQLException {
        deleteChessPiece(target.getPosition(), con);
        String query = "UPDATE chess_piece SET coordinate = ?, piece_name = ?, piece_color = ? where room_no = 1 and coordinate = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, target.getPosition());
        pstmt.setString(2, pieceDto.getName());
        pstmt.setString(3, pieceDto.getColor());
        pstmt.setString(4, source.getPosition());
        pstmt.executeUpdate();
    }

    private void deleteChessPiece(String position, Connection con) throws SQLException {
        String query = "DELETE FROM chess_piece where room_no = 1 && coordinate = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, position);
        pstmt.executeUpdate();
    }

    public int deleteChessRoomByRoomNo(int roomNo) throws SQLException {
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String query1 = "DELETE FROM chess_piece where room_no = ?";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setInt(1, roomNo);
            pstmt1.executeUpdate();

            String query2 = "DELETE FROM chess_room where room_no = ?";
            PreparedStatement pstmt2 = con.prepareStatement(query2);
            pstmt2.setInt(1, roomNo);
            pstmt2.executeUpdate();
            con.commit();
            return 1;
        }catch (Exception e) {
            con.rollback();
            return 0;
        }finally {
            closeConnection(con);
        }
    }
}
