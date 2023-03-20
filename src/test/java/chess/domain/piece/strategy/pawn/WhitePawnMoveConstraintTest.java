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
@DisplayName("WhitePawnMoveConstraint 은")
class WhitePawnMoveConstraintTest {

    @ParameterizedTest(name = "북쪽을 향해 이동할 수 있다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 이동가능하다")
    @CsvSource({
            "b2,b3",
            "b2,b4",
            "b2,a3",
            "b2,c3",
            "b2,b5",
            "b2,c4",
    })
    void 북쪽으로_이동할_수_있다(final PiecePosition from, final PiecePosition to) {
        // given
        final PawnMoveConstraint whitePawnMoveConstraint = new WhitePawnMoveConstraint();
        final Path path = Path.of(from, to);
        ;

        // when & then
        assertDoesNotThrow(() -> whitePawnMoveConstraint.validateConstraint(path));
    }

    @ParameterizedTest(name = "이외의 경우 오류이다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 불가능하다.")
    @CsvSource({
            "b2,a2",
            "b2,c2",
            "b2,b1",
            "b2,a1",
            "b2,c1",
    })
    void 이외의_경우_오류(final PiecePosition from, final PiecePosition to) {
        // given
        final PawnMoveConstraint whitePawnMoveConstraint = new WhitePawnMoveConstraint();
        final Path path = Path.of(from, to);

        // when & then
        assertThatThrownBy(() -> whitePawnMoveConstraint.validateConstraint(path))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
