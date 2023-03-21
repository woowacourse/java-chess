package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Path;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class PawnTest {

    private static final Position INITIAL_POSITION = new Position(1, 2);

    @Test
    void test_searchPathTo() {

        Pawn pawn = new Pawn(Color.WHITE);

        Path path = pawn.searchPathTo(INITIAL_POSITION, new Position(1, 3), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    @Test
    void test_searchPathTo2() {

        Pawn pawn = new Pawn(Color.WHITE);

        Path path = pawn.searchPathTo(INITIAL_POSITION, new Position(1, 4), Empty.getInstance());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(new Position(1, 3));
    }

    @Test
    void test_searchPathTo3() {
        Position to = new Position(2, 3);
        Pawn pawn = new Pawn(Color.WHITE);

        Path path = pawn.searchPathTo(INITIAL_POSITION, to, new Pawn(Color.BLACK));

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    @Test
    void test_searchPathTo4() {
        Position to = new Position(2, 3);
        Pawn pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() ->
                pawn.searchPathTo(INITIAL_POSITION, to, new Pawn(Color.WHITE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
