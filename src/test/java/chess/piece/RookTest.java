package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.path.Path;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo() {
        Piece Rook = new Rook(Color.WHITE);

        Position initialPosition = new Position(5, 1);
        Path path = Rook.searchPathTo(initialPosition, new Position(5, 8), null);

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
        Rook Rook = new Rook(Color.WHITE);

        Position initialPosition = new Position(5, 5);
        Path path = Rook.searchPathTo(initialPosition, new Position(5, 1), null);

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly(
                        new Position(5, 4),
                        new Position(5, 3),
                        new Position(5, 2));
    }

    @DisplayName("비정상 경로를 받으면 예외 처리한다.")
    @Test
    void test_searchPathTo3() {
        Rook Rook = new Rook(Color.WHITE);

        Position initialPosition = new Position(5, 5);

        assertThatThrownBy(
                () -> Rook.searchPathTo(initialPosition,
                        new Position(5, 1),
                        new King(Color.WHITE)))
                .isInstanceOf(IllegalStateException.class);
    }
}
