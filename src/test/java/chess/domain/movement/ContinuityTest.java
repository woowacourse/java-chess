package chess.domain.movement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ContinuityTest {

    @DisplayName("이동한 칸의 개수에 따라 Continuity를 반환한다.")
    @ParameterizedTest(name = "({0}, {1}): {2}")
    @CsvSource({
            "1,1,DISCONTINUOUS",
            "2,1,CONTINUOUS",
            "2,0,CONTINUOUS"})
    void checkContinuity(int fileInterval, int rankInterval, Continuity continuity) {
        assertThat(Continuity.of(fileInterval, rankInterval)).isEqualTo(continuity);
    }
}
