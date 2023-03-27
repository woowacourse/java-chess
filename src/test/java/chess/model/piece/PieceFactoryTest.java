package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceFactoryTest {

    @ParameterizedTest(name = "create()는 {0}을 건네주면 {0} 타입의 기물을 반환한다")
    @DisplayName("create() 성공 테스트")
    @MethodSource("providePieceType")
    void create_givenPieceType_thenReturnPieceInstance(final PieceType pieceType, final PieceType expected) {
        // when
        final Piece actual = PieceFactory.create(pieceType, Camp.BLACK);

        // then
        assertThat(actual).isInstanceOf(Piece.class);
        assertThat(actual.isSameType(expected)).isTrue();
    }

    private static Stream<Arguments> providePieceType() {
        return Stream.of(
                Arguments.of(PieceType.BISHOP, PieceType.BISHOP), Arguments.of(PieceType.PAWN, PieceType.INITIAL_PAWN),
                Arguments.of(PieceType.KING, PieceType.KING), Arguments.of(PieceType.KNIGHT, PieceType.KNIGHT),
                Arguments.of(PieceType.QUEEN, PieceType.QUEEN), Arguments.of(PieceType.ROOK, PieceType.ROOK)
        );
    }

    @Test
    @DisplayName("create()는 생성할 수 없는 기물 PieceType.Empty 타입을 전달하면 예외가 발생한다")
    void create_givenEmptyClass_thenFail() {
        // when, then
        assertThatThrownBy(() -> PieceFactory.create(PieceType.EMPTY, Camp.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("존재하지 않는 기물입니다.");
    }
}
