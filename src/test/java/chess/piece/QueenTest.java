package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.path.Path;
import chess.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo() {

        Piece queen = new Queen(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = queen.searchPathTo(initialPosition, new Position(5, 8), null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(5, 2), new Position(5, 3),
                        new Position(5, 4), new Position(5, 5),
                        new Position(5, 6), new Position(5, 7));
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo2() {

        Queen queen = new Queen(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = queen.searchPathTo(initialPosition, new Position(8, 4), null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(new Position(6, 2), new Position(7, 3));
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo3() {

        Queen queen = new Queen(Color.WHITE);

        Position initialPosition = new Position(5, 5);
        Path path = queen.searchPathTo(initialPosition, new Position(5, 1), null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(5, 4),
                        new Position(5, 3),
                        new Position(5, 2));
    }

    @DisplayName("비정상 경로를 받으면 예외 처리한다.")
    @Test
    void test_searchPathTo4() {

        Queen queen = new Queen(Color.WHITE);

        Position initialPosition = new Position(5, 5);

        assertThatThrownBy(
                () -> queen.searchPathTo(initialPosition,
                        new Position(5, 1),
                        new King(Color.WHITE)))
                .isInstanceOf(IllegalStateException.class);
    }
}
