package chess.domain.piece.movestrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
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
@DisplayName("KingMovementStrategy 은")
class KingMovementTest {

    private final PieceMovementStrategy movement = new KingMovementStrategy();
    private final PiecePosition source = PiecePosition.of("e4");

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 어드_방향이든_한_칸_움직이는_경우 {

        @ParameterizedTest(name = "움직일 수 있다. [e4] -> [{0}]")
        @CsvSource({
                "e3",
                "e5",
                "d4",
                "f4",
                "d3",
                "d5",
                "f3",
                "f5",
        })
        void 움직일_수_있다(final PiecePosition destination) {
            // when & then
            assertDoesNotThrow(() -> movement.validateMove(source, destination, null));
        }

        @ParameterizedTest(name = "경유지는 없다.")
        @CsvSource({
                "e3",
                "e5",
                "d4",
                "f4",
                "d3",
                "d5",
                "f3",
                "f5",
        })
        void 경유지는_없다(final PiecePosition destination) {
            // when & then
            assertThat(movement.waypoints(source, destination, null)).isEmpty();
        }
    }

    @Nested
    class 한_칸을_초과하여_움직이는_경우 {

        @ParameterizedTest(name = "움직일 수 없다. [e4] -> [{0}]")
        @CsvSource({
                "e2",
                "e6",
                "c4",
                "g4",
                "c2",
                "c6",
                "g2",
                "g6",
        })
        void 움직일_수_없다(final PiecePosition destination) {
            // when & then
            assertThatThrownBy(() -> movement.validateMove(source, destination, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest(name = "경유지를 조회하면 예외. [e4] -> [{0}]")
        @CsvSource({
                "e2",
                "e6",
                "c4",
                "g4",
                "c2",
                "c6",
                "g2",
                "g6",
        })
        void 경유지를_조회하면_예외(final PiecePosition destination) {
            // when & then
            assertThatThrownBy(() -> movement.waypoints(source, destination, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 적군을_죽일_수_있다() {
        // given
        final PiecePosition dest = PiecePosition.of("e3");
        final Piece enemy = new Piece(Color.BLACK, dest, new RookMovementStrategy());

        // when & then
        assertDoesNotThrow(() -> movement.validateMove(source, dest, enemy));
    }

    @Test
    void 왕이다() {
        // when & then
        assertThat(movement.isKing()).isTrue();
    }
}
