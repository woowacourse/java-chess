package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("랭크")
class RankTest {

    @DisplayName("랭크는 8개로 이루어지고, 순서를 유지한다.")
    @Test
    void rank() {
        //given & when & then
        assertAll(
                () -> assertThat(Rank.ONE.index()).isEqualTo(0),
                () -> assertThat(Rank.TWO.index()).isEqualTo(1),
                () -> assertThat(Rank.THREE.index()).isEqualTo(2),
                () -> assertThat(Rank.FOUR.index()).isEqualTo(3),
                () -> assertThat(Rank.FIVE.index()).isEqualTo(4),
                () -> assertThat(Rank.SIX.index()).isEqualTo(5),
                () -> assertThat(Rank.SEVEN.index()).isEqualTo(6),
                () -> assertThat(Rank.EIGHT.index()).isEqualTo(7),
                () -> assertThat(Rank.values()).hasSize(8)
        );
    }

    @DisplayName("문자로 랭크를 찾을 수 있다")
    @Test
    void findRank() {
        //given
        String givenRank = "2";

        //when
        Rank rank = Rank.from(givenRank);

        //then
        assertThat(rank).isEqualTo(Rank.TWO);
    }

    @DisplayName("유효하지 않은 랭크면 예외가 발생한다")
    @Test
    void invalidRank() {
        //given
        String givenRank = "10";

        //when & then
        assertThatThrownBy(() -> Rank.from(givenRank))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
