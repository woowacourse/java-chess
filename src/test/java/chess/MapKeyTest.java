package chess;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.ONE;

import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapKeyTest {
    @Test
    void mapKeyTest() {

        int key1 = Objects.hash(A, ONE);
        int key2 = Objects.hash(A, ONE);

        Assertions.assertThat(key1).isEqualTo(key2);
    }
}
