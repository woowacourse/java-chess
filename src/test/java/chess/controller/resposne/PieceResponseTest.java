package chess.controller.resposne;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class PieceResponseTest {

    @Test
    void from_으로_생성하면_타입_이름과_색깔을_문자열로_변환한다() {
        //given
        Piece piece = PieceFactory.getInstance(PieceType.PAWN, Color.WHITE);

        //when
        PieceResponse pieceResponse = PieceResponse.from(piece);

        //then
        assertAll(
                () -> assertThat(pieceResponse.getType()).isEqualTo("p"),
                () -> assertThat(pieceResponse.getColor()).isEqualTo("WHITE")
        );
    }
}
