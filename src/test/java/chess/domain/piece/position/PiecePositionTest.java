package chess.domain.piece.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PiecePosition 은")
class PiecePositionTest {

    @Test
    void Rank_와_File_을_받아_생성된다() {
        // when & then
        assertDoesNotThrow(() -> PiecePosition.of(1, 'a'));
    }

    @ParameterizedTest
    @CsvSource({
            "1,a",
            "3,h",
            "3,g",
            "4,c"
    })
    void 값이_같으면_동등하다(final int rank, final char file) {
        // given
        final PiecePosition position1 = PiecePosition.of(rank, file);
        final PiecePosition position2 = PiecePosition.of(rank, file);

        // when & then
        assertThat(position1).isEqualTo(position2);
    }
}
