package dao;

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

    public void updateGame(ResultDto resultDto, String source, String target, int roomNumber) {
        updateGameInfo(resultDto, roomNumber);
        deleteTargetPiece(target, roomNumber);
        updateTargetPiece(source, target, roomNumber);
    }

    private void updateTargetPiece(String source, String target, int roomNumber) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("update piece set position=? where position=? AND gameid=?")) {
            pstmt.setString(1, target);
            pstmt.setString(2, source);
            pstmt.setInt(3, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTargetPiece(String target, int roomNumber) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("delete from piece where position=? AND gameid=?");) {
            pstmt.setString(1, target);
            pstmt.setInt(2, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateGameInfo(ResultDto resultDto, int roomNumber) {
        PiecesDto piecesDto = resultDto.getPiecesDto();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE game SET blackscore = ?, whitescore=?, turn=? WHERE gameid=?")) {
            pstmt.setDouble(1, piecesDto.getBlackScore());
            pstmt.setDouble(2, piecesDto.getWhiteScore());
            pstmt.setBoolean(3, piecesDto.isTurn());
            pstmt.setInt(4, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewGameInfo(ResultDto resultDto) {
        PiecesDto piecesDto = resultDto.getPiecesDto();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("insert into game(blackscore, whitescore, turn) values(?, ?, ?)")) {
            pstmt.setDouble(1, piecesDto.getBlackScore());
            pstmt.setDouble(2, piecesDto.getBlackScore());
            pstmt.setBoolean(3, piecesDto.isTurn());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<PieceDto> pieces = piecesDto.getPieces();
        int newGameID = lastGameID();
        for (PieceDto piece : pieces) {
            insertPieceInfo(newGameID, piece);
        }
    }

    private void insertPieceInfo(int newGameID, PieceDto piece) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("insert into piece(gameid, name, position) values(" + newGameID + ", ?, ?)")) {
            pstmt.setString(1, piece.getPieceName());
            pstmt.setString(2, piece.getPosition());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultDto selectGameInfo(int roomNumber) {
        double blackScore = 0, whiteScore = 0;
        boolean turn = false;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select blackscore, whitescore, turn from game where gameid=?")) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) throw new SQLException();
            blackScore = rs.getInt(1);
            whiteScore = rs.getInt(2);
            turn = rs.getBoolean(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Map<Position, Piece> pieces = new HashMap<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select name, position from piece where gameid=?")) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            putResultSetToPieces(pieces, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PiecesDto piecesDto = new PiecesDto(pieces, new StatusDto(blackScore, whiteScore), false, turn);
        return new ResultDto(piecesDto, "");
    }

    private void putResultSetToPieces(Map<Position, Piece> pieces, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Piece piece = PieceFactory.findPiece(rs.getString(1));
            Position position = Position.of(rs.getString(2));
            pieces.put(position, piece);
        }
    }

    public void deleteGame(int roomNumber) {
        deleteInfo("piece", roomNumber);
        deleteInfo("game", roomNumber);
    }

    private void deleteInfo(String table, int roomNumber) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("delete from " + table + " where gameid=?")) {
            pstmt.setInt(1, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> findGames() {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select gameid from game")) {
            ResultSet rs = pstmt.executeQuery();
            List<String> gameIDs = new ArrayList<>();
            while (rs.next()) {
                gameIDs.add(rs.getString(1));
            }
            return gameIDs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int lastGameID() {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name ='game' AND table_schema = DATABASE()")) {
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) throw new SQLException();
            System.out.println("*** : " + rs.getInt(1));
            return rs.getInt(1) - 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
