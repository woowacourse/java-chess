package chess.dao;

import chess.Member;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        memberDao.save(new Member("yukong", "김유빈"));
    }

    @Test
    void saveWithRollback() throws Exception {
        final MemberDao memberDao = new MemberDao();
        final Connection connection = memberDao.getConnection();
        connection.setAutoCommit(false);
        memberDao.save(new Member("pobi", "박재성"));
        connection.rollback();
    }

    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findById("yukong");
        System.out.println(member);
        assertThat(member.getName()).isEqualTo("김유빈");
    }

    @Test
    void findWithRoleById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findWithRoleById("yukong");
        System.out.println(member);
        assertThat(member.getName()).isEqualTo("김유빈");
        assertThat(member.getRole().getRole()).isEqualTo("admin");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }
}