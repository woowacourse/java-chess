package chess.utils;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BetweenPositionsGeneratorTest {

    @Test
    @DisplayName("같은 row인 경우 그 사이의 Positions 확인")
    void checkSameRowPosition() {
        assertThat(BetweenPositionsGenerator.
                computeBetweenPositionBySameRow(new Position(4, 4), new Position(4, 7)))
                .isEqualTo(List.of(new Position(4, 5), new Position(4, 6)));
    }

    @Test
    @DisplayName("같은 column인 경우 그 사이의 Positions 확인")
    void checkSameColumnPosition() {
        assertThat(BetweenPositionsGenerator.
                computeBetweenPositionBySameColumn(new Position(7, 4), new Position(4, 4)))
                .isEqualTo(List.of(new Position(5, 4), new Position(6, 4)));
    }
}