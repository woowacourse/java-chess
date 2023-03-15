package chess.domain.piece;

import static chess.domain.board.FileCoordinate.B;
import static chess.domain.board.RankCoordinate.ONE;
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
class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:true", "B:THREE:true", "A:TWO:false", "B:ONE:false", "C:THREE:true"}, delimiter = ':')
    void 폰이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Color.WHITE);
        Position sourcePosition = new Position(B, ONE);
        assertThat(pawn.canMove(sourcePosition, new Position(fileCoordinate, rankCoordinate))).isEqualTo(expect);
    }
}
