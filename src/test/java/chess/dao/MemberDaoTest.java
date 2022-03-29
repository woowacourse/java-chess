package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MemberDaoTest {

    @Test
    void setup() {
        MemberDao memberDao = new MemberDao();
        Connection con = memberDao.getConnection();
        assertThat(con).isNotNull();
    }

    @Test
    void save() {
        MemberDao memberDao = new MemberDao();
        memberDao.save(new Member("shb", "사현빈"));
    }

    @Test
    void findById() {
        final MemberDao dao = new MemberDao();
        Member member = dao.findById("shb");
        assertThat(member.getName()).isEqualTo("사현빈");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }

    @Test
    void findWithRoleById() {
        final MemberDao dao = new MemberDao();
        Member member = dao.findWithRoleById("shb");
        assertThat(member.getRole().getRole()).isEqualTo("admin");
    }
}
