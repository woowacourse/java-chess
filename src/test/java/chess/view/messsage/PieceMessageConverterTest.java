package chess.view.messsage;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceMessageConverterTest {

    @ParameterizedTest(name = "convert()는 {0} 기물의 {1} 진형인 경우 메세지를 {2}로 변환한다")
    @DisplayName("convert() 테스트")
    @CsvSource(value = {
            "chess.model.piece.type.Bishop:BLACK:B", "chess.model.piece.type.Bishop:WHITE:b",
            "chess.model.piece.type.InitialPawn:BLACK:P", "chess.model.piece.type.InitialPawn:WHITE:p",
            "chess.model.piece.type.King:BLACK:K", "chess.model.piece.type.King:WHITE:k",
            "chess.model.piece.type.Knight:BLACK:N", "chess.model.piece.type.Knight:WHITE:n",
            "chess.model.piece.type.Pawn:BLACK:P", "chess.model.piece.type.Pawn:WHITE:p",
            "chess.model.piece.type.Queen:BLACK:Q", "chess.model.piece.type.Queen:WHITE:q",
            "chess.model.piece.type.Rook:BLACK:R", "chess.model.piece.type.Rook:WHITE:r",
    }, delimiter = ':')
    void convert_givenPieceTypeAndCamp_thenReturnPieceMessage(
            final Class<? extends Piece> pieceType,
            final Camp camp,
            final String expected
    ) {
        // when
        final String actual = PieceMessageConverter.convert(pieceType, camp);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
