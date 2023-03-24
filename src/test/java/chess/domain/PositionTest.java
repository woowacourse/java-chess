package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.path.Direction;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
public class PositionTest {

    private static final String OUT_OF_BOUNDS_ERROR_MESSAGE = "말의 위치 범위를 벗어났습니다.";

    @ParameterizedTest
    @CsvSource(value = {"0:1", "1:0", "9:1", "1:9"}, delimiter = ':')
    @DisplayName("Position 은 각각 1 ~ 8을 넘길 수 없다.")
    void 위치_생성_실패(int x, int y) {
        assertThatThrownBy(() -> Position.of(x, y))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(OUT_OF_BOUNDS_ERROR_MESSAGE);
    }

    @DisplayName("Position 이 1 ~ 8 이내이면 생성이 이루어진다. 두 좌표값을 받으면 첫 번째를 파일로, 두 번째를 랭크로 생성한다.")
    @Test
    void 위치_생성_성공_좌표값() {
        Position coordsToPosition = Position.of(1, 8);
        Position inputsToPosition = Position.from("a8");

        assertThat(coordsToPosition).isEqualTo(inputsToPosition);
    }

    @DisplayName("문자열로 전달된 Position 이 범위 밖의 값이라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"i1", "11", "a0", "a9"})
    void 문자열_위치_생성_실패(String positionInput) {
        assertThatThrownBy(() -> Position.from(positionInput))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("전달 받은 방향에 따른 다음 위치를 반환한다.")
    @Test
    void 다음_이동_위치_반환() {
        Position current = Position.from("A1");

        Position next = current.findNextPosition(Direction.NORTH_EAST);

        assertThat(next).isEqualTo(Position.from("B2"));
    }

    @DisplayName("전달 받은 방향에 따른 다음 위치가 범위 밖이라면, null 이 반환된다.")
    @Test
    void 다음_이동_불가() {
        Position current = Position.from("H1");

        Position next = current.findNextPosition(Direction.EAST);

        assertThat(next).isNull();
    }

    @DisplayName("전달 받은 위치가 현재 위치로부터 대각선 한 칸 앞이면 참을 반환한다.")
    @Test
    void 대각선_한칸앞_확인() {
        Position current = Position.from("A1");

        assertThat(current.isOneStepForwardDiagonal(Position.from("B2")))
            .isTrue();
    }

}
