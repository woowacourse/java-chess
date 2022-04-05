package chess.web.dao;

import chess.domain.piece.vo.TeamColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamColorDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public void update(TeamColor teamColor) {
        try {
            Connection connection = getConnection();
            String sql = "update teamColor SET currentTurn = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, teamColor.name());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TeamColor findCurrentTurn() {
        TeamColor currentTurn = TeamColor.EMPTY;
        try {
            Connection connection = getConnection();
            String sql = "select currentTurn from teamColor";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            currentTurn = TeamColor.valueOf(resultSet.getString("currentTurn"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentTurn;
    }

    private Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);// 어떤 URL의 커넥션을 가져올 것 인지
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() { // 생략 가능( 드라이버를 지정하고 싶을 때 작성, 하나만 사용한다면 DriverManager가 알아서 해준다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
