package db_study.dao;

import org.junit.jupiter.api.Test;

class MemberDaoTest {

    @Test
        // DB 연동!
    void connection() {
        // MySQL과의 연결고리가 생겨나야 쿼리를 보내고 결과를 받아올 수 있게 됨.
        // DB에 있어 중요한 자원. 다다익선이 아님.

//        final MemberDao memberDao = new MemberDao();
//        final Connection connection = memberDao.getConnection();
//        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
//        final MemberDao memberDao = new MemberDao();
//        memberDao.save(new Member("jeong_id", "jeong"));
    }

    // select id, name from member;
    @Test
    void findById() {
//        final MemberDao memberDao = new MemberDao();
//        final Member member = memberDao.findById("jeong_id");
//        assertThat(member.getName()).isEqualTo("jeong");
    }

    @Test
    void findAll() {
//        final MemberDao memberDao = new MemberDao();
//        final List<Member> members = memberDao.findAll();
//        assertThat(members).isNotEmpty();
    }
}
