package chess.piece;

import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.H1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.path.Path;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo() {
        Piece piece = new King(Color.WHITE);

        assertThatThrownBy(
                () -> piece.searchPathTo(F1, H1, new King(Color.BLACK)))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo2() {
        Piece piece = new King(Color.WHITE);

        Path path = piece.searchPathTo(E1, E2, null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo3() {
        Piece piece = new King(Color.WHITE);

        Path path = piece.searchPathTo(E1, E2, new Queen(Color.BLACK));

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }
}
