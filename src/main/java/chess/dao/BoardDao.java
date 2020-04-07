package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Rows;

import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * HistoryDao 클래스는 DB와의 통신을 위한 클래스로, DB에 게임 기록을 저장하거나 불러오는 작업을 수행한다.
 * 필요한 테이블 조작 기능 세 가지만을 메서드로 가지고 있다.
 * 기능의 목록은 다음과 같다.
 * - insert : 새로운 기록을 테이블에 추가한다. 게임 진행 중에 수시로 호출된다.
 * - selectAll : 테이블의 모든 기록을 가져온다. 불러오기 기능에서 사용된다.
 * - clear : 테이블의 모든 기록을 지운다. 게임이 종료되거나, 기존 기록을 지우고 게임을 새로 시작핧 때 사용된다.
 */
public class BoardDao {
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
     * update는 말의 이동이 생길 시, 보드의 상태를 DB의 board 테이블에 저장한다.
     * 게임의 id값을 바탕으로, 각 줄을 문자열 형태로 변환하여 저장한다.
     *
     * @param id    상태 갱신을 위한 게임의 id값이다.
     * @param board 상태를 저장할 board 클래스이다.
     * @throws SQLException DB와의 통신이나 SQL 실행에 문제 발생 시 RuntimeException이 발생한다.
     */
    public void update(String id, Board board) throws SQLException {
        for (Rows row : board.getRows()) {
            String query = "UPDATE board SET game_id=?, row_number=?, row=? WHERE game_id = ? AND row_number=?;";
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, row.getColumn().getName());
            pstmt.setString(3, row.getResources());
            pstmt.setString(4, id);
            pstmt.setString(5, row.getColumn().getName());
            pstmt.executeUpdate();
            closeConnection(connection);
        }
    }

    /**
     * select는 DB의 board 테이블에 저장된 게임의 상태를 불러오는 메서드이다.
     * 이를 바탕으로, 보드에 말들이 어떻게 배열되었는지 알 수 있다.
     * 이 때, game의 id값을 바탕으로 조회하여 값을 가져온다.
     *
     * @param id 가져올 값을 선택하기 위한 게임의 id값이다.
     * @return 각각의 줄에 어떤 상태가 저장되었는지 매칭되어있는 Map을 반환한다.
     * @throws SQLException DB와의 통신이나 SQL 실행에 문제 발생 시 RuntimeException이 발생한다.
     */
    public Map<String, String> select(String id) throws SQLException {
        String query = "SELECT * FROM board WHERE game_id=?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        Map<String, String> result = new HashMap<>();
        while (rs.next()) {
            result.put(rs.getString("row_number"), rs.getString("row"));
        }

        closeConnection(connection);
        return Collections.unmodifiableMap(result);
    }
}