package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.PositionFixture.C_4;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"A:TWO:true", "E:SIX:true", "B:TWO:false", "C:FOUR:false"}, delimiter = ':')
    void 비숍이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Bishop bishop = new Bishop(Team.WHITE, C_4);
        assertThat(bishop.canMove(new Position(fileCoordinate, rankCoordinate), Team.EMPTY)).isEqualTo(expect);
    }

    @Test
    void Empty인지_알_수_있다() {
        final var piece = new Bishop(Team.WHITE, C_4);
        assertThat(piece.isEmpty()).isFalse();
    }

    @Test
    void 비숍을_움직일_수_있다() {
        final var piece = new Bishop(Team.WHITE, C_4);
        assertThat(piece.move(new Position(FileCoordinate.A, RankCoordinate.TWO), Team.WHITE, Team.EMPTY))
                .isInstanceOf(Bishop.class);
    }
}
