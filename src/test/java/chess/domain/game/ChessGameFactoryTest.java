package chess.domain.game;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

class ChessGameFactoryTest {

    @DisplayName("64칸이 초기화된 보드를 생성한다.")
    @Test
    void boardInitTest() {
        // when && then
        assertThat(ChessGameFactory.generate()).extracting("board")
                .extracting("names", InstanceOfAssertFactories.collection(LIST.getClass()))
                .size()
                .isEqualTo(64);
    }
}
