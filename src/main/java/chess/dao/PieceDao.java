package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;

import java.sql.*;
import java.util.*;

public class PieceDao {
    private static final int SERVER_INDEX = 0;
    private static final int DATABASE_INDEX = 1;
    private static final int OPTION_INDEX = 2;
    private static final int USERNAME_INDEX = 3;
    private static final int PASSWORD_INDEX = 4;

    public List<String> Properties() {
        String server = "localhost:13306";
        String database = "db_name";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";
        return Arrays.asList(server, database, option, userName, password);
    }

    public Connection getConnection() {
        Optional<Connection> con = null;
        List<String> properties = Properties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = Optional
                    .of(DriverManager
                            .getConnection("jdbc:mysql://"
                                            + properties.get(SERVER_INDEX)
                                            + "/" + properties.get(DATABASE_INDEX)
                                            + properties.get(OPTION_INDEX)
                                    , properties.get(USERNAME_INDEX), properties.get(PASSWORD_INDEX)));
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con.orElseThrow(NullPointerException::new);
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
