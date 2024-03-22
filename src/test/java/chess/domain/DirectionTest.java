package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("방향")
class DirectionTest {

    @DisplayName("방향을 좌표간의 차이에 따라 결정한다.")
    @Test
    void findDirectionByDiff() {
        // given
        Square source = Square.of(File.A, Rank.SEVEN);
        Square destination = Square.of(File.B, Rank.SIX);
        SquareDifferent diff = source.calculateDiff(destination);

        // when
        Direction actual = Direction.findDirectionByDiff(diff);

        // then
        assertThat(actual).isEqualTo(Direction.RIGHT_DOWN);
    }
}
