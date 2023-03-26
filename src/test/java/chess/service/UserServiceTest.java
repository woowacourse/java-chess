package chess.service;

import chess.dao.MockUserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserServiceTest {

    @Test
    @DisplayName("사용자의 이름이 이미 저장되어 있다면, 예외를 발생시킨다.")
    void validateDuplicate() {
        // given
        final UserService userService = new UserService(new MockUserDao());

        // when, then
        assertThatThrownBy(() -> userService.validateDuplicate("journey"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 사용자입니다. 다른 이름을 입력해 주세요.");
    }

    @Test
    @DisplayName("사용자의 이름에 따라 사용자의 정보를 삽입한다.")
    void insert() {
        // given
        final UserService userService = new UserService(new MockUserDao());

        // when
        Long userId = userService.insert("journey");

        // then
        assertThat(userId)
                .isEqualTo(1L);
    }
}
