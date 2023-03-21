package chess.domain.piece;

import static chess.domain.PositionFixture.B_1;
import static chess.domain.PositionFixture.B_7;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.FileCoordinate;
import chess.domain.position.Position;
import chess.domain.position.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:true", "B:THREE:true", "A:TWO:false", "B:ONE:false", "C:THREE:false",
            "C:TWO:false"}, delimiter = ':')
    void 하얀_폰이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThat(pawn.canMove(B_1, new Position(fileCoordinate, rankCoordinate), Color.EMPTY)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"B:FIVE:true", "B:SIX:true", "B:FOUR:false", "C:FIVE:false", "A:SIX:false",
            "C:TWO:false"}, delimiter = ':')
    void 검은_폰이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Color.BLACK);

        assertThat(pawn.canMove(B_7, new Position(fileCoordinate, rankCoordinate), Color.EMPTY)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"A:TWO:true", "C:TWO:true"}, delimiter = ':')
    void 폰은_대각선에_적이_있으면_그_방향으로_이동할_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThat(pawn.canMove(B_1, new Position(fileCoordinate, rankCoordinate), Color.BLACK)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"A:TWO:false", "C:TWO:false"}, delimiter = ':')
    void 폰은_대각선에_적이_없다면_그_방향으로_이동할_수_없다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThat(pawn.canMove(B_1, new Position(fileCoordinate, rankCoordinate), Color.WHITE)).isEqualTo(expect);
    }

    @Test
    void 폰은_Empty가_아니다() {
        Pawn pawn = new Pawn(Color.WHITE);

        assertThat(pawn.isEmpty()).isEqualTo(false);
    }
}
