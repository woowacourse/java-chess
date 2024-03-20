package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquaresGeneratorTest {

    @Test
    @DisplayName("64개의 칸을 생성한다.")
    void generate_SquaresSize() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();

        List<Square> squares = squaresGenerator.generate();

        assertThat(squares).hasSize(64);
    }
}
