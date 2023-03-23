package chess.domain;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.pieces.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("nextPosition()은 이전 포지션을 받아 다음 위치를 반환한다.")
    void test_() {
        // given
        Position position = new Position(Rank.ONE, File.A);

        // when

        Position expected = position.nextPosition(1, 0);
        // then
        Assertions.assertThat(expected).isInstanceOf(new Position(Rank.TWO, File.A).getClass());
    }
}
