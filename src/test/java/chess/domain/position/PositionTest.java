package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PositionTest {
    @DisplayName("Position 객체를 생성한다.")
    @Test
    void createPosition() {
        assertThatCode(() -> new Position(Rank.ONE, File.A))
                .doesNotThrowAnyException();
    }

    @DisplayName("rank가 1~8이 아닌 경우에 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 9,  10})
    void rankException(final int rank) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Position(Rank.findByRank(rank), File.A);
        }).withMessage("없는 랭크임! 입력 값: %d", rank);
    }

    @DisplayName("file의 value가 a~h를 의미하지 않는 경우에 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 9,  10})
    void fileException(final int file) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Position(Rank.EIGHT, File.findByFile(file));
        }).withMessage("없는 파일임! 입력 값: %d", file);
    }
}
