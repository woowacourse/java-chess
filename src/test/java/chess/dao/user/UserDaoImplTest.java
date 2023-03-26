package chess.dao.user;

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
        final String name = "journey";
        userDao.save(name);
        final UserEntity expected = new UserEntity(1L, name);

        // when
        final Optional<UserEntity> userEntity = userDao.findByName(name);
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

    @Test
    @DisplayName("사용자 정보를 삽입하면, 삽입된 사용자의 아이디를 반환한다.")
    void insert() {
        // given
        final UserDao userDao = new MockUserDao();

        // when
        Long userId = userDao.save("journey");

        // then
        assertThat(userId)
                .isEqualTo(1L);
    }
}
