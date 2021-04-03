package chess.domain.dao;

import chess.domain.piece.Piece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PieceDAO {

    private static Connection con = getConnection();

    public static Connection getConnection() {
    Connection con = null;
    String server = "localhost:13306"; // MySQL 서버 주소
    String database = "chess_game"; // MySQL DATABASE 이름
    String option = "?useSSL=false&serverTimezone=UTC";
    String userName = "root"; //  MySQL 서버 아이디
    String password = "root"; // MySQL 서버 비밀번호

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

    public static boolean save(final Piece piece) {
        try{
            String query = "INSERT INTO pieces(notation, position) VALUES(?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, piece.getNotation());
            preparedStatement.setString(2, piece.getPosition().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean saveAll(List<Piece> pieces) {
        deleteAll();
        for(Piece piece : pieces) {
            if(!save(piece)){
                return false;
            }
        }
        return true;
    }

    private static void deleteAll() {
        try{
            String query = "DELETE FROM pieces";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
