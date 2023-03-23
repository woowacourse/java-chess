package chess.domain.dto;

import chess.domain.Color;
import chess.domain.piece.PawnPiece;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceDtoTest {
    @Test
    void Test() {
        //given
        PawnPiece pawnPiece = new PawnPiece(Color.WHITE);
        PieceDto.from(pawnPiece);
        //when
        //then
    }

}
