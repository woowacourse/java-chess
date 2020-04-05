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
     * insert는 말의 이동이 생길 시 그 명령을 DB의 log 테이블에 저장한다.
     * id값은 테이블을 생성할 때 AUTO_INCREMENT로 설정하였으므로,
     * 이동 시작 위치와 끝 위치만 전달하면 이후 이를 바탕으로 경기를 불러올 수 있다.
     *
     * @param start 이동이 시작된 위치값이다.
     * @param end 이동이 끝난 위치값이다.
     * @throws SQLException DB와의 통신이나 SQL 실행에 문제 발생 시 RuntimeException이 발생한다.
     */
    public void insert(String start, String end) throws SQLException {
        String query = "INSERT INTO log (start, end) VALUES (?, ?)";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, start);
        pstmt.setString(2, end);
        pstmt.executeUpdate();
    }

    /**
     * selectAll은 DB의 log 테이블에 저장된 모든 기록을 불러오는 메서드이다.
     * 이를 바탕으로, 첫 턴부터 현재 턴까지의 경기 내용을 반영할 수 있다.(즉, 경기를 불러올 수 있다.)
     * 테이블에는 자동으로 증가하는 id값과 그에 따른 기록이 저장되는데,
     * 자동으로 증가하는 id값은 기록의 순서와 같으므로, 각 기록이 몇 번째 턴에 기록되었는지를 뜻하게 된다.
     * 그렇기에 id값을 key로, Log 클래스를 value로 가지는 맵을 만들어 이를 반환한다.
     * 이때 맵은 LinkedHashMap으로 하여, 순서를 가지도록 한다.
     * (사용하기에 따라서는, 맵의 키 값을 통해 특정 턴까지만 불러오거나 이전으로 명령을 되돌리는 등의 기능도 수행할 수 있다)
     *
     * @return 각각의 턴에 대한 로그가 매칭되는 Map을 반환한다.
     * @throws SQLException DB와의 통신이나 SQL 실행에 문제 발생 시 RuntimeException이 발생한다.
     */
    public Map<Integer, Log> selectAll() throws SQLException {
        String query = "SELECT * FROM log";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        Map<Integer, Log> result = new LinkedHashMap<>();
        while (rs.next()) {
            int key = Integer.parseInt(rs.getString("log_id"));
            Log log = new Log(rs.getString("start"), rs.getString("end"));
            result.put(key, log);
        }
        return Collections.unmodifiableMap(result);
    }

    /**
     * clear는 DB의 log 테이블에 저장된 모든 기록을 지우는 메서드이다.
     * log 테이블은 한 게임에 대한 정보만 기록할 수 있다.
     * 그런데 만약 게임이 종료되었는데 기록이 지워지지 않거나,
     * 혹은 새 게임을 시작하려고 하는데 이전 기록이 남아있을 경우 오류를 유발할 수 있다.
     * 그렇기 때문에, 완전히 게임이 끝나거나 유저가 새 게임을 시작하려고 할 경우
     * 테이블의 모든 기록을 지운다.
     *
     * @throws SQLException DB와의 통신이나 SQL 실행에 문제 발생 시 RuntimeException이 발생한다.
     */
    public void clear() throws SQLException {
        String query = "DELETE FROM log";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();

        query = "ALTER TABLE log AUTO_INCREMENT=1";
        pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
    }
}