package domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("주어진 File, Rank와 일치하는 포지션인지 확인한다.")
    @Test
    void findPosition() {
        // given
        Rank rank = Rank.ONE;
        File file = File.A;
        Position position = new Position(Rank.ONE, File.A);

        // when
        boolean isRightPosition = position.findPosition(rank, file);

        // then
        assertThat(isRightPosition).isTrue();
    }
}
