package chess.domain.piece.type;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class BishopTest {

    private final Bishop bishop = new Bishop(Color.BLACK);

    @ParameterizedTest
    @CsvSource(value = {"D, FIVE" , "D, THREE", "C, FOUR","E,FOUR"})
    void Bishop이_갈수없는_방향으로_이동하려고_하면_예외(Column endColumn, Rank endRank) {
        Position start = Position.of(Column.D, Rank.FOUR);
        Position end = Position.of(endColumn, endRank);
        Piece toMove = EmptyPiece.of();

        assertThat(bishop.isMovable(start, end, toMove))
                .isFalse();

    }

    @Test
    void 도착점에_기물의_색깔이_아군인_경우_예외() {
        Position start = Position.of(Column.D, Rank.FOUR);
        Position end = Position.of(Column.C, Rank.FIVE);
        Piece toMove = new Pawn(Color.BLACK);

        assertThat(bishop.isMovable(start, end, toMove))
                .isFalse();

    }

    @Test
    void 모든_이동_조건을_충족하면_true를_반환한다() {
        Position start = Position.of(Column.D, Rank.FOUR);
        Position end = Position.of(Column.C, Rank.FIVE);
        Piece toMove = EmptyPiece.of();

        assertThat(bishop.isMovable(start, end, toMove))
                .isTrue();

    }

}