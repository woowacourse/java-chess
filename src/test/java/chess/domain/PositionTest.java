package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("위치는 가로, 세로 좌표값을 가진다.")
    void Given_Position_When_CreateWithValidFileAndRank_Then_DoesNotThorAnyException() {
        //given, when, then
        assertThatCode(() -> new Position(1, 1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("위치는 가로, 세로 범위는 각각 1 ~ 8 이다.")
    @CsvSource({"0,1", "1,0", "1, 9", "9, 1"})
    void Given_Position_When_CreateWithInvalidFileAndRankRange_Then_Exception(int file, int rank) {
        //given, when ,then
        assertThatThrownBy(() -> new Position(file, rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
    }

    @Test
    @DisplayName("위치의 file이 최소인 경우 참을 반환한다.")
    void Given_Position_When_IsMinimumFileWithMinimumFile_Then_True() {
        //given
        Position position = new Position(1, 3);
        //when, then
        assertThat(position.isMinimumFile()).isTrue();
    }

    @Test
    @DisplayName("위치의 file이 최대인 경우 참을 반환한다.")
    void Given_Position_When_IsMaximumFileWithMaximumFile_Then_True() {
        //given
        Position position = new Position(8, 3);
        //when, then
        assertThat(position.isMaximumFile()).isTrue();
    }

    @Test
    @DisplayName("위치의 rank가 최소인 경우 참을 반환한다.")
    void Given_Position_When_IsMinimumRankWithMinimumRank_Then_True() {
        //given
        Position position = new Position(3, 1);
        //when, then
        assertThat(position.isMinimumRank()).isTrue();
    }

    @Test
    @DisplayName("위치의 rank가 최대인 경우 참을 반환한다.")
    void Given_Position_When_IsMaximumRankWithMaximumRank_Then_True() {
        //given
        Position position = new Position(3, 8);
        //when, then
        assertThat(position.isMaximumRank()).isTrue();
    }

    @Test
    @DisplayName("최대 최소가 아닌 값이 나올 경우 거짓을 반환한다.")
    void Given_Position_When_IsMinimumAndMaximumPositionWithNotMinMaxPosition_Then_False() {
        //given
        Position position = new Position(3, 3);
        //when, then
        assertAll(
                () -> assertThat(position.isMinimumFile()).isFalse(),
                () -> assertThat(position.isMaximumFile()).isFalse(),
                () -> assertThat(position.isMinimumRank()).isFalse(),
                () -> assertThat(position.isMaximumRank()).isFalse()
        );
    }

    @Test
    @DisplayName("현재 위치에서 더할 값이 위치 범위 내에 있으면 참을 반환한다.")
    void Given_Position_When_IsNextPositionInRangeWithAddedPoint_Then_True() {
        //given
        Position position = new Position(3, 3);
        //when, then
        assertThat(position.isNextPositionInRange(new Point(1, 1))).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더할 값이 위치 범위 밖에 있으면 거짓을 반환한다.")
    void Given_Position_When_IsNextPositionInRangeWithAddedPoint_Then_False() {
        //given
        Position position = new Position(3, 3);
        //when, then
        assertAll(
                () -> assertThat(position.isNextPositionInRange(new Point(8, 8))).isFalse(),
                () -> assertThat(position.isNextPositionInRange(new Point(-8, -8))).isFalse()
        );
    }
}
