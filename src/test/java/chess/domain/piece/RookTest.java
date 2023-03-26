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
class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"B:SIX:false", "A:FOUR:true", "C:SIX:true", "C:FOUR:false"}, delimiter = ':')
    void 룩은_상하좌우로_이동할_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Rook rook = new Rook(Team.WHITE, C_4);

        assertThat(rook.canMove(new Position(fileCoordinate, rankCoordinate), Team.EMPTY)).isEqualTo(expect);
    }

    @Test
    void Empty인지_알_수_있다() {
        final var piece = new Rook(Team.WHITE, C_4);
        assertThat(piece.isEmpty()).isFalse();
    }

    @Test
    void 룩을_움직일_수_있다() {
        final var piece = new Rook(Team.WHITE, C_4);
        assertThat(piece.move(new Position(FileCoordinate.A, RankCoordinate.FOUR), Team.WHITE, Team.EMPTY))
                .isInstanceOf(Rook.class);
    }
}
