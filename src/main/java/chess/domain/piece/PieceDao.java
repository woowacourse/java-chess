package chess.domain.piece;

import chess.domain.piece.position.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceDao {
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

    public void addPiece(Piece piece) throws SQLException {
        String query = "INSERT INTO chess(name,file,rank,team) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        pstmt.setString(1, piece.getPieceName());
        pstmt.setString(2, piece.getFile());
        pstmt.setString(3, piece.getRank());
        pstmt.setString(4, piece.getTeamStrategy().toString());

        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deletePiece(Position targetPosition) throws SQLException {
        String query = "delete from chess where file=? and rank=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        pstmt.setString(1, targetPosition.getXPosition());
        pstmt.setString(2, targetPosition.getYPosition());

        pstmt.executeUpdate();
        pstmt.close();

    }

    public void updatePiece(Position sourcePosition, Position targetPosition) throws SQLException {
        String query = "UPDATE chess SET file = ?, rank = ? WHERE file=? AND rank=?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);

        pstmt.setString(1, targetPosition.getXPosition());
        pstmt.setString(2, targetPosition.getYPosition());
        pstmt.setString(3, sourcePosition.getXPosition());
        pstmt.setString(4, sourcePosition.getYPosition());

        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteAll() throws SQLException {
        String query = "delete from chess";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }

    public List<Map<String, Object>> readPieces() throws SQLException {
        String query = "SELECT * from chess";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();

        List<Map<String, Object>> pieces = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i < metaData.getColumnCount(); ++i) {
                map.put(metaData.getColumnName(i), rs.getObject(i));
            }
            pieces.add(map);
        }

        pstmt.close();
        return pieces;
    }
}
