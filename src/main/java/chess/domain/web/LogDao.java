package chess.domain.web;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LogDao 클래스는 DB와의 통신을 위한 클래스로, DB에 게임 기록을 저장하거나 불러오는 작업을 수행한다.
 * 필요한 테이블 조작 기능 세 가지만을 메서드로 가지고 있다.
 * 기능의 목록은 다음과 같다.
 *  - insert : 새로운 기록을 테이블에 추가한다. 게임 진행 중에 수시로 호출된다.
 *  - selectAll : 테이블의 모든 기록을 가져온다. 불러오기 기능에서 사용된다.
 *  - clear : 테이블의 모든 기록을 지운다. 게임이 종료되거나, 기존 기록을 지우고 게임을 새로 시작핧 때 사용된다.
 */
public class LogDao {
    // TODO: 2020/04/05 문서화 주석 작성
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

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void insert(String start, String end) throws SQLException {
        String query = "INSERT INTO log (start, end) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, start);
        pstmt.setString(2, end);
        pstmt.executeUpdate();
    }

    public Map<Integer, Log> selectAll() throws SQLException {
        String query = "SELECT * FROM log";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Map<Integer, Log> result = new LinkedHashMap<>();
        while (rs.next()) {
            result.put(Integer.parseInt(rs.getString("log_id")), new Log(rs.getString("start"), rs.getString("end")));
        }
        return Collections.unmodifiableMap(result);
    }

    public void clear() throws SQLException {
        String query = "DELETE FROM log";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();

        query = "ALTER TABLE log AUTO_INCREMENT=1";
        pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}