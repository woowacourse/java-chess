package chess.model.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceFactoryTest {

    @ParameterizedTest(name = "create()는 {0}을 건네주면 {1}의 타입의 기물을 반환한다")
    @DisplayName("create() 성공 테스트")
    @MethodSource("providePieceClass")
    void create_givenPieceType_thenReturnPieceInstance(
            final Class<? extends Piece> pieceType,
            final Class<? extends Piece> expected
    ) {
        // when
        final Piece actual = PieceFactory.create(pieceType, Camp.BLACK);

        // then
        assertThat(actual)
                .isInstanceOf(Piece.class)
                .isExactlyInstanceOf(expected);
    }

    private static Stream<Arguments> providePieceClass() {
        return Stream.of(
                Arguments.of(Bishop.class, Bishop.class), Arguments.of(Pawn.class, InitialPawn.class),
                Arguments.of(King.class, King.class), Arguments.of(Knight.class, Knight.class),
                Arguments.of(Queen.class, Queen.class), Arguments.of(Rook.class, Rook.class)
        );
    }

    @Test
    @DisplayName("create()는 생성할 수 없는 기물 Empty 클래스를 전달하면 예외가 발생한다")
    void create_givenEmptyClass_thenFail() {
        // when, then
        assertThatThrownBy(() -> PieceFactory.create(Empty.class, Camp.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("존재하지 않는 기물입니다.");
    }
}
