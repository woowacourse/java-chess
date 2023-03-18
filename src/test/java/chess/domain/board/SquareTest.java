package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SquareTest {

    @Test
    void 파일과_랭크가_같으면_같은_체크_칸이다() {
        final File file = A;
        final Rank rank = ONE;
        final Square square = new Square(file, rank);

        assertThat(square).isEqualTo(new Square(file, rank));
    }
}
