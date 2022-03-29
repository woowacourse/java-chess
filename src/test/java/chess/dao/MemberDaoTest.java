package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoTest {

    @Test
    void connection() {
        final MemberDao memberDao = new MemberDao();
        final Connection connection = memberDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        final MemberDao memberDao = new MemberDao();
        memberDao.save(new Member("corinne", "코린"));
    }

    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findById("corinne");
        assertThat(member.getName()).isEqualTo("코린");
    }

    @Test
    void findWithRoleById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findWithRoleById("corinne");
        assertThat(member.getRole().getRole()).isEqualTo("admin");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }
}
