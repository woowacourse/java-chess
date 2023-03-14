package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PieceTest {
    @Test
    void 피스_이름_가져오기_테스트() {
        //given
        Piece piece = new Piece("p", Color.WHITE);

        //when
        PieceType result = piece.getType();

        //then
        assertThat(result).isEqualTo(PieceType.PAWN);
    }

}
