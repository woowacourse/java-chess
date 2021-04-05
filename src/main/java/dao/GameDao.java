package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.piece.objects.Piece;
import domain.piece.objects.PieceFactory;
import domain.piece.position.Position;
import dto.PieceDto;
import dto.PiecesDto;
import dto.ResultDto;
import dto.StatusDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "db_name";
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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void updateGame(ResultDto resultDto, String source, String target, int roomNumber) throws SQLException {
        PiecesDto piecesDto = resultDto.getPiecesDto();
        String query = "UPDATE game SET blackscore = ?, whitescore=?, turn=? WHERE gameid=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setDouble(1, piecesDto.getBlackScore());
        pstmt.setDouble(2, piecesDto.getWhiteScore());
        pstmt.setBoolean(3, piecesDto.isTurn());
        pstmt.setInt(4, roomNumber);
        pstmt.executeUpdate();

        query = "delete from piece where position=? AND gameid=?"; // 이동할 위치에 있는 기물이 있는 경우 지운다.
        pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, target);
        pstmt.setInt(2, roomNumber);
        pstmt.executeUpdate();

        query = "update piece set position=? where position=? AND gameid=?";
        pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, target);
        pstmt.setString(2, source);
        pstmt.setInt(3, roomNumber);
        pstmt.executeUpdate();
    }

    public void insertNewGameInfo(ResultDto resultDto) throws SQLException, JsonProcessingException {
        PiecesDto piecesDto = resultDto.getPiecesDto();
        String game2Query = "insert into game(blackscore, whitescore, turn) values(?, ?, ?)";
        PreparedStatement pstmt2 = getConnection().prepareStatement(game2Query);
        pstmt2.setDouble(1, piecesDto.getBlackScore());
        pstmt2.setDouble(2, piecesDto.getBlackScore());
        pstmt2.setBoolean(3, piecesDto.isTurn());
        pstmt2.executeUpdate();

        List<PieceDto> pieces = piecesDto.getPieces();
        int newGameID = lastGameID();
        for (PieceDto piece : pieces) {
            String piece2Query = "insert into piece(gameid, name, position) values(" + newGameID + ", ?, ?)";
            PreparedStatement pstmt3 = getConnection().prepareStatement(piece2Query);
            pstmt3.setString(1, piece.getPieceName());
            pstmt3.setString(2, piece.getPosition());
            pstmt3.executeUpdate();
        }
    }

    public ResultDto selectGameInfo(int roomNumber) throws SQLException, JsonProcessingException {
        String query = "select blackscore, whitescore, turn from game where gameid=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomNumber);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) throw new SQLException();
        double blackScore = rs.getInt(1);
        double whiteScore = rs.getInt(2);
        boolean turn = rs.getBoolean(3);

        query = "select name, position from piece where gameid=?";
        pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomNumber);
        rs = pstmt.executeQuery();

        Map<Position, Piece> pieces = new HashMap<>();
        while (rs.next()) {
            Piece piece = PieceFactory.findPiece(rs.getString(1));
            Position position = Position.of(rs.getString(2));
            pieces.put(position, piece);
        }
        PiecesDto piecesDto = new PiecesDto(pieces, new StatusDto(blackScore, whiteScore), false, turn);
        return new ResultDto(piecesDto, "");
    }

    public void deleteGame(int roomNumber) throws SQLException {
        String query = "delete from piece where gameid=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomNumber);
        pstmt.executeUpdate();

        query = "delete from game where gameid=?";
        pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomNumber);
        pstmt.executeUpdate();
    }

    public List<String> findGames() throws SQLException {
        String query = "select gameid from game";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<String> gameIDs = new ArrayList<>();
        while (rs.next()) {
            gameIDs.add(rs.getString(1));
        }
        return gameIDs;
    }

    public int lastGameID() throws SQLException {
        String query = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name ='game' AND table_schema = DATABASE()";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) throw new SQLException();
        return rs.getInt(1) - 1;
    }
}
