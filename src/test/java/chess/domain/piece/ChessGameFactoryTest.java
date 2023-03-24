package chess.domain.piece;

import chess.domain.game.ChessGameFactory;
import chess.domain.game.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.InstanceOfAssertFactories.map;

class ChessGameFactoryTest {

    @DisplayName("64칸이 초기화된 보드를 생성한다.")
    @Test
    void boardInitTest() {
        // when && then
        Assertions.assertThat(ChessGameFactory.generate()).extracting("board")
                .asInstanceOf(map(Position.class, String.class))
                .size()
                .isEqualTo(64);
    }
}
