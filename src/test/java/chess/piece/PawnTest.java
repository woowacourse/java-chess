package chess.piece;

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

    private static final Position INITIAL_POSITION = new Position(1, 2);

    @Nested
    class searchPathTo {

        @DisplayName("초기 위치에서 1칸 앞으로 전진할 수 있다..")
        @Test
        void test_searchPathTo() {

            Pawn pawn = new Pawn(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, new Position(1, 3), null);

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @DisplayName("초기 위치에서는 2칸 앞으로 전진할 수 있다.")
        @Test
        void test_searchPathTo2() {

            Pawn pawn = new Pawn(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, new Position(1, 4), null);

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(new Position(1, 3));
        }

        @DisplayName("대각선으로 이동하는 경우, 다른 색 말이 도착지에 있으면 이동할 수 있다.")
        @Test
        void test_searchPathTo3() {
            Position to = new Position(2, 3);
            Pawn pawn = new Pawn(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, to, new Pawn(Color.BLACK));

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @DisplayName("대각선으로 이동하는 경우, 같은 색 말이 도착지에 있으면 이동할 수 없다.")
        @Test
        void test_searchPathTo4() {
            Position to = new Position(2, 3);
            Pawn pawn = new Pawn(Color.WHITE);

            assertThatThrownBy(() ->
                    pawn.searchPathTo(INITIAL_POSITION, to, new Pawn(Color.WHITE)))
                    .isInstanceOf(IllegalStateException.class);
        }

        @DisplayName("초기 위치가 아니면 2칸 이동할 수 없다.")
        @Test
        void test_searchPathTo5() {
            Pawn pawn = new Pawn(Color.WHITE);

            assertThatThrownBy(() ->
                    pawn.searchPathTo(new Position(2, 4), new Position(2, 6), null))
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}
