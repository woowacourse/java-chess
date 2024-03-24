package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RankTest {

    @Test
    @DisplayName("세로는 1~8 사이의 숫자로 생성된다.")
    void Rank_create_with_valid_range_number() {
        var result = Rank.valueOf("8");
        assertThat(result).isInstanceOf(Rank.class);
    }

    @Test
    @DisplayName("1~8이 아닌 숫자로 만들어진 세로는 존재하지 않는다.")
    void Rank_validate_with_range_number() {
        assertThatThrownBy(() -> {
            Rank.valueOf("10");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("값을 넘겨주어 rank을 최신화한다.")
    void Rank_Update_with_value() {
        Rank rank = Rank.valueOf("2");

        assertThat(rank.update(1)).isEqualTo(Rank.valueOf("3"));
    }

    @Test
    @DisplayName("rank과 rank의 차이를 구한다.")
    void Rank_Subtract_with_other_rank() {
        Rank rank = Rank.valueOf("2");
        var sut = Rank.valueOf("4");

        var result = sut.subtractRank(rank);

        assertThat(result).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 4, 1", "2, 2, 0", "2, 1, -1"})
    @DisplayName("rank과 rank의 크기를 비교하여 방향을 찾는다..")
    void Rank_Find_direction_with_other_rank(String value1, String value2, int compareResult) {
        var sut = Rank.valueOf(value1);
        Rank rank = Rank.valueOf(value2);

        var result = sut.findDirection(rank);

        assertThat(result).isEqualTo(compareResult);
    }
}
