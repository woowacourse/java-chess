package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @DisplayName("64칸이 초기화된 보드를 생성한다.")
    @Test
    void boardInitTest() {
        // when && then
        assertThat(BoardFactory.generate()).extracting("board")
                .asInstanceOf(map(Position.class, String.class))
                .size()
                .isEqualTo(64);
    }
}
