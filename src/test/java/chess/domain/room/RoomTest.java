package chess.domain.room;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RoomTest {

    @Test
    void 방이_정상_생성된다() {
        // given
        final Room room = new Room(1, "방1", true);

        // expect
        assertThat(room.getName()).isEqualTo("방1");
    }
}
