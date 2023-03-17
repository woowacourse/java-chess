package chess.domain.piece.state;

import chess.domain.piece.move.BlackPawnMoveStrategy;
import chess.domain.piece.move.WhitePawnMoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("MovedPawn 은")
class MovedPawnTest {

    // TODO 마찬가지로 PawnTest 에 있는데, 이곳에 테스트가 있어야 할까요?
    @Nested
    class 흰색_폰_테스트 {

        @ParameterizedTest(name = "북쪽을 향해 직진 한칸 혹은 대각선으로 한 칸 이동이 가능하다. " +
                "예를 들어 [{0}] 에서 [{1}]로 이동이 가능하다")
        @CsvSource({
                "b2,b3",
                "b2,b3",
                "b2,c3",
        })
        void 북쪽으로_한칸_대각선한칸_이동_가능하다(final PiecePosition from, final PiecePosition destination) {
            // given
            final InitialPawn initialPawn = new InitialPawn(new WhitePawnMoveStrategy());

            // when & then
            assertDoesNotThrow(() -> initialPawn.validateMovable(Path.of(from, destination)));
        }

        @Test
        void 직선인_경우_두칸부터는_이동할_수_없다() {
            // given
            final Path path = Path.of(PiecePosition.of("b2"), PiecePosition.of("b4"));
            final InitialPawn initialPawn = new InitialPawn(new WhitePawnMoveStrategy());

            // when & then
            assertThatThrownBy(() -> initialPawn.validateMovable(path))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 대각선인_경우_두칸부터_이동할_수_없다() {
            // given
            final Path path1 = Path.of(PiecePosition.of("c2"), PiecePosition.of("e4"));
            final Path path2 = Path.of(PiecePosition.of("c2"), PiecePosition.of("a4"));
            final InitialPawn initialPawn = new InitialPawn(new WhitePawnMoveStrategy());

            // when & then
            assertThatThrownBy(() -> initialPawn.validateMovable(path1))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> initialPawn.validateMovable(path2))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 검정색_폰_테스트 {

        @ParameterizedTest(name = "남쪽을 향해 직진 한칸 혹은 대각선으로 한 칸 이동이 가능하다. " +
                "예를 들어 [{0}] 에서 [{1}]로 이동이 가능하다")
        @CsvSource({
                "b7,b6",
                "b7,a6",
                "b7,c6",
        })
        void 남쪽으로_한칸_대각선한칸_이동_가능하다(final PiecePosition from, final PiecePosition destination) {
            // given
            final InitialPawn initialPawn = new InitialPawn(new BlackPawnMoveStrategy());

            // when & then
            assertDoesNotThrow(() -> initialPawn.validateMovable(Path.of(from, destination)));
        }

        @Test
        void 직선인_경우_두칸부터는_이동할_수_없다() {
            // given
            final Path path = Path.of(PiecePosition.of("b7"), PiecePosition.of("b5"));
            final InitialPawn initialPawn = new InitialPawn(new BlackPawnMoveStrategy());

            // when & then
            assertThatThrownBy(() -> initialPawn.validateMovable(path))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 대각선인_경우_두칸부터_이동할_수_없다() {
            // given
            final Path path1 = Path.of(PiecePosition.of("c4"), PiecePosition.of("a1"));
            final Path path2 = Path.of(PiecePosition.of("c4"), PiecePosition.of("e6"));
            final InitialPawn initialPawn = new InitialPawn(new BlackPawnMoveStrategy());

            // when & then
            assertThatThrownBy(() -> initialPawn.validateMovable(path1))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> initialPawn.validateMovable(path2))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
