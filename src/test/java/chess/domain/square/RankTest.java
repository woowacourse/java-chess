package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {
    @DisplayName("현재 칸과 이동할 칸의 Rank 차를 계산한다.")
    @ParameterizedTest(name = "현재 rank: {0}, 이동할 rank: {1} => 차: {2}")
    @CsvSource({"8,7,-1", "8,1,-7"})
    void calculateRankInterval(String src, String dst, int interval) {

        assertThat(Rank.calculate(Rank.findRankBy(src), Rank.findRankBy(dst))).isEqualTo(interval);
    }

    @DisplayName("Rank의 범위를 벗어나면 에러가 발생한다.")
    @ParameterizedTest(name = "source: {0}, destination: {1}")
    @CsvSource({"8,0", "9,8"})
    void calculateRankInterval_fail(String src, String dst) {
        assertThatThrownBy(() -> Rank.calculate(Rank.findRankBy(src), Rank.findRankBy(dst)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Rank를 입력했습니다.");

    }

    @DisplayName("rankInput으로 Rank를 찾는다.")
    @ParameterizedTest(name = "rankInput: {0}, Rank: {1}")
    @CsvSource({"1,ONE", "3,THREE"})
    void findRankByRankInput_success(String rankInput, Rank rank) {
        assertThat(Rank.findRankBy(rankInput)).isEqualTo(rank);
    }

    @DisplayName("rankInput이 Rank에 존재하지 않는다면 예외가 발생한다.")
    @ParameterizedTest(name = "존재하지 않는 rank: {0}")
    @CsvSource({"10", "0"})
    void findRankByRankInput_fail(String rankInput) {
        assertThatThrownBy(() -> Rank.findRankBy(rankInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Rank를 입력했습니다.");
    }

    @DisplayName("방향을 이용하여 다음 Rank을 반환한다.")
    @ParameterizedTest(name = "방향: {0}, 현재 rank: {1}, 이동할 rank: {2}")
    @CsvSource({"2,THREE,ONE", "-1,SEVEN,EIGHT"})
    void nextRank(int direction, Rank before, Rank next) {
        assertThat(before.next(direction)).isEqualTo(next);
    }
}
