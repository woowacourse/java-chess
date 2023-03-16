package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Path;
import chess.Position;
import java.util.Optional;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Nested
    class searchPathTo {

        @Test
        void test_searchPathTo() {

            Pawn pawn = new Pawn(Color.WHITE, new Position(1, 2));

            Path path = pawn.searchPathTo(new Position(1, 3), Optional.empty());

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @Test
        void test_searchPathTo2() {

            Pawn pawn = new Pawn(Color.WHITE, new Position(1, 2));

            Path path = pawn.searchPathTo(new Position(1, 4), Optional.empty());

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(new Position(1, 3));
        }

        @Test
        void test_searchPathTo3() {
            Position to = new Position(2, 3);
            Pawn pawn = new Pawn(Color.WHITE, new Position(1, 2));

            Path path = pawn.searchPathTo(to, Optional.of(new Pawn(Color.BLACK, to)));

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @Test
        void test_searchPathTo4() {
            Position to = new Position(2, 3);
            Pawn pawn = new Pawn(Color.WHITE, new Position(1, 2));

            assertThatThrownBy(() ->
                    pawn.searchPathTo(to, Optional.of(new Pawn(Color.WHITE, to))))
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}
