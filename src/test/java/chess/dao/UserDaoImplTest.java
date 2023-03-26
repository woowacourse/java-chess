package chess.dao;

import chess.dao.user.UserDao;
import chess.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserDaoImplTest {

    @Test
    @DisplayName("사용자가 입력한 이름이 존재하면 해당 이름을 가진 User 엔티티를 반환한다.")
    void findByName() {
        // given
        final UserDao userDao = new MockUserDao();
        final UserEntity expected = new UserEntity(1L, "journey");

        // when
        final Optional<UserEntity> userEntity = userDao.findByName("journey");
        final UserEntity actual = userEntity.get();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("사용자가 입력한 이름이 존재하지 않으면 빈 객체를 반환한다.")
    void findByNameEmpty() {
        // given
        final UserDao userDao = new MockUserDao();

        // when
        final Optional<UserEntity> userEntity = userDao.findByName("pobi");

        assertThat(userEntity)
                .isEqualTo(Optional.empty());
    }
}
