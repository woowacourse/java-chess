package chess.piece;

import static chess.InitialPositionFixtures.BLACK_BISHOP_RIGHT_POSITION;
import static chess.InitialPositionFixtures.WHITE_BISHOP_LEFT_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Path;
import chess.Position;
import java.util.Optional;
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
            Piece bishop = new Bishop(Color.WHITE);
            Path path = bishop.searchPathTo(WHITE_BISHOP_LEFT_POSITION, new Position(7, 5), Optional.empty());

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(
                            new Position(4, 2),
                            new Position(5, 3),
                            new Position(6, 4));
        }

        @DisplayName("정상 위치로 이동 시 경로를 반환할 수 있다.")
        @Test
        void test_searchPathTo2() {

            Bishop bishop = new Bishop(Color.BLACK);

            Path path = bishop.searchPathTo(BLACK_BISHOP_RIGHT_POSITION, new Position(1, 3), Optional.empty());

            assertThat(path)
                    .extracting("positions", InstanceOfAssertFactories.list(Position.class))
                    .containsExactly(
                            new Position(5, 7),
                            new Position(4, 6),
                            new Position(3, 5),
                            new Position(2, 4));
        }
    }

    @DisplayName("비정상 경로를 받으면 예외 처리한다.")
    @Test
    void test_searchPathTo4() {

        Bishop bishop = new Bishop(Color.WHITE);

        assertThatThrownBy(
                () -> bishop.searchPathTo(WHITE_BISHOP_LEFT_POSITION,
                        new Position(5, 1),
                        Optional.of(new King(Color.WHITE))))
                .isInstanceOf(IllegalStateException.class);
    }
}
