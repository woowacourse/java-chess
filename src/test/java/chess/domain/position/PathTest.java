package chess.domain.position;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.PositionFixture.A2;
import static chess.PositionFixture.A3;
import static chess.PositionFixture.A4;
import static chess.PositionFixture.A5;
import static chess.PositionFixture.A6;
import static chess.PositionFixture.A7;
import static chess.PositionFixture.B3;
import static chess.PositionFixture.B5;
import static chess.PositionFixture.C4;
import static chess.PositionFixture.C5;
import static chess.PositionFixture.D5;
import static chess.PositionFixture.E5;
import static chess.PositionFixture.E6;
import static chess.PositionFixture.F6;
import static org.assertj.core.api.Assertions.assertThat;

class PathTest {

    @Test
    @DisplayName("수평 위치 경로를 생성한다.")
    void find_horizontal_path_test() {
        final Path path = Path.of(E5, B5);

        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(D5, C5);
    }

    @Test
    @DisplayName("수직 위치 경로를 생성한다.")
    void find_vertical_path_test() {
        final Path path = Path.of(A2, A7);

        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(A3, A4, A5, A6);
    }

    @Test
    @DisplayName("대각선 위치 경로를 생성한다.")
    void find_diagonal_path_test() {
        final Path path = Path.of(A2, E6);

        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(B3, C4, D5);
    }

    @Test
    @DisplayName("수평, 수직 또는 대각선 위치에 있지 않으면 빈 경로를 반환한다.")
    void find_empty_path_test() {
        final Path path = Path.of(A2, F6);

        assertThat(path).extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .isEmpty();
    }
}
