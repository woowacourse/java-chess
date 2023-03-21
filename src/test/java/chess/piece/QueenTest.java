package chess.piece;

import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.E6;
import static chess.fixture.PositionFixture.E7;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.G3;
import static chess.fixture.PositionFixture.H4;
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

class QueenTest {

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo() {

        Piece queen = new Queen(Color.WHITE);

        Path path = queen.searchPathTo(E1, E8, null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(E2, E3, E4, E5, E6, E7);
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo2() {

        Queen queen = new Queen(Color.WHITE);

        Path path = queen.searchPathTo(E1, H4, null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(F2, G3);
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo3() {

        Queen queen = new Queen(Color.WHITE);

        Path path = queen.searchPathTo(E5, E1, null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(E4, E3, E2);
    }

    @DisplayName("비정상 경로를 받으면 예외 처리한다.")
    @Test
    void test_searchPathTo4() {

        Queen queen = new Queen(Color.WHITE);

        assertThatThrownBy(
                () -> queen.searchPathTo(E5, E1, new King(Color.WHITE)))
                .isInstanceOf(IllegalStateException.class);
    }
}
