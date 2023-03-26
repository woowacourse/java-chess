package chess.domain.piece;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:true", "B:THREE:true", "A:TWO:false", "B:ONE:false", "C:THREE:false",
            "C:TWO:false"}, delimiter = ':')
    void 하얀_폰이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Team.WHITE, B_1);
        assertThat(pawn.canMove(new Position(fileCoordinate, rankCoordinate), Team.EMPTY)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"B:FIVE:true", "B:SIX:true", "B:FOUR:false", "C:FIVE:false", "A:SIX:false",
            "C:TWO:false"}, delimiter = ':')
    void 검은_폰이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Team.BLACK, B_7);
        assertThat(pawn.canMove(new Position(fileCoordinate, rankCoordinate), Team.EMPTY)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"A:TWO:true", "C:TWO:true"}, delimiter = ':')
    void 폰은_대각선에_적이_있으면_그_방향으로_이동할_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Team.WHITE, B_1);
        assertThat(pawn.canMove(new Position(fileCoordinate, rankCoordinate), Team.BLACK)).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"A:TWO:false", "C:TWO:false"}, delimiter = ':')
    void 폰은_대각선에_적이_없다면_그_방향으로_이동할_수_없다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Team.WHITE, B_1);
        assertThat(pawn.canMove(new Position(fileCoordinate, rankCoordinate), Team.WHITE)).isEqualTo(expect);
    }

    @Test
    void 폰은_직선방향에_적이_있어도_그_방향으로_이동할_수_없다() {
        Pawn pawn = new Pawn(Team.WHITE, B_2);
        assertThat(pawn.canMove(B_3, Team.BLACK)).isFalse();
    }

    @Test
    void Empty인지_알_수_있다() {
        final var piece = new Pawn(Team.WHITE, B_2);
        assertThat(piece.isEmpty()).isFalse();
    }

    @Test
    void 폰은_대각선에_상대방이_있을_때_움직일_수_있다_move() {
        final var piece = new Pawn(Team.WHITE, B_1);
        assertThat(piece.move(new Position(FileCoordinate.A, RankCoordinate.TWO), Team.WHITE, Team.BLACK))
                .isInstanceOf(Pawn.class);
    }

    @Test
    void 폰은_직선이_비어있을_때_움직일_수_있다_move() {
        final var piece = new Pawn(Team.WHITE, B_1);
        assertThat(piece.move(new Position(FileCoordinate.B, RankCoordinate.TWO), Team.WHITE, Team.EMPTY))
                .isInstanceOf(Pawn.class);
    }
}
