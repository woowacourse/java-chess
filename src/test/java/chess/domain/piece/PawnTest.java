package chess.domain.piece;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    private static final Position INITIAL_POSITION = new Position(1, 2);

    @Nested
    class searchPathTo {

        @Test
        void test_searchPathTo() {

            Pawn pawn = new Pawn(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, new Position(1, 3), Optional.empty());

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @Test
        void test_searchPathTo2() {

            Pawn pawn = new Pawn(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, new Position(1, 4), Optional.empty());

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(new Position(1, 3));
        }

        @Test
        void test_searchPathTo3() {
            Position to = new Position(2, 3);
            Pawn pawn = new Pawn(Color.WHITE);

            Path path = pawn.searchPathTo(INITIAL_POSITION, to, Optional.of(new Pawn(Color.BLACK)));

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly();
        }

        @Test
        void test_searchPathTo4() {
            Position to = new Position(2, 3);
            Pawn pawn = new Pawn(Color.WHITE);

            assertThatThrownBy(() ->
                                       pawn.searchPathTo(INITIAL_POSITION, to, Optional.of(new Pawn(Color.WHITE))))
                    .isInstanceOf(IllegalStateException.class);
        }

        @Test
        void test_searchPathTo5() {
            Position from = new Position(2, 5);
            Position to = new Position(3, 4);
            Piece piece = new Pawn(Color.BLACK);

            Path path = piece.searchPathTo(from, to, Optional.of(new Pawn(Color.WHITE)));
        }
    }
}
