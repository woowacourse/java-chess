package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.helper.CommonAttackEvaluator;
import chess.model.position.Distance;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTypeTest extends CommonAttackEvaluator {

    @ParameterizedTest(name = "PieceType.{0}은 PieceType.{0}를 건네주면 true를 반환한다.")
    @DisplayName("isSameType() 테스트")
    @MethodSource("provideIsSameTypeArguments")
    void isSameType_givenSamePieceType(final PieceType pieceType) {
        // when
        final boolean actual = pieceType.isSamePieceType(pieceType);

        // then
        assertThat(actual).isTrue();
    }

    private static Stream<Arguments> provideIsSameTypeArguments() {
        return Stream.of(
                Arguments.of(PieceType.KING), Arguments.of(PieceType.QUEEN), Arguments.of(PieceType.BISHOP),
                Arguments.of(PieceType.ROOK), Arguments.of(PieceType.KNIGHT), Arguments.of(PieceType.INITIAL_PAWN),
                Arguments.of(PieceType.PAWN), Arguments.of(PieceType.EMPTY)
        );
    }

    @Test
    @DisplayName("PieceType.empty.movable()은 호출하면 false를 반환한다.")
    void movable_whenCall_thenReturnFalse() {
        // given
        final Distance distance = new Distance(1, 1);

        // when
        final boolean actual = PieceType.EMPTY.movable(distance, blackEmptyEvaluator);

        // then
        assertThat(actual).isFalse();
    }
}
