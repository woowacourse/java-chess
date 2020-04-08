package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplatePieceDao implements PieceDao {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static PieceDao jdbcTemplatePieceDao = new JdbcTemplatePieceDao();

    private JdbcProperties jdbcProperties;

    private JdbcTemplatePieceDao() {
        jdbcProperties = new JdbcProperties();
    }

    static public PieceDao getInstance() {
        return jdbcTemplatePieceDao;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            String url = jdbcProperties.getUrl();
            String userName = jdbcProperties.getUserName();
            String password = jdbcProperties.getPassword();

            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류 : " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
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
    public void addPiece(PieceEntity pieceEntity) throws SQLException {
        String query = "INSERT INTO piece VALUES (?, ?, ?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceEntity.getPosition());
        pstmt.setString(2, pieceEntity.getTeam());
        pstmt.setString(3, pieceEntity.getPieceType());
        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }

    @Override
    public void updatePiece(PieceEntity pieceEntity) throws SQLException {
        String query = "UPDATE SET team=? pieceType=? WHERE position=?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, pieceEntity.getTeam());
        pstmt.setString(2, pieceEntity.getPieceType());
        pstmt.setString(3, pieceEntity.getPosition());
        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }

    @Override
    public List<PieceEntity> findPiece() throws SQLException {
        String query = "SELECT * FROM piece";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        List<PieceEntity> pieceEntities = new ArrayList<>();

        while (resultSet.next()) {
            String position = resultSet.getString("position");
            String team = resultSet.getString("team");
            String pieceType = resultSet.getString("pieceType");
            pieceEntities.add(new PieceEntity(position, team, pieceType));
        }

        resultSet.close();
        pstmt.close();
        closeConnection(connection);

        return pieceEntities;
    }
}
