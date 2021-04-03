package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import chess.dto.RoomNamesDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "chess";
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

    public List<RoomNamesDto> findRoomNames() throws SQLException {
        String query = "SELECT * FROM room";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        List<RoomNamesDto> roomNameDtos = new ArrayList<>();

        while (rs.next()) {
            roomNameDtos.add(new RoomNamesDto(rs.getString("name")));
        }

        closeConnection(con);
        return roomNameDtos;
    }

    public String findRoomTurnColor(String roomName) throws SQLException {
        String query = "SELECT turn FROM room WHERE name = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, roomName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        String turnColor = rs.getString("turn");
        closeConnection(con);
        return turnColor;
    }

    public Piece findInitialBoardPieceAtPosition(String positionName) throws SQLException {
        String query = "SELECT piece.piece_kind, piece.piece_color FROM ((initial_board " +
            "INNER JOIN piece ON initial_board.piece_id = piece.pid) " +
            "INNER JOIN position ON initial_board.position_id = position.pid)" +
            "WHERE position.address = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, positionName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        PieceKind pieceKind = PieceKind.pieceKindByName(rs.getString("piece_kind"));
        PieceColor pieceColor = PieceColor.pieceColorByName(rs.getString("piece_color"));

        closeConnection(con);
        return new Piece(pieceKind, pieceColor);
    }

    public void addRoom(String name, PieceColor turnColor) throws SQLException {
        String query = "INSERT INTO room VALUES (?, ?)";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, turnColor.getName());
        pstmt.executeUpdate();

        closeConnection(con);
    }

    public void savePlayingBoard(String name, Map<Position, Piece> playingBoard, PieceColor turnColor) throws SQLException {
        Connection con = getConnection();
        if (existingBoard(con, name) == 1) {
            deleteExistingBoard(name);
        }

        updateTurnColor(name, turnColor);
        addPlayingBoard(con, name, playingBoard);
        closeConnection(con);
    }

    private void updateTurnColor(String roomName, PieceColor turnColor) throws SQLException {
        String query = "UPDATE room SET turn = ? WHERE name = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, turnColor.getName());
        pstmt.setString(2, roomName);
        pstmt.executeUpdate();

        closeConnection(con);
    }

    private int existingBoard(Connection con, String name) throws SQLException {
        String query = "SELECT EXISTS (" +
            "SELECT room_name FROM backup_board WHERE room_name = ?) " +
            "as success";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return rs.getInt("success");
    }

    public void deleteExistingBoard(String name) throws SQLException {
        String query = "DELETE FROM backup_board WHERE room_name = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.executeUpdate();

        closeConnection(con);
    }

    private void addPlayingBoard(Connection con, String name, Map<Position, Piece> playingBoard) {
        playingBoard.keySet()
            .stream()
            .forEach(position -> {
                try {
                    addSquare(con, name, position, playingBoard);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
    }

    private void addSquare(Connection con, String name, Position position, Map<Position, Piece> playingBoard) throws
        SQLException {
        int positionId = findPositionId(con, position);
        int pieceId = findPieceId(con, playingBoard.get(position));

        String query = "INSERT INTO backup_board VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setInt(2, positionId);
        pstmt.setInt(3, pieceId);
        pstmt.executeUpdate();
    }

    private int findPieceId(Connection con, Piece piece) throws SQLException {
        String query = "SELECT pid FROM piece WHERE piece_kind = ? AND piece_color = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, piece.symbol().toUpperCase());
        pstmt.setString(2, piece.color().getName());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return rs.getInt("pid");
    }

    private int findPositionId(Connection con, Position position) throws SQLException {
        String query = "SELECT pid FROM position WHERE address = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, position.toString());
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return 0;

        return rs.getInt("pid");
    }

    public Piece findPlayingBoardByRoom(String name, String positionName) throws SQLException {
        String query = "SELECT piece.piece_kind, piece.piece_color FROM ((backup_board " +
            "INNER JOIN piece ON backup_board.piece_id = piece.pid) " +
            "INNER JOIN position ON backup_board.position_id = position.pid)" +
            "WHERE backup_board.room_name = ? AND position.address = ?";

        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, positionName);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        PieceKind pieceKind = PieceKind.pieceKindByName(rs.getString("piece_kind"));
        PieceColor pieceColor = PieceColor.pieceColorByName(rs.getString("piece_color"));

        closeConnection(con);
        return new Piece(pieceKind, pieceColor);
    }

    public void deleteRoom(String roomName) throws SQLException {
        String query = "DELETE FROM room WHERE name = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, roomName);
        pstmt.executeUpdate();

        closeConnection(con);
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
