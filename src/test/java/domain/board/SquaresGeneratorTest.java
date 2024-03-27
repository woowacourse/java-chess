package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SquaresGeneratorTest {

    @Test
    @DisplayName("64개의 칸을 생성한다.")
    void generate_SquaresSize() {
        SquaresGenerator squaresGenerator = new SquaresGenerator();

        Map<Position, Piece> squares = squaresGenerator.generate();

        assertThat(squares).hasSize(64);
    }
}
