package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DirectionTest {

    private static final Position START_POSITION = Position.of(3, 3);

    // TODO parameterizedTest로 변경 ? 하드코딩 수정
    // TODO optional empty 처리
    @Test
    void 이동정보_변환() {
        assertThat(Direction.find(START_POSITION, Position.of(4, 3)).get())
                .isEqualTo(Direction.EAST);
        assertThat(Direction.find(START_POSITION, Position.of(2, 3)).get())
                .isEqualTo(Direction.WEST);
        assertThat(Direction.find(START_POSITION, Position.of(3, 2)).get())
                .isEqualTo(Direction.SOUTH);
        assertThat(Direction.find(START_POSITION, Position.of(3, 4)).get())
                .isEqualTo(Direction.NORTH);
        assertThat(Direction.find(START_POSITION, Position.of(4, 4)).get())
                .isEqualTo(Direction.NORTH_EAST);
        assertThat(Direction.find(START_POSITION, Position.of(2, 4)).get())
                .isEqualTo(Direction.NORTH_WEST);
        assertThat(Direction.find(START_POSITION, Position.of(4, 2)).get())
                .isEqualTo(Direction.SOUTH_EAST);
        assertThat(Direction.find(START_POSITION, Position.of(2, 2)).get())
                .isEqualTo(Direction.SOUTH_WEST);
        assertThat(Direction.find(START_POSITION, Position.of(5, 4)).get())
                .isEqualTo(Direction.EAST_UP);
        assertThat(Direction.find(START_POSITION, Position.of(5, 2)).get())
                .isEqualTo(Direction.EAST_DOWN);
        assertThat(Direction.find(START_POSITION, Position.of(1, 4)).get())
                .isEqualTo(Direction.WEST_UP);
        assertThat(Direction.find(START_POSITION, Position.of(1, 2)).get())
                .isEqualTo(Direction.WEST_DOWN);
        assertThat(Direction.find(START_POSITION, Position.of(4, 5)).get())
                .isEqualTo(Direction.NORTH_RIGHT);
        assertThat(Direction.find(START_POSITION, Position.of(2, 5)).get())
                .isEqualTo(Direction.NORTH_LEFT);
        assertThat(Direction.find(START_POSITION, Position.of(4, 1)).get())
                .isEqualTo(Direction.SOUTH_RIGHT);
        assertThat(Direction.find(START_POSITION, Position.of(2, 1)).get())
                .isEqualTo(Direction.SOUTH_LEFT);
    }
}
