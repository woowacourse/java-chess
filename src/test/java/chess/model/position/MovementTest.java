package chess.model.position;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MovementTest {
    @Test
    void 출발지와_도착지가_같으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Movement(Position.of(1, 1), Position.of(1, 1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 출발지와_도착지_사이의_음수를_포함한_file_차이를_계산할_수_있다() {
        Movement movement = new Movement(Position.of(2, 1), Position.of(1, 1));
        assertThat(movement.getFileGap()).isEqualTo(-1);
    }

    @Test
    void 출발지와_도착지_사이의_음수를_포함한_rank_차이를_계산할_수_있다() {
        Movement movement = new Movement(Position.of(1, 2), Position.of(1, 1));
        assertThat(movement.getRankGap()).isEqualTo(-1);
    }

    @Test
    void 출발지와_도착지_사이의_file_거리를_계산할_수_있다() {
        Movement movement = new Movement(Position.of(2, 1), Position.of(1, 1));
        assertThat(movement.getFileDistance()).isEqualTo(1);
    }

    @Test
    void 출발지와_도착지_사이의_rank_거리를_계산할_수_있다() {
        Movement movement = new Movement(Position.of(1, 2), Position.of(1, 1));
        assertThat(movement.getRankDistance()).isEqualTo(1);
    }

    @Test
    void 출발지와_도착지가_같은_file_혹은_같은_rank_위에_놓여있는지_확인한다() {
        Movement movement = new Movement(Position.of(1, 1), Position.of(1, 2));
        assertThat(movement.isSameFileOrRank()).isTrue();
    }

    @Test
    void 출발지와_도착지가_같은_file_위에_놓여있는지_확인한다() {
        Movement movement = new Movement(Position.of(1, 1), Position.of(1, 2));
        assertThat(movement.isSameFile()).isTrue();
    }

    @Test
    void 출발지와_도착지가_같은_대각선_위에_놓여있는지_확인한다() {
        Movement movement = new Movement(Position.of(1, 1), Position.of(2, 2));
        assertThat(movement.isDiagonal()).isTrue();
    }

    @Test
    void 출발지와_도착지_사이에_있는_모든_position을_반환한다() {
        Movement movement = new Movement(Position.of(1, 1), Position.of(3, 3));
        assertThat(movement.getIntermediatePositions()).containsExactly(Position.of(2, 2));
    }

    @Test
    void 대각선이나_가로세로선이_아닌_움직임에서는_중간_위치를_반환하지_않는다() {
        Movement movement = new Movement(Position.of(1, 1), Position.of(3, 2));
        List<Position> positions = movement.getIntermediatePositions();
        assertThat(positions.isEmpty()).isTrue();
    }
}
