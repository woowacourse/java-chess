package chess.domain.route;

import static chess.fixture.PieceFixture.BLACK_ROOK;
import static chess.fixture.PieceFixture.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesTest {

    @DisplayName("모든 기물이 EMPTY는 아니다.")
    @Test
    void notAllEmpty() {
        Pieces pieces = new Pieces(List.of(EMPTY, BLACK_ROOK));

        boolean actual = pieces.notAllEmpty();

        assertThat(actual).isTrue();
    }

    @DisplayName("모든 기물이 EMPTY이다.")
    @Test
    void allEmpty() {
        Pieces pieces = new Pieces(List.of(EMPTY, EMPTY));

        boolean actual = pieces.notAllEmpty();

        assertThat(actual).isFalse();
    }
}
