package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

class MemberDaoTest {

    @Test
    public void connection() {
        // given
        final MemberDao memberDao = new MemberDao();
        // when
        final Connection connection = memberDao.getConnection();
        // then
        assertThat(connection).isNotNull();
    }

    @Test
    public void save() {
        // given
        final MemberDao memberDao = new MemberDao();
        // when
        memberDao.save(new Member("chalee", "이찬주"));
        // then
    }

    @Test
    public void findById() {
        // given
        final MemberDao memberDao = new MemberDao();
        // when
        final Member member = memberDao.findById("chalee");
        // then
        assertThat(member.getName()).isEqualTo("이찬주");
    }

    @Test
    public void findAll() {
        // given
        final MemberDao memberDao = new MemberDao();
        // when
        final List<Member> members = memberDao.findAll();
        // then
        assertThat(members).isNotEmpty();
    }

    @Test
    void finalWithRoleById() {
        // given
        final MemberDao memberDao = new MemberDao();
        // when
        final Member member = memberDao.findWithRoleById("chalee");
        // then
        assertThat(member.getName()).isEqualTo("이찬주");
        System.out.println(member);
    }
}