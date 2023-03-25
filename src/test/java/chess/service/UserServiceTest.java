package chess.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dto.NameDto;
import chess.dto.UserDto;
import chess.repository.UserDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class UserServiceTest {

    private UserDao mockUserDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        mockUserDao = new UserDao() {
            private final List<UserDto> users = new ArrayList<>();
            private int index = 0;

            @Override
            public void save(final NameDto nameDto) {
                users.add(new UserDto(++index, nameDto.getValue()));
            }

            @Override
            public UserDto findByName(final NameDto nameDto) {
                return users.stream()
                        .filter(user -> user.getName().equals(nameDto.getValue()))
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public void deleteAll() {
            }
        };
        userService = new UserService(mockUserDao);
    }

    @Test
    void 사용자의_이름을_받아_사용자를_저장한다() {
        // given
        final NameDto nameDto = new NameDto("herb");

        // when
        userService.save(nameDto);

        // then
        final UserDto result = userService.findByName(nameDto);
        assertThat(result.getName()).isEqualTo("herb");
    }

    @Test
    void 사용자의_이름을_받아_사용자를_조회한다() {
        // given
        final NameDto nameDto = new NameDto("herb");
        userService.save(nameDto);

        // when
        final UserDto result = userService.findByName(nameDto);

        // then
        assertAll(
                () -> assertThat(result.getId()).isPositive(),
                () -> assertThat(result.getName()).isEqualTo("herb")
        );
    }
}
