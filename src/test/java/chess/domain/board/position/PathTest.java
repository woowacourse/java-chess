package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class PathTest {

    @Test
    @DisplayName("Path 객체 생성된다.")
    void createPathTest() {
        Path path = new Path(Arrays.asList(
                Position.of("a1"),
                Position.of("a2")
        ));

        assertThat(path).isInstanceOf(Path.class);
    }
}