package chess.domain.piece.move;

import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.type.pawn.move.BlackPawnMoveStrategy;
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

    // TODO 질문입니다 !!
    // 마찬가지로 이게 생겼으면, PawnTest 에서 검정 Pawn 은 남쪽으로 이동 가능한지에 대한 테스트가 필요한가??????
    // 혹은 이미 Pawn에서 남쪽으로 이동할 수 있는 테스트가 있는데, 이곳에 굳이 필요할까?
    @ParameterizedTest(name = "남쪽으로 이동할 수 있다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 이동가능하다")
    @CsvSource({
            "b7,b2",
            "b7,b4",
            "b7,c5",
            "b7,a5",
    })
    void 남쪽으로_이동할_수_있다(final PiecePosition from, final PiecePosition to) {
        // given
        final BlackPawnMoveStrategy blackPawnMoveStrategy = new BlackPawnMoveStrategy();
        final Path path = Path.of(from, to);
        ;

        // when & then
        assertDoesNotThrow(() -> blackPawnMoveStrategy.validateMovementDirection(path));
    }

    @ParameterizedTest(name = "남쪽이 아닌 경우 오류이다. 예를 들어 [{0}] 에서 [{1}] 로의 경로는 불가능하다.")
    @CsvSource({
            "b7,b8",
            "b7,a8",
            "b7,c8",
            "b7,a7",
            "b7,c7",
    })
    void 남쪽이_아닌_경우_오류(final PiecePosition from, final PiecePosition to) {
        // given
        final BlackPawnMoveStrategy blackPawnMoveStrategy = new BlackPawnMoveStrategy();
        final Path path = Path.of(from, to);

        // when & then
        assertThatThrownBy(() -> blackPawnMoveStrategy.validateMovementDirection(path))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
