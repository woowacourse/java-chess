package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PathTest {
    @Test
    @DisplayName("이동 방향으로 정상적인 경로 생성")
    void next() {
        Path path = new Path(Position.from("a2"), Position.from("a5"), Direction.NORTH);
        assertThat(path.hasNext()).isTrue();
        assertThat(path.next()).isEqualTo(Position.from("a3"));
        assertThat(path.hasNext()).isTrue();
        assertThat(path.next()).isEqualTo(Position.from("a4"));
        assertThat(path.hasNext()).isFalse();
    }
}
