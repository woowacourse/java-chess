package chess.dao;

import chess.dto.PieceDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PieceDAO {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static PieceDAO pieceDAO;

    private PieceDAO() {
    }

    public static PieceDAO getInstance() {
        if (pieceDAO == null) {
            pieceDAO = new PieceDAO();
        }
        return pieceDAO;
    }

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "java_chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "moonui";
        String password = "8123";

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("con 오류 : " + e.getMessage());
        }
    }

    public void addPiece(PieceDTO pieceDTO) throws SQLException {
        String query = "INSERT INTO piece VALUES (?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceDTO.getPosition());
        pstmt.setString(2, pieceDTO.getTeam());
        pstmt.setString(3, pieceDTO.getPieceType());
        pstmt.executeUpdate();
        closeConnection(connection);
    }

    public void updatePiece(PieceDTO pieceDTO) throws SQLException {
        String query = "UPDATE SET team=? pieceType=? WHERE position=?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceDTO.getTeam());
        pstmt.setString(2, pieceDTO.getPieceType());
        pstmt.setString(3, pieceDTO.getPosition());
        pstmt.executeUpdate();
        closeConnection(connection);
    }
}
