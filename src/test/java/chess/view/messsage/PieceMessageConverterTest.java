package chess.view.messsage;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Camp;
import chess.model.piece.PieceType;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceMessageConverterTest {

    @ParameterizedTest(name = "convert()는 {0} 기물의 {1} 진형인 경우 메세지를 {2}로 변환한다")
    @DisplayName("convert() 테스트")
    @MethodSource("provideConverterArguments")
    void convert_givenPieceTypeAndCamp_thenReturnPieceMessage(final PieceType pieceType, final Camp camp,
            final String expected) {
        // when
        final String actual = PieceMessageConverter.convert(pieceType, camp);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideConverterArguments() {
        return Stream.of(
                Arguments.of(PieceType.BISHOP, Camp.BLACK, "B"), Arguments.of(PieceType.BISHOP, Camp.WHITE, "b"),
                Arguments.of(PieceType.INITIAL_PAWN, Camp.BLACK, "P"), Arguments.of(PieceType.INITIAL_PAWN, Camp.WHITE, "p"),
                Arguments.of(PieceType.KING, Camp.BLACK, "K"), Arguments.of(PieceType.KING, Camp.WHITE, "k"),
                Arguments.of(PieceType.KNIGHT, Camp.BLACK, "N"), Arguments.of(PieceType.KNIGHT, Camp.WHITE, "n"),
                Arguments.of(PieceType.PAWN, Camp.BLACK, "P"), Arguments.of(PieceType.PAWN, Camp.WHITE, "p"),
                Arguments.of(PieceType.QUEEN, Camp.BLACK, "Q"), Arguments.of(PieceType.QUEEN, Camp.WHITE, "q"),
                Arguments.of(PieceType.ROOK, Camp.BLACK, "R"), Arguments.of(PieceType.ROOK, Camp.WHITE, "r")
        );
    }
}
