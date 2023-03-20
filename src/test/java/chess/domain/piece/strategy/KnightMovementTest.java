package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("KnightMovementStrategy 은")
class KnightMovementTest {

    private final PieceMovementStrategy movement = new KnightMovementStrategy();
    private final PiecePosition source = PiecePosition.of("e4");

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("[Rank 2칸 & File 1칸] 혹은 [Rank 1칸 & File 2칸] 움직이는 경우")
    class movablePath {

        @ParameterizedTest(name = "움직일 수 있다. [e4] -> [{0}]")
        @CsvSource({
                "f6",
                "f2",
                "d6",
                "d2",
                "g5",
                "g3",
                "c5",
                "c3"
        })
        void 움직일_수_있다(final PiecePosition destination) {
            // given
            final Path path = Path.of(source, destination);

            // when & then
            assertDoesNotThrow(() -> movement.validateMove(Color.WHITE, path, null));
        }

        @ParameterizedTest(name = "경유지는 없다.")
        @CsvSource({
                "f6",
                "f2",
                "d6",
                "d2",
                "g5",
                "g3",
                "c5",
                "c3"
        })
        void 경유지는_없다(final PiecePosition destination) {
            // given
            final Path path = Path.of(source, destination);

            // when & then
            assertThat(movement.waypoints(Color.WHITE, path, null)).isEmpty();
        }
    }

    @Nested
    class 나머지_경로에_대해서는 {

        @ParameterizedTest(name = "움직일 수 없다. [e4] -> [{0}]")
        @CsvSource({
                "e5",
                "e3",
                "e7",
                "e1",
                "a4",
                "b4",
                "c4",
        })
        void 움직일_수_없다(final PiecePosition destination) {
            // given
            final Path path = Path.of(source, destination);

            // when & then
            assertThatThrownBy(() -> movement.validateMove(Color.WHITE, path, null));
        }

        @ParameterizedTest(name = "경유지를 조회하면 예외. [e4] -> [{0}]")
        @CsvSource({
                "e5",
                "e3",
                "e7",
                "e1",
                "a4",
                "b4",
                "c4",
        })
        void 경유지를_조회하면_예외(final PiecePosition destination) {
            // given
            final Path path = Path.of(source, destination);

            // when & then
            assertThatThrownBy(() -> movement.waypoints(Color.WHITE, path, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 아군을_죽일_수_없다() {
        // given
        final PiecePosition dest = PiecePosition.of("f6");
        final Path path = Path.of(source, dest);
        final Piece ally = new Piece(Color.BLACK, dest, new RookMovementStrategy());

        // when & then
        assertThatThrownBy(() -> movement.validateMove(Color.BLACK, path, ally))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 적군을_죽일_수_있다() {
        // given
        final PiecePosition dest = PiecePosition.of("f6");
        final Path path = Path.of(source, dest);
        final Piece enemy = new Piece(Color.BLACK, dest, new RookMovementStrategy());

        // when & then
        assertDoesNotThrow(() -> movement.validateMove(Color.WHITE, path, enemy));
    }
}
