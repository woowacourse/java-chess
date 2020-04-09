package dao;

import domain.pieces.Pieces;
import domain.point.Point;
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

    public void addPieces(Pieces pieces) throws SQLException {
        for (Point point : pieces.getPieces().keySet()) {
            addPiece(pieces, point);
        }
    }

    private void addPiece(Pieces pieces, Point point) throws SQLException {
        String query = "INSERT INTO pieces VALUES (?, ?)";
        try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query)) {
            pstmt.setString(1, pieces.getPiece(point).getInitial());
            pstmt.setString(2, point.toString());
            pstmt.executeUpdate();
        }
    }

    public Map<String, Object> readPieces() throws SQLException {
        String query = "SELECT * from pieces";
         try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query)) {
             ResultSet rs = pstmt.executeQuery();
             Map<String, Object> pieces = new HashMap<>();
             while (rs.next()) {
                 pieces.put(rs.getObject(2).toString(), rs.getObject(1));
             }
             return pieces;
         }

    }

    public void updatePieces(Pieces pieces) throws SQLException {
        deleteAll();
        addPieces(pieces);
    }

    public void deleteAll() throws SQLException {
        String query = "delete from pieces";
        try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query)) {
             pstmt.executeUpdate();
        }
    }

    public boolean isSave() throws SQLException {
        String query = "SELECT * from pieces";
        try (PreparedStatement pstmt = JDBCConnection.getConnection().prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            return rs.isBeforeFirst();
        }
    }
}
