package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.Member;
import chess.Role;

class MemberDaoTest {

    @Test
    void connection() {
        MemberDao memberDao = new MemberDao();
        Connection connection = memberDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        MemberDao memberDao = new MemberDao();
        memberDao.save(new Member("sojukang", "강승주", new Role("admin")));
    }

    @Test
    void findById() {
        MemberDao memberDao = new MemberDao();
        Member member = memberDao.findById("sojukang");
        assertThat(member.getName()).isEqualTo("강승주");
    }

    @Test
    void findWithRoleById() {
        MemberDao memberDao = new MemberDao();
        Member member = memberDao.findWithRoleById("sojukang");
        System.out.println(member);
        assertThat(member.getName()).isEqualTo("강승주");
        assertThat(member.getRole().getRole()).isEqualTo("admin");
    }

    @Test
    void findAll() {
        MemberDao memberDao = new MemberDao();
        List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }
}