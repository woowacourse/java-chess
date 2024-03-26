package chess.model.game;

import chess.model.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        Turn difference = Turn.from(Side.WHITE);
        Turn otherDifference = Turn.from(Side.WHITE);

        // when
        boolean result = difference.equals(otherDifference);

        // then
        assertThat(result).isTrue();
    }

}
