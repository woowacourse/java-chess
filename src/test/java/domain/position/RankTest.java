package domain.position;

import domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @DisplayName("인덱스를 입력하면 해당하는 Rank를 반환한다.")
    @Test
    void getFileTest() {
        // Given
        final int index = 2;

        // When
        Rank file = Rank.of(index);

        // Then
        assertThat(file).isEqualTo(Rank.SIX);
    }

    @DisplayName("유효하지 않은 인덱스를 입력하면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenInputInvalidIndex() {
        // Given
        final int index = 13;

        // When & Then
        assertThatThrownBy(() -> Rank.of(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 인덱스입니다.");
    }
}
