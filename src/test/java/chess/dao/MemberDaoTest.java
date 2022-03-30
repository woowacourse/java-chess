package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Member;
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

//    @Test
//    void save() {
//        final MemberDao memberDao = new MemberDao();
//        memberDao.save(new Member("roma", "로마"));
//    }

    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findById("roma");
        System.out.println(member);
        assertThat(member.getName()).isEqualTo("로마");
    }

    @Test
    void findWithRoleById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findWithRoleById("roma");
        System.out.println(member);
        assertThat(member.getName()).isEqualTo("로마");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }
}