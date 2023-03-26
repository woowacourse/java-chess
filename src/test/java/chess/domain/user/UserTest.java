package chess.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class UserTest {

    @Test
    void 사용자가_정상_생성된다() {
        // given
        final User user = new User(1, "가비");

        // expect
        assertThat(user.getName()).isEqualTo("가비");
    }
}
