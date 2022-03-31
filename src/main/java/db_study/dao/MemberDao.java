package db_study.dao;

import db_study.Member;
import db_study.Role;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess"; // docker에서 3306:XXXX인 경우
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    // connection 생성 방법: 드라이버 필요
    // 기본적으로 자원을 잡아먹기 때문에 사용 끝난 후 닫아줘야 함.
    // 최소 사용되는 개수만큼 상시 돌려놔도 되기는 함. connection pool
    public Connection getConnection() {
        // loadDriver(); // 굳이 명시적으로 등록하지 않더라도 없으면 Driver manager에 의해 자동으로 등록해줌.
        // 복수의 드라이버 중 하나를 명시적으로 등록할 때 주로 사용.
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 드라이버 등록 : 드라이버는 연결을 뚫어주는 역할
    public void save(final Member member) {
        final Connection connection = getConnection();
        final String sql = "insert into member (id, name) values (?, ?)";
        // member 테이블의 id, name 컬럼에 해당 데이터 저장
        // (?, ?) : JDBC 문법

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // 아직 member 테이블이 없는 경우 예외 발생
            // java.sql.SQLSyntaxErrorException: Table 'chess.member' doesn't exist
        }
    }

    public Member findById(String id) {
        final Connection connection = getConnection();
        final String sql = "select id, name from member where id = (?)";
        // member 테이블에서 id 컬럼이 ?인 데이터의 id와 name 조회

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                    resultSet.getString("id"),
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // join
    public Member findWithRoleById(String id) {
        final Connection connection = getConnection();
        final String sql = "select id, name, role "
                + "from member join role on member.id = role.user_id "
                + "where id = (?)";

        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    new Role(resultSet.getString("role"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> findAll() {
        final Connection connection = getConnection();
        final String sql = "select id, name from member";
        List<Member> members = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(new Member(
                        resultSet.getString("id"),
                        resultSet.getString("name")
                ));
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 드라이버 등록 : 드라이버는 연결을 뚫어주는 역할
    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver 클래스의 static 블록을 실행 (클릭)
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
