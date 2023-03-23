package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    private static final String OUT_OF_BOUNDS_ERROR_MESSAGE = "말의 위치 범위를 벗어났습니다.";

    @DisplayName("Position 은 각각 1 ~ 8을 넘길 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"0:1", "1:0", "9:1", "1:9"}, delimiter = ':')
    void 위치_생성_실패(int x, int y) {
        assertThatThrownBy(() -> Position.of(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_BOUNDS_ERROR_MESSAGE);
    }

    @DisplayName("Position 이 1 ~ 8 이내이면 생성이 이루어진다. 두 좌표값을 받으면 첫 번째를 파일로, 두 번째를 랭크로 생성한다.")
    @Test
    void 위치_생성_성공_좌표값() {
        Position coordsToPosition = Position.of(1, 8);
        Position inputsToPosition = Position.from(List.of(1, 8));

        assertThat(coordsToPosition).isEqualTo(inputsToPosition);
    }

    @DisplayName("전달 받은 방향에 따른 다음 위치를 반환한다.")
    @Test
    void 다음_이동_위치_반환() {
        Position current = Position.of(1, 1);

        Position next = current.findNextPosition(Direction.NORTH_EAST);

        assertThat(next).isEqualTo(Position.of(2, 2));
    }

    @DisplayName("전달 받은 방향에 따른 다음 위치가 범위 밖이라면, null 이 반환된다.")
    @Test
    void 다음_이동_불가() {
        Position current = Position.of(8, 1);

        Position next = current.findNextPosition(Direction.EAST);

        assertThat(next).isNull();
    }

    @DisplayName("다음 이동 위치를 전달받아, 증가하는 file 값을 계산한다.")
    @Test
    void 다음_이동_파일_증가값_반환() {
        int file = 1;
        int rank = 1;
        Position current = Position.of(file, rank);

        int increase = 1;

        assertThat(current.fileIncreaseFrom(Position.of(file + increase, rank)))
                .isEqualTo(increase);
    }

    @DisplayName("다음 이동 위치를 전달받아, 증가하는 rank 값을 계산한다.")
    @Test
    void 다음_이동_랭크_증가값_반환() {
        int file = 1;
        int rank = 1;
        Position current = Position.of(file, rank);

        int increase = 1;

        assertThat(current.rankIncreaseFrom(Position.of(file, rank + increase)))
                .isEqualTo(increase);
    }
}
