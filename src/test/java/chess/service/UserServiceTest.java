package chess.service;

import chess.dao.user.MockUserDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserServiceTest {

    @Test
    @DisplayName("사용자의 이름이 이미 저장되어 있다면, 존재하는 사용자의 아이디를 반환한다.")
    void getUserId_exist() {
        // given
        final UserService userService = new UserService(new MockUserDao());

        // when
        Long userId = userService.getUserId("journey");

        // then
        assertThat(userId)
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("사용자의 이름이 저장되지 않았다면, 새롭게 저장 후 저장된 아이디를 반환한다.")
    void getUserId_new() {
        // given
        final UserService userService = new UserService(new MockUserDao());

        // when
        Long userId = userService.getUserId("hello");

        // then
        assertThat(userId)
                .isEqualTo(2L);
    }
}
