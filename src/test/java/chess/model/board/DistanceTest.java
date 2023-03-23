package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.position.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DistanceTest {

    @Test
    @DisplayName("생성자는 rank와 file을 전달하면 Distance를 생성한다.")
    void constructor_givenRankAndFile_ThenSuccess() {
        final Distance distance = assertDoesNotThrow(() -> new Distance(1, 1));

        assertThat(distance).isExactlyInstanceOf(Distance.class);
    }
}
