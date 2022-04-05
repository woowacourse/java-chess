package lecture.jason.dao;

import lecture.jason.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberDaoTest {

    final MemberDao memberDao = new MemberDao();
    final Connection connection = memberDao.getConnection();

    @BeforeEach
    void setUp() {
        memberDao.deleteAll();
    }

    @Test
    void connection() {
        assertThat(connection).isNotNull();
    }

    @Test
    void saveAndFindById() {
        Member member = new Member(1, "philz");
        memberDao.save(member);
        Member findMember = memberDao.findById(1);
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        memberDao.save(new Member(1, "user a"));
        memberDao.save(new Member(2, "user b"));
        List<Member> members = memberDao.findAll();
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void deleteById() {
        memberDao.save(new Member(1, "user a"));
        memberDao.save(new Member(2, "user b"));
        memberDao.deleteById(1);
        List<Member> members = memberDao.findAll();
        assertThat(members.size()).isEqualTo(1);
    }
}
