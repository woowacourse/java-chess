package chess.view.messsage;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.piece.type.Bishop;
import chess.model.piece.type.InitialPawn;
import chess.model.piece.type.King;
import chess.model.piece.type.Knight;
import chess.model.piece.type.Pawn;
import chess.model.piece.type.Queen;
import chess.model.piece.type.Rook;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceMessageConverterTest {

    @ParameterizedTest(name = "convert()는 {0} 기물의 {1} 진형인 경우 메세지를 {2}로 변환한다")
    @DisplayName("convert() 테스트")
    @MethodSource("provideConverterArguments")
    void convert_givenPieceTypeAndCamp_thenReturnPieceMessage(final Class<? extends Piece> pieceType, final Camp camp,
            final String expected) {
        // when
        final String actual = PieceMessageConverter.convert(pieceType, camp);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideConverterArguments() {
        return Stream.of(
                Arguments.of(Bishop.class, Camp.BLACK, "B"), Arguments.of(Bishop.class, Camp.WHITE, "b"),
                Arguments.of(InitialPawn.class, Camp.BLACK, "P"), Arguments.of(InitialPawn.class, Camp.WHITE, "p"),
                Arguments.of(King.class, Camp.BLACK, "K"), Arguments.of(King.class, Camp.WHITE, "k"),
                Arguments.of(Knight.class, Camp.BLACK, "N"), Arguments.of(Knight.class, Camp.WHITE, "n"),
                Arguments.of(Pawn.class, Camp.BLACK, "P"), Arguments.of(Pawn.class, Camp.WHITE, "p"),
                Arguments.of(Queen.class, Camp.BLACK, "Q"), Arguments.of(Queen.class, Camp.WHITE, "q"),
                Arguments.of(Rook.class, Camp.BLACK, "R"), Arguments.of(Rook.class, Camp.WHITE, "r")
        );
    }
}
