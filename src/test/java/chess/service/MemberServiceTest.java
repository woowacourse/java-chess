package chess.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.dao.MemberDaoImpl;
import chess.domain.Member;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
    @DisplayName("")
    @Test
    void save() {
        final MemberService memberService2 = new MemberService(new MemberDaoImpl() {
            private final Map<String, Member> members = new HashMap();

            @Override
            public void save(final Member member) {
                members.put(member.getId(), member);
            }

            @Override
            public Member findById(final String id) {
                return members.get(id);

            }
        });
        assertDoesNotThrow(() -> memberService2.save("jason"));
    }

    @DisplayName("")
    @Test
    void save_with_long_name() {
        final MemberService memberService2 = new MemberService(new MemberDaoImpl() {
            private final Map<String, Member> members = new HashMap();

            @Override
            public void save(final Member member) {
                members.put(member.getId(), member);
            }
        });

        assertThatThrownBy(() -> memberService2.save("jason_long_name"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
