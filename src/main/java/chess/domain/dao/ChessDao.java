package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import chess.domain.dto.MoveResultDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PositionDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessDao {
    private static final String DB_ERROR = "[ERROR] DB 오류가 발생했습니다.";

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
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    public void addChessRoom(Map<PositionDto, PieceDto> chessBoard, String turn, double blackScore, double whiteScore) throws SQLException {
        String addRoomQuery = "INSERT INTO chess_room VALUES (1, ?, ?, ?)";
        String addPieceQuery = "INSERT INTO chess_piece VALUES (?, ?, ?, 1)";
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement pstmt1 = connection.prepareStatement(addRoomQuery);
                 PreparedStatement pstmt2 = connection.prepareStatement(addPieceQuery)) {
                addChessRoom(blackScore, whiteScore, turn, pstmt1);
                addChessPiece(chessBoard, pstmt2);
            } catch (SQLException throwables) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException(DB_ERROR);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    private void addChessRoom(double blackScore, double whiteScore, String turn, PreparedStatement pstmt) throws SQLException {
        pstmt.setDouble(1, blackScore);
        pstmt.setDouble(2, whiteScore);
        pstmt.setString(3, turn);
        pstmt.executeUpdate();
    }

    private void addChessPiece(Map<PositionDto, PieceDto> chessBoard, PreparedStatement pstmt) throws SQLException {
        for (PositionDto positionDto : chessBoard.keySet()) {
            pstmt.setString(1, positionDto.getPosition());
            pstmt.setString(2, chessBoard.get(positionDto).getName());
            pstmt.setString(3, chessBoard.get(positionDto).getColor());
            pstmt.addBatch();
            pstmt.clearParameters();
        }
        pstmt.executeBatch();
    }

    public ChessRoomDto findChessRoomByRoomNo(int roomNo) throws SQLException {
        String findRoomQuery = "SELECT * FROM chess_room r JOIN chess_piece b ON r.room_no = b.room_no WHERE r.room_no = ?;";
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(findRoomQuery)) {
            pstmt.setInt(1, roomNo);
            try (ResultSet rs = pstmt.executeQuery()){
                validateRoomEmpty(rs);
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
        } catch (SQLException throwables) {
            throw new SQLException(DB_ERROR);
        }
    }


    private void validateRoomEmpty(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            throw new IllegalArgumentException("[ERROR] 불러올 방이 없습니다.");
        }
        rs.previous();
    }

    public void updateChessRoom(MoveResultDto moveResultDto, PieceDto targetPiece, PositionDto source, PositionDto target) throws SQLException {
        String updateRoomQuery = "UPDATE chess_room SET black_score = ?, white_score = ?, turn = ? where room_no = 1";
        String updatePieceQuery = "UPDATE chess_piece SET coordinate = ?, piece_name = ?, piece_color = ? where room_no = 1 and coordinate = ?";
        String deleteTargetPieceQuery = "DELETE FROM chess_piece where room_no = 1 && coordinate = ?";
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement pstmt1 = connection.prepareStatement(updateRoomQuery);
                 PreparedStatement pstmt2 = connection.prepareStatement(updatePieceQuery);
                 PreparedStatement pstmt3 = connection.prepareStatement(deleteTargetPieceQuery)) {
                updateChessRoom(moveResultDto, pstmt1);
                deleteTargetPiece(target.getPosition(), pstmt3);
                updateChessPiece(targetPiece, source, target, pstmt2);
            } catch (SQLException throwables) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException(DB_ERROR);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    private void updateChessRoom(MoveResultDto moveResultDto, PreparedStatement pstmt) throws SQLException {
        pstmt.setDouble(1, moveResultDto.getBlackScore());
        pstmt.setDouble(2, moveResultDto.getWhiteScore());
        pstmt.setString(3, moveResultDto.getTurn());
        pstmt.executeUpdate();
    }

    private void deleteTargetPiece(String position, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, position);
        pstmt.executeUpdate();
    }

    private void updateChessPiece(PieceDto pieceDto, PositionDto source, PositionDto target, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, target.getPosition());
        pstmt.setString(2, pieceDto.getName());
        pstmt.setString(3, pieceDto.getColor());
        pstmt.setString(4, source.getPosition());
        pstmt.executeUpdate();
    }

    public void deleteChessRoomByRoomNo(int roomNo) throws SQLException {
        String deletePieceQuery = "DELETE FROM chess_piece where room_no = ?";
        String deleteRoomQuery = "DELETE FROM chess_room where room_no = ?";
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement pstmt1 = connection.prepareStatement(deletePieceQuery);
                 PreparedStatement pstmt2 = connection.prepareStatement(deleteRoomQuery)) {
                pstmt1.setInt(1, roomNo);
                pstmt1.executeUpdate();

                pstmt2.setInt(1, roomNo);
                pstmt2.executeUpdate();
            } catch (SQLException throwables) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException(DB_ERROR);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
    }
}
