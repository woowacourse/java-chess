package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("랭크")
class RankTest {

    @DisplayName("랭크는 8개로 이루어지고, 순서를 유지한다.")
    @Test
    void rank() {
        //given & when & then
        assertAll(
                () -> assertThat(Rank.ONE.ordinal()).isEqualTo(0),
                () -> assertThat(Rank.TWO.ordinal()).isEqualTo(1),
                () -> assertThat(Rank.THREE.ordinal()).isEqualTo(2),
                () -> assertThat(Rank.FOUR.ordinal()).isEqualTo(3),
                () -> assertThat(Rank.FIVE.ordinal()).isEqualTo(4),
                () -> assertThat(Rank.SIX.ordinal()).isEqualTo(5),
                () -> assertThat(Rank.SEVEN.ordinal()).isEqualTo(6),
                () -> assertThat(Rank.EIGHT.ordinal()).isEqualTo(7),
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
