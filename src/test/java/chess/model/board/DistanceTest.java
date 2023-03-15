package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.position.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DistanceTest {

    @Test
    @DisplayName("생성자는 rank와 file을 전달하면 Distance를 생성한다.")
    void constructor_givenRankAndFile_ThenSuccess() {
        final Distance distance = assertDoesNotThrow(() -> new Distance(1, 1));

        assertThat(distance).isExactlyInstanceOf(Distance.class);
    }

    @ParameterizedTest(name = "converToIndex()는 호출하면 거리를 인덱스로 변환한다.")
    @CsvSource({"1,1,9", "3,5,43", "-2,-1,-10"})
    void convertToIndex_whenCall_thenReturnIndex(final int rank, final int file, final int result) {
        final Distance distance = new Distance(rank, file);

        assertThat(distance.convertToIndex()).isEqualTo(result);
    }
}
