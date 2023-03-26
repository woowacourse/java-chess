package chess.domain.winningstatus;

import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import chess.domain.piece.state.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreTest {
    private static final Rook ROOK_BLACK = new Rook(Team.BLACK);
    private static final Knight KNIGHT_BLACK = new Knight(Team.BLACK);
    private static final Bishop BISHOP_BLACK = new Bishop(Team.BLACK);
    private static final Queen QUEEN_BLACK = new Queen(Team.BLACK);
    private static final King KING_BLACK = new King(Team.BLACK);
    private static final Pawn PAWN_BLACK = new Pawn(Team.BLACK);

    @Test
    void 점수_계산_더블폰이_없는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 0);

        assertThat(score.getScore()).isEqualTo(38);
    }

    @Test
    void 점수_계산_더블폰이_있는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 2);

        assertThat(score.getScore()).isEqualTo(37);
    }

    @Test
    void 점수_계산_폰이_한개_없는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 0);

        assertThat(score.getScore()).isEqualTo(37);
    }

    @Test
    void 점수_계산_룩이_한개_없는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 0);

        assertThat(score.getScore()).isEqualTo(33);
    }

    @Test
    void 점수_계산_나이트가_한개_없는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, BISHOP_BLACK, QUEEN_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 0);

        assertThat(score.getScore()).isEqualTo(35.5);
    }

    @Test
    void 점수_계산_비숍이_한개_없는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, KNIGHT_BLACK, QUEEN_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 0);

        assertThat(score.getScore()).isEqualTo(35);
    }

    @Test
    void 점수_계산_퀸이_없는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 0);

        assertThat(score.getScore()).isEqualTo(29);
    }

    @Test
    void 점수_계산_킹이_없는경우() {
        List<SquareState> pices = List.of(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK,
                BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
        Score score = new Score(pices, 0);

        assertThat(score.getScore()).isEqualTo(38);
    }
}