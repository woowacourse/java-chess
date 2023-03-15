package chess.domain.piece;

import static chess.domain.board.FileCoordinate.C;
import static chess.domain.board.RankCoordinate.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BishopTest {

    private static final Position C_4 = new Position(C, FOUR);

    @ParameterizedTest
    @CsvSource(value = {"A:TWO:true", "E:SIX:true", "B:TWO:false", "C:FOUR:false"}, delimiter = ':')
    void 비숍이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Bishop bishop = new Bishop(Color.WHITE);
        assertThat(bishop.canMove(C_4, new Position(fileCoordinate, rankCoordinate))).isEqualTo(expect);
    }
}
