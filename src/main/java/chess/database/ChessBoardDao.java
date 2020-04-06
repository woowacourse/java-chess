package chess.database;

import java.sql.*;

public class ChessBoardDao {
    public Connection getConnection() {
        Connection connection = null;
        String server = "localhost:13306";
        String database = "WOOWA";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //어떤 드라이버에 연결할지
       } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password); //진짜 연결
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("connection 오류: " + e.getMessage());
        }
    }

    public void addBoard(String position, String piece) throws SQLException {
        String query = "INSERT INTO board VALUES (?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, position);
        preparedStatement.setString(2, piece);
        preparedStatement.executeUpdate();
    }

    public ChessBoard findByPosition(String position) throws SQLException {
        String query = "SELECT * FROM board WHERE position = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, position);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return null;
        }

        return new ChessBoard(
                resultSet.getString("position"),
                resultSet.getString("piece"));
    }

    public void updatePiece(String position, String piece) throws SQLException {
        String query = "UPDATE board SET piece = ? where position = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, piece);
        preparedStatement.setString(2, position);
        preparedStatement.executeUpdate();
    }
}
