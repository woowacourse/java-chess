package chess.piece;

import static chess.position.InitialPositionFixtures.WHITE_KING_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.path.Path;
import chess.position.Position;
import java.util.Optional;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo() {

        Piece piece = new King(Color.WHITE);

        Position initialPosition = new Position(6, 1);

        assertThatThrownBy(
                () -> piece.searchPathTo(initialPosition,
                        new Position(8, 1),
                        Optional.of(new King(Color.BLACK))))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo2() {

        Piece piece = new King(Color.WHITE);

        Path path = piece.searchPathTo(WHITE_KING_POSITION, new Position(5, 2), Optional.empty());

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }

    @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
    @Test
    void test_searchPathTo3() {

        Piece piece = new King(Color.WHITE);

        Path path = piece.searchPathTo(WHITE_KING_POSITION, new Position(5, 2), Optional.of(new Queen(Color.BLACK)));

        assertThat(path)
                .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                .containsExactly();
    }
}
