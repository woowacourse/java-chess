package chess.domain.piece.move;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("WhitePawnMoveStrategy 은")
class WhitePawnMoveStrategyTest {

    @ParameterizedTest(name = "북쪽으로 이동할 수 있다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 이동가능하다")
    @CsvSource({
            "b2,b3",
            "b2,b4",
            "b2,a3",
            "b2,c7",
            "b2,c5",
            "b2,c8",
    })
    void 북쪽으로_이동할_수_있다(final PiecePosition from, final PiecePosition to) {
        // given
        final WhitePawnMoveStrategy whitePawnMoveStrategy = new WhitePawnMoveStrategy();
        final Path path = Path.of(from, to);
        ;

        // when & then
        assertDoesNotThrow(() -> whitePawnMoveStrategy.validateMovementDirection(path));
    }

    @ParameterizedTest(name = "북쪽이 아닌 경우 오류이다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 불가능하다.")
    @CsvSource({
            "b2,a2",
            "b2,c2",
            "b2,b1",
            "b2,a1",
            "b2,c1",
    })
    void 북쪽이_아닌_경우_오류(final PiecePosition from, final PiecePosition to) {
        // given
        final WhitePawnMoveStrategy whitePawnMoveStrategy = new WhitePawnMoveStrategy();
        final Path path = Path.of(from, to);

        // when & then
        assertThatThrownBy(() -> whitePawnMoveStrategy.validateMovementDirection(path))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
