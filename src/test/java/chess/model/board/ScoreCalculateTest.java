package chess.model.board;

import static chess.model.board.PositionFixture.C5;
import static chess.model.board.PositionFixture.C6;
import static chess.model.board.PositionFixture.D4;
import static chess.model.board.PositionFixture.D5;
import static chess.model.board.PositionFixture.D6;
import static chess.model.board.PositionFixture.D8;
import static chess.model.board.PositionFixture.E4;
import static chess.model.board.PositionFixture.E5;
import static chess.model.board.PositionFixture.F4;
import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreCalculateTest {

    private static final int DEFAULT_MAX_SCORE = 38;

    @Test
    @DisplayName("기본 체스 보드의 점수를 계산한다.")
    void calculateScore_whenCall_thenReturnScore() {
        // given
        final Board board = Board.create();

        // when
        final Score score = board.calculateScore(WHITE);

        // then
        assertAll(
                () -> assertThat(score.getColor()).isEqualTo(WHITE),
                () -> assertThat(score.getScore()).isEqualTo(DEFAULT_MAX_SCORE)
        );
    }

    /**
     * RNB.KBNR
     * ........
     * ..p.....
     * PPp..PPP
     * ppp..Ppp
     * ........
     * ........
     * rnbqkbnr
     */
    @Test
    @DisplayName("폰이 같은 파일에 있을 때, 체스 보드의 점수를 계산한다.")
    void calculateScore_givenSameFilePawn_whenCall_thenReturnScore() {
        // given
        final Board board = PawnBoard.create();
        setBoard(board);

        // when
        final Score whiteScore = board.calculateScore(WHITE);
        final Score blackScore = board.calculateScore(BLACK);

        // then
        assertAll(
                () -> assertThat(whiteScore.getColor()).isEqualTo(WHITE),
                () -> {
                    final double totalMinus = whiteTotalMinus();
                    assertThat(whiteScore.getScore()).isEqualTo(DEFAULT_MAX_SCORE - totalMinus);
                },
                () -> assertThat(blackScore.getColor()).isEqualTo(BLACK),
                () -> {
                    final double totalMinus = blackTotalMinus();
                    assertThat(blackScore.getScore()).isEqualTo(DEFAULT_MAX_SCORE - totalMinus);
                }
        );
    }

    private static void setBoard(final Board board) {
        board.move(D4, C5, WHITE);
        board.move(E4, D5, WHITE);

        board.move(D8, D6, BLACK);
        board.move(D6, C6, BLACK);
        board.move(D5, C6, WHITE);

        board.move(E5, F4, BLACK);
    }

    private static double whiteTotalMinus() {
        final int pawnDie = 1;
        final double sameFilePawn = 3 * 0.5;
        return pawnDie + sameFilePawn;
    }

    private static double blackTotalMinus() {
        final int queenDie = 9;
        final int pawnDie = 2;
        final double sameFilePawn = 2 * 0.5;
        return queenDie + pawnDie + sameFilePawn;
    }
}
