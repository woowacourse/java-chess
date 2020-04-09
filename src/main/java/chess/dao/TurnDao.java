package chess.dao;

import chess.domain.game.Turn;

import java.sql.*;

/**
 * TurnDao 클래스는 DB와의 통신을 위한 클래스로, DB에 턴 기록을 저장하거나 불러오는 작업을 수행한다.
 * 필요한 테이블 조작 기능 두 가지를 메서드로 가지고 있다.
 * 기능의 목록은 다음과 같다.
 * - insert : 새로운 턴 기록을 게임의 id를 바탕으로 테이블에 추가한다. 게임 진행 중에 수시로 호출된다.
 * - select : id 값을 바탕으로 테이블의 기록을 가져온다. 불러오기 기능에서 사용된다.
 */
public class TurnDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
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

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    /**
     * insert는 턴의 변경이 생길 시 그 값을 id를 키로 하여 turn 테이블에 저장한다.
     *
     * @param id 검색을 위해 필요한 id 값이다.
     * @param turn 테이블에 저장할 턴 정보이다. 여기서 Color값을 받아 문자열로 저장한다.
     * @throws SQLException DB와의 통신이나 SQL 실행에 문제 발생 시 RuntimeException이 발생한다.
     */
    public void update(String id, Turn turn) throws SQLException {
        String query = "UPDATE turn SET game_id=?, turn=? WHERE game_id = ?;";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, id);
        pstmt.setString(2, turn.getColor().toString());
        pstmt.setString(3, id);
        pstmt.executeUpdate();
        closeConnection(connection);
    }

    /**
     * select는 DB의 turn 테이블에 저장된 턴 기록을 불러오는 메서드이다.
     * turn 정보는 각각 게임의 id값을 키로 하여 저장된다.
     * 그렇기 때문에, select는 파라미터로 id를 받아 그에 맞는 턴 값을 받는다.
     *
     * @param id 검색을 위해 필요한 id 값이다.
     * @return 전달받은 id를 바탕으로 검색된 turn 정보를 반환한다.
     * @throws SQLException DB와의 통신이나 SQL 실행에 문제 발생 시 RuntimeException이 발생한다.
     */
    public Turn select(String id) throws SQLException {
        String query = "SELECT turn FROM turn WHERE game_id = ?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        String turn = "";
        if(rs.next()) {
            turn = rs.getString("turn");
        }

        closeConnection(connection);
        return new Turn(turn);
    }
}