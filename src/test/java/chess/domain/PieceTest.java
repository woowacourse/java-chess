package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

    @ParameterizedTest
    @CsvSource(value = {"BLACK, -2", "WHITE, 2"})
    void 폰일_경우_처음에_2칸을_움직일_수_있다(Color color, int yChange) {
        //given
        Piece piece = new Piece(PieceType.PAWN, color);
        Piece empty = Piece.empty();

        //expect
        assertDoesNotThrow(() -> piece.move(0, yChange, empty));
    }
}
