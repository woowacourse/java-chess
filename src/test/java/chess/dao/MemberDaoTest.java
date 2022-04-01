//package chess.dao;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//import chess.Member;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//
//class MemberDaoTest {
//
//    @Test
//    void connection() {
//        final MemberDao memberDao = new MemberDao();
//        final Connection connection = memberDao.getConnection();
//        assertThat(connection).isNotNull();
//    }
//
//    @Test
//    void save() throws SQLException {
//        final MemberDao memberDao = new MemberDao();
//        Connection connection = memberDao.getConnection();
//        connection.setAutoCommit(false);
//        memberDao.save(new Member("eden", "sungsan"));
//        connection.rollback();
//
//    }
//
//    @Test
//    void findById() {
//        final MemberDao memberDao = new MemberDao();
//        final Member member = memberDao.findById("eden");
//        assertThat(member.getName()).isEqualTo("sungsan");
//    }
//
//    @Test
//    void findByIdWithRole() {
//        final MemberDao memberDao = new MemberDao();
//        final Member member = memberDao.findWithRoleById("neo");
//        assertThat(member.getName()).isEqualTo("admin");
//    }
//
//    @Test
//    void findAll() {
//        final MemberDao memberDao = new MemberDao();
//        final List<Member> members = memberDao.findAll();
//        assertThat(members).isNotEmpty();
//        assertThat(members.size()).isEqualTo(2);
//    }
//}
