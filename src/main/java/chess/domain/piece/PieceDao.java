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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void addPiece(Piece piece) throws SQLException {
        String query = "INSERT INTO chess(name,xposition,yposition,team) VALUES (?, ?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, piece.getPieceName());
        pstmt.setString(2, piece.getXPosition());
        pstmt.setString(3, piece.getYPosition());
        pstmt.setString(4, piece.getTeamStrategy().toString());
        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    public void deletePiece(Position targetPosition) throws SQLException {
        String query = "delete from chess where xposition=? and yposition=?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, targetPosition.getXPosition());
        pstmt.setString(2, targetPosition.getYPosition());

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();

    }

    public void updatePiece(Position sourcePosition, Position targetPosition) throws SQLException {
        String query = "UPDATE chess SET xposition = ?, yposition = ? WHERE xposition=? AND yposition=?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, targetPosition.getXPosition());
        pstmt.setString(2, targetPosition.getYPosition());
        pstmt.setString(3, sourcePosition.getXPosition());
        pstmt.setString(4, sourcePosition.getYPosition());

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();
    }

    public void deleteAll() throws SQLException {
        String query = "delete from chess";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.executeUpdate();

        pstmt.close();
        connection.close();

    }

    public List<Map<String, Object>> readPieces() throws SQLException {
        String query = "SELECT * from chess";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
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

        rs.close();
        pstmt.close();
        connection.close();

        return pieces;
    }
}
