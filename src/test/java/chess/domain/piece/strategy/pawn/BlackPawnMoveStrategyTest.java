package chess.domain.piece.strategy.pawn;

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
@DisplayName("BlackPawnMoveStrategy 은")
class BlackPawnMoveStrategyTest {

    @ParameterizedTest(name = "남쪽을 향해 [직진 2칸] 혹은 [직진 1칸] 혹은 [대각선 1칸] 이동할 수 있다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 이동가능하다")
    @CsvSource({
            "b7,b6",
            "b7,b5",
            "b7,c6",
            "b7,a6",
    })
    void 남쪽으로_이동할_수_있다(final PiecePosition from, final PiecePosition to) {
        // given
        final BlackPawnMoveStrategy blackPawnMoveStrategy = new BlackPawnMoveStrategy();
        final Path path = Path.of(from, to);

        // when & then
        assertDoesNotThrow(() -> blackPawnMoveStrategy.validatePath(path));
    }

    @ParameterizedTest(name = "이외의 경우 오류이다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 불가능하다.")
    @CsvSource({
            "b7,b8",
            "b7,a8",
            "b7,c8",
            "b7,a7",
            "b7,c7",
            "b7,b4",
            "b7,d5",
    })
    void 이외의_경우_오류(final PiecePosition from, final PiecePosition to) {
        // given
        final BlackPawnMoveStrategy blackPawnMoveStrategy = new BlackPawnMoveStrategy();
        final Path path = Path.of(from, to);

        // when & then
        assertThatThrownBy(() -> blackPawnMoveStrategy.validatePath(path))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
