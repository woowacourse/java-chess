package chess.domain.piece.move;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.strategy.pawn.WhitePawnMoveStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("WhitePawnMoveStrategy 은")
class WhitePawnMoveStrategyTest {

    @ParameterizedTest(name = "북쪽을 향해 [직진 2칸] 혹은 [직진 1칸] 혹은 [대각선 1칸] 이동할 수 있다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 이동가능하다")
    @CsvSource({
            "b2,b3",
            "b2,b4",
            "b2,a3",
            "b2,c3",
    })
    void 북쪽으로_이동할_수_있다(final PiecePosition from, final PiecePosition to) {
        // given
        final WhitePawnMoveStrategy whitePawnMoveStrategy = new WhitePawnMoveStrategy();
        final Path path = Path.of(from, to);
        ;

        // when & then
        assertThat(whitePawnMoveStrategy.movable(path)).isTrue();
    }

    @ParameterizedTest(name = "이외의 경우 오류이다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 불가능하다.")
    @CsvSource({
            "b2,a2",
            "b2,c2",
            "b2,b1",
            "b2,a1",
            "b2,c1",
            "b2,b5",
            "b2,c4",
    })
    void 이외의_경우_오류(final PiecePosition from, final PiecePosition to) {
        // given
        final WhitePawnMoveStrategy whitePawnMoveStrategy = new WhitePawnMoveStrategy();
        final Path path = Path.of(from, to);

        // when & then
        assertThat(whitePawnMoveStrategy.movable(path)).isFalse();
    }
}
