package chess.piece;

import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.B4;
import static chess.fixture.PositionFixture.C1;
import static chess.fixture.PositionFixture.C5;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D6;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E7;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.F8;
import static chess.fixture.PositionFixture.G5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.path.Path;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Nested
    class validSearchPath {

        @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
        @Test
        void test_searchPathTo() {
            Piece bishop = Bishop.from(Color.WHITE);
            Path path = bishop.searchPathTo(C1, G5, null);

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(D2, E3, F4);
        }

        @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
        @Test
        void test_searchPathTo2() {
            Bishop bishop = Bishop.from(Color.BLACK);

            Path path = bishop.searchPathTo(F8, A3, null);

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(E7, D6, C5, B4);
        }
    }

    @DisplayName("비정상 경로를 받으면 예외 처리한다.")
    @Test
    void test_searchPathTo4() {
        Bishop bishop = Bishop.from(Color.WHITE);

        assertThatThrownBy(
                () -> bishop.searchPathTo(C1, E1, King.from(Color.WHITE)))
                .isInstanceOf(IllegalStateException.class);
    }
}
