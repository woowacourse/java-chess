package chess.web.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.web.dao.member.MemberDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(new MemberDaoImpl());
    }

    @DisplayName("")
    @Test
    void save() {
        assertDoesNotThrow(() -> memberService.save("jason"));
    }

    @DisplayName("")
    @Test
    void save_with_long_name() {
        assertThatThrownBy(() -> memberService.save("jason_long_name"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
