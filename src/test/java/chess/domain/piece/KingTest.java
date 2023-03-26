package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.PositionFixture.C_4;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"B:FIVE:true", "D:THREE:true", "E:SIX:false", "C:FOUR:false"}, delimiter = ':')
    void 킹은_상하좌우_대각선으로_이동할_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        King king = new King(Team.WHITE, C_4);

        assertThat(king.canMove(new Position(fileCoordinate, rankCoordinate), Team.EMPTY)).isEqualTo(expect);
    }

    @Test
    void Empty인지_알_수_있다() {
        final var piece = new King(Team.WHITE, C_4);
        assertThat(piece.isEmpty()).isFalse();
    }

    @Test
    void 킹을_움직일_수_있다() {
        final var piece = new King(Team.WHITE, C_4);
        assertThat(piece.move(new Position(FileCoordinate.B, RankCoordinate.FIVE), Team.WHITE, Team.EMPTY))
                .isInstanceOf(King.class);
    }
}
