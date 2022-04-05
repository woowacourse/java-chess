package db.dao;

import static org.assertj.core.api.Assertions.assertThat;

import db.Member;
import db.dao.MemberDao;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.Test;

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
        memberDao.save(new Member("pepper", "수연"));
    }

    @Test
    void saveWithRollback() throws Exception {
        final MemberDao memberDao = new MemberDao();
        final Connection connection = memberDao.getConnection();
        connection.setAutoCommit(false);
        memberDao.save(new Member("pobi", "박재성"), connection);
        List<Member> all = memberDao.findAll();
        for (Member member: all) {
            System.out.println(member);
        }
        System.out.println(all.size());
        connection.rollback();
    }

    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();
        Member member = memberDao.findById("pepper");
        System.out.println(member);
        assertThat(member.getName()).isEqualTo("수연");
    }

    @Test
    void findWithRoleById() {
        final MemberDao memberDao = new MemberDao();
        Member member = memberDao.findWithRoleById("pepper");
        System.out.println(member);
        assertThat(member.getRole().getRole()).isEqualTo("admin");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        for (Member member: members) {
            System.out.println(member);
        }
        assertThat(members).isNotEmpty();
    }
}
