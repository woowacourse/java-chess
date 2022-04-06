package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.web.Member;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberDaoImplTest {
    private MemberDaoImpl memberDaoImpl;

    @BeforeEach
    void setUp() {
        memberDaoImpl = new MemberDaoImpl();
    }

    //connection
    @DisplayName("member 테이블에 대한 connection 생성한다.")
    @Test
    void connection() {
        final Connection connection = memberDaoImpl.getConnection();
        assertThat(connection).isNotNull();
    }

    //save_by_insert_into (C) with tearDown removeById(D)
    @DisplayName("member 테이블에 데이터를 생성한다.")
    @Test
    void save() {
        Assertions.assertDoesNotThrow(
            () -> memberDaoImpl.save(new Member("테스트용Id", "테스트용Name", 0.0))
        );
    }

    //findById_by_select_where (R)
    @DisplayName("member 테이블에서 id를 통해 특정 데이터를 가져와 해당 class 객체로 응답받는다.")
    @Test
    void findById() {
        memberDaoImpl.save(new Member("테스트용Id", "테스트용Name", 0.0));
        final Member member = memberDaoImpl.findById("테스트용Id");

        assertThat(member.getName()).isEqualTo("테스트용Name");
    }

    //findAll_by_select_noWhere(R2)
    @DisplayName("member 테이블에서 모든 데이터를 가져와 해당 class 객체들들로 응받는다.")
    @Test
    void select_findAll() {
        // 최소 1개 데이터(tearDown에서 removeById될)를 넣어주고,
        // -> 데이터를 EmptyList가 아닌 list를 받아오면 통과다
        memberDaoImpl.save(new Member("테스트용Id", "테스트용Name", 0.0));
        final List<Member> members = memberDaoImpl.findAll();

        assertThat(members).isNotEmpty();
    }

    @DisplayName("member 테이블에 있는 특정 데이터를 id로 찾아 name 값을 변경한다.")
    @Test
    void updateNameById() {
        //바뀌기전 데이터
        memberDaoImpl.save(new Member("테스트용Id", "테스트용Name", 0.0));

        //바뀐후 데이터
        memberDaoImpl.updateNameById("테스트용Id", "바뀐이름");
        final Member members = memberDaoImpl.findById("테스트용Id");

        assertThat(members.getName()).isEqualTo("바뀐이름");
    }

    @AfterEach
    void tearDown() {
        // C + C->R  + C -> U이 ----> 모두 @AfterEach에서 D에 의존한다.
        //.save()  / .save() 후 .findById() / .save()후 findAll() / .save()후 update
        memberDaoImpl.removeById("테스트용Id");
    }
}
