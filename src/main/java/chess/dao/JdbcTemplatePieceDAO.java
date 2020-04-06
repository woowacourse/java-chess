package chess.dao;

import chess.dto.PieceDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTemplatePieceDAO implements PieceDAO {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static PieceDAO jdbcTemplateDAO;

    private JdbcTemplatePieceDAO() {
    }

    static public PieceDAO getInstance() {
        if (jdbcTemplateDAO == null) {
            jdbcTemplateDAO = new JdbcTemplatePieceDAO();
        }
        return jdbcTemplateDAO;
    }

    private Connection getConnection() {
        Connection con = null;

        try {
            String file = "src\\jdbc.properties";
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String server = properties.getProperty("server");
            String database = properties.getProperty("database");
            String option = properties.getProperty("option");
            String userName = properties.getProperty("userName");
            String password = properties.getProperty("password");

            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (IOException e) {
            System.err.println("jdbc 속성 파일 오류 : " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    private void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("con 오류 : " + e.getMessage());
        }
    }

    @Override
    public void addPiece(PieceDTO pieceDTO) throws SQLException {
        String query = "INSERT INTO piece VALUES (?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceDTO.getPosition());
        pstmt.setString(2, pieceDTO.getTeam());
        pstmt.setString(3, pieceDTO.getPieceType());
        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }

    @Override
    public void updatePiece(PieceDTO pieceDTO) throws SQLException {
        String query = "UPDATE SET team=? pieceType=? WHERE position=?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceDTO.getTeam());
        pstmt.setString(2, pieceDTO.getPieceType());
        pstmt.setString(3, pieceDTO.getPosition());
        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }
}
