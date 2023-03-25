package chess.piece;

import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.B4;
import static chess.fixture.PositionFixture.B6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.path.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {

    private static final Position INITIAL_POSITION = A2;

    @Nested
    class searchPathTo {

        @DisplayName("초기 위치에서 1칸 앞으로 전진할 수 있다..")
        @Test
        void test_searchPathTo() {
            Pawn pawn = Pawn.from(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, A3, null);

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @DisplayName("초기 위치에서는 2칸 앞으로 전진할 수 있다.")
        @Test
        void test_searchPathTo2() {

            Pawn pawn = Pawn.from(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, A4, null);

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(A3);
        }

        @DisplayName("대각선으로 이동하는 경우, 다른 색 말이 도착지에 있으면 이동할 수 있다.")
        @Test
        void test_searchPathTo3() {
            Pawn pawn = Pawn.from(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, B3, Pawn.from(Color.BLACK));

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @DisplayName("대각선으로 이동하는 경우, 같은 색 말이 도착지에 있으면 이동할 수 없다.")
        @Test
        void test_searchPathTo4() {
            Pawn pawn = Pawn.from(Color.WHITE);

            assertThatThrownBy(() ->
                    pawn.searchPathTo(INITIAL_POSITION, B3, Pawn.from(Color.WHITE)))
                    .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("초기 위치가 아니면 2칸 이동할 수 없다.")
        @Test
        void test_searchPathTo5() {
            Pawn pawn = Pawn.from(Color.WHITE);

            assertThatThrownBy(() ->
                    pawn.searchPathTo(B4, B6, null))
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}
