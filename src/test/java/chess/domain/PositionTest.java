package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("위치는 가로, 세로 좌표값을 가진다.")
    void createPosition() {
        assertThatCode(() -> new Position(1, 1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("위치는 가로, 세로 범위는 각각 1 ~ 8 이다.")
    @CsvSource({"0,1", "1,0", "1, 9", "9, 1"})
    void createPositionThrowException(int file, int rank) {
        assertThatThrownBy(() -> new Position(file, rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
    }

    @Test
    @DisplayName("위치의 file이 최소인 경우 참을 반환한다.")
    void isMinimumFile() {
        Position position = new Position(1, 3);
        assertThat(position.isMinimumFile()).isTrue();
    }


    @Test
    @DisplayName("위치의 file이 최대인 경우 참을 반환한다.")
    void isMaximumFile() {
        Position position = new Position(8, 3);
        assertThat(position.isMaximumFile()).isTrue();
    }

    @Test
    @DisplayName("위치의 rank가 최소인 경우 참을 반환한다.")
    void isMinimumRank() {
        Position position = new Position(3, 1);
        assertThat(position.isMinimumRank()).isTrue();
    }


    @Test
    @DisplayName("위치의 rank가 최대인 경우 참을 반환한다.")
    void isMaximumRank() {
        Position position = new Position(3, 8);
        assertThat(position.isMaximumRank()).isTrue();
    }


    @Test
    @DisplayName("최대 최소가 아닌 값이 나올 경우 거짓을 반환한다.")
    void isNotBoundaryValue() {
        Position position = new Position(3, 3);
        assertAll(
                () -> assertThat(position.isMinimumFile()).isFalse(),
                () -> assertThat(position.isMaximumFile()).isFalse(),
                () -> assertThat(position.isMinimumRank()).isFalse(),
                () -> assertThat(position.isMaximumRank()).isFalse()
        );
    }
}
