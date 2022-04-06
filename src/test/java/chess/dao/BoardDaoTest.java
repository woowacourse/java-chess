package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @Test
    void connection() {
        final BoardDao boardDao = new BoardDao();
        final Connection connection = boardDao.getConnection();
        assertThat(connection).isNotNull();
    }

//    @Test
//    void save() {
//        final MemberDao memberDao = new MemberDao();
//        memberDao.save(new Member("rennon", "조형래"));
//    }
//
//    @Test
//    void findById() {
//        final MemberDao memberDao = new MemberDao();
//        final Member member = memberDao.findById("rennon");
//        System.out.println(member);
//        assertThat(member.getName()).isEqualTo("조형래");
//    }
//
//    @Test
//    void findAll() {
//        final MemberDao memberDao = new MemberDao();
//        final List<Member> members = memberDao.findAll();
//        System.out.println(members);
//        assertThat(members).isNotEmpty();
//    }
//
//    @Test
//    void findWithRoleById() {
//        final MemberDao memberDao = new MemberDao();
//        final Member member = memberDao.findWithRoleById("rennon");
//        System.out.println(member);
//        assertThat(member.getName()).isEqualTo("조형래");
//    }
}
