package chess.domain.board;

import chess.domain.piece.Team;

import java.sql.*;

public class TurnDAO {
    private Connection connection;

    public TurnDAO() throws SQLException {
        this.connection = getConnection();
        updateTurn(Team.WHITE);
    }

    private Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
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

    private void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void updateTurn(Team targetTeam) throws SQLException {
        deleteTurn();
        String query = "INSERT INTO turn (team) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, targetTeam.name());
        preparedStatement.executeUpdate();
    }

    public void deleteTurn() throws SQLException {
        String query = "TRUNCATE turn";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public Team findTurn() throws SQLException {
        String query = "SELECT * FROM turn";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Team output = Team.BLANK;
        while(resultSet.next()) {
            output = Team.of(resultSet.getString("team"));
        }
        return output;
    }
}
