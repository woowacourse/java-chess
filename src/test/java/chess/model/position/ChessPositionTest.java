package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessPositionTest {

    @DisplayName("다른 위치와 거리를 계산한다.")
    @Test
    void calculateDistance() {
        // given
        ChessPosition now = new ChessPosition(File.C, Rank.SIX);
        ChessPosition other = new ChessPosition(File.A, Rank.ONE);

        // when
        Distance distance = now.calculateDistance(other);

        // then
        assertThat(distance.getFileDifference()).isEqualTo(2);
        assertThat(distance.getRankDifference()).isEqualTo(5);

    }
}
