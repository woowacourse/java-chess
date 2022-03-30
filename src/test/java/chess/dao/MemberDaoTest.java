package chess.dao;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import chess.Member;
import java.sql.Connection;
import java.util.List;
import org.assertj.core.api.Assertions;
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
        memberDao.save(new Member("alex", "이영환"));
    }

    @Test
    void findById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findById("alex");
        assertThat(member.getName()).isEqualTo("이영환");
    }

    @Test
    void findByWithRoleId() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findByWithRoleId("alex");
        assertThat(member.getName()).isEqualTo("이영환");
    }

    @Test
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        final List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }
}