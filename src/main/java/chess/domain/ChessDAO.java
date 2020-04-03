package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceGenerator;
import chess.domain.piece.Team;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessDAO {
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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void savePiece(List<Piece> pieces) throws SQLException {
        PreparedStatement cleanup = getConnection().prepareStatement("DELETE FROM Pieces");
        cleanup.executeUpdate();
        for (Piece piece : pieces) {
            String query = "INSERT INTO Pieces (position, representation, team) VALUES (?, ?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, piece.getPosition().toString());
            pstmt.setString(2, piece.toString());
            pstmt.setString(3, piece.getTeam().toString());
            pstmt.executeUpdate();
        }
    }

    public void saveTurn(Turn turn) throws SQLException {
        PreparedStatement cleanup = getConnection().prepareStatement("DELETE FROM Turn");
        cleanup.executeUpdate();
        String query = "INSERT INTO Turn (turn) VALUES (?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, turn.getTeam().toString());
        pstmt.executeUpdate();
    }

    public Pieces loadPieces() throws SQLException {
        String query = "SELECT * FROM Pieces";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Map<Position, Piece> pieces = new HashMap<>();


        while (rs.next()) {
            pieces.put(new Position(rs.getString("position")), PieceGenerator.generate(rs.getString("representation"), rs.getString("team"), rs.getString("position")));
        }
        return new Pieces(pieces);
    }

    public Turn loadTurn() throws SQLException {
        String query = "SELECT * FROM Turn";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return null;

        return new Turn(Team.valueOf(rs.getString("turn")));
    }
}
