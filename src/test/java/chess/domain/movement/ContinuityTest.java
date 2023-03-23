package chess.domain.movement;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ContinuityTest {

    @ParameterizedTest(name = "이동한 칸의 개수에 따라 continuity를 반환한다.")
    @CsvSource({
            "1,1,DISCONTINUOUS",
            "2,1,CONTINUOUS",
            "2,0,CONTINUOUS"})
    void checkContinuity(int fileInterval, int rankInterval, Continuity continuity) {
        assertThat(Continuity.of(fileInterval, rankInterval)).isEqualTo(continuity);
    }
}
