package chess.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Member;
import chess.domain.piece.Role;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberDaoTest {
    @Test
    @DisplayName("커넥션 테스트")
    void connection() {
        final MemberDao memberDao = new MemberDao();
        final Connection connection = memberDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("데이터 저장 테스트")
    void save() {
        final MemberDao memberDao = new MemberDao();
        memberDao.save(new Member("Richard", "리차드"));
    }

    @Test
    @DisplayName("아이디로 단 건 조회 테스트")
    void findById() {
        final MemberDao memberDao = new MemberDao();
        Member member = memberDao.findById("Richard");
        assertThat(member.getId()).isEqualTo("Richard");
    }

    @Test
    @DisplayName("아이디로 단 건 조회 테스트")
    void findAll() {
        final MemberDao memberDao = new MemberDao();
        List<Member> members = memberDao.findAll();
        assertThat(members).isNotEmpty();
    }

    @Test
    @DisplayName("조인 단 건 조회 테스트")
    void findRoleById() {
        final MemberDao memberDao = new MemberDao();
        final Member member = memberDao.findRoleById("Richard");
        assertThat(member).isEqualTo(new Member("Richard", "리차드", new Role("ADMIN")));
    }
}
