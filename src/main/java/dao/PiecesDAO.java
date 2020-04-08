package dao;

import domain.pieces.Pieces;
import domain.point.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PiecesDAO {

    private static PiecesDAO piecesDAO = new PiecesDAO();

    private PiecesDAO() {}

    public static PiecesDAO getInstance() {
        return piecesDAO;
    }

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:3306"; // MySQL 서버 주소
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

    public void addPieces(Pieces pieces) throws SQLException {
        for (Point point : pieces.getPieces().keySet()) {
            addPiece(pieces, point);
        }
    }

    private void addPiece(Pieces pieces, Point point) throws SQLException {
        String query = "INSERT INTO pieces VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, pieces.getPiece(point).getInitial());
        pstmt.setString(2, point.toString());
        pstmt.executeUpdate();
    }

    public Map<String, Object> readPieces() throws SQLException {
        String query = "SELECT * from pieces";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Map<String, Object> pieces = new HashMap<>();
        while (rs.next()) {
            pieces.put(rs.getObject(2).toString(), rs.getObject(1));
        }
        pstmt.close();
        return pieces;
    }

    public void updatePieces(Pieces pieces) throws SQLException {
        deleteAll();
        addPieces(pieces);
    }

    public void deleteAll() throws SQLException {
        String query = "delete from pieces";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public boolean isSave() throws SQLException {
        String query = "SELECT * from pieces";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        return rs.isBeforeFirst();
    }
}
