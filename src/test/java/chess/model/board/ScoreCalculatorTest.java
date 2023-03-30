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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {

    private static final double DEFAULT_MAX_SCORE = 38;

    private ScoreCalculator calculator;

    @BeforeEach
    void init() {
        calculator = new ScoreCalculator();
    }

    @Test
    @DisplayName("기본 체스 보드의 점수를 계산한다.")
    void calculateScore_whenCall_thenReturnScore() {
        // given
        final ScoreCalculator scoreCalculator = new ScoreCalculator();

        // when
        final Score score = scoreCalculator.calculateScore(WHITE, Board.create().getSquares());

        // then
        assertAll(
                () -> assertThat(score.getColor()).isSameAs(WHITE),
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
        final Score whiteScore = calculator.calculateScore(WHITE, board.getSquares());
        final Score blackScore = calculator.calculateScore(BLACK, board.getSquares());

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
