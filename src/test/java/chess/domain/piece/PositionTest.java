package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("file, rank를 입력받아 위치를 생성한다.")
    @Test
    void create() {
        // given
        final Position positionA_ONE = new Position('a', 1);
        final Position positionB_TWO = new Position('b', 2);

        // when && then
        Assertions.assertThat(positionA_ONE).isEqualTo(new Position(File.A, Rank.ONE));
        Assertions.assertThat(positionB_TWO).isEqualTo(new Position(File.B, Rank.TWO));
    }
}
