package chess.domain.board;

import chess.domain.piece.Color;
import chess.helper.BoardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardScoreTest {

    /**
     * .KR.....  8
     * P.PB....  7
     * .P..Q...  6
     * ........  5
     * .....nq.  4
     * .....p.p  3
     * .....pp.  2
     * ....rk..  1
     * <p>
     * 12345678
     */
    @Test
    @DisplayName("calculateBoardScoreBy() : 색깔마다 체스 기물들의 점수 합을 모두 구할 수 있다.")
    void test_calculateBoardScoreBy() throws Exception {
        //given
        final BoardScore boardScore = BoardScore.flatByColumnFrom(BoardFixture.createBoard());
        final Color black = Color.BLACK;
        final Color white = Color.WHITE;

        //when
        Score whitePieceScore = boardScore.calculateBoardScoreBy(white);
        Score blackPieceScore = boardScore.calculateBoardScoreBy(black);

        //then
        assertAll(
                () -> assertEquals(whitePieceScore, Score.from(19.5)),
                () -> assertEquals(blackPieceScore, Score.from(20))
        );
    }
}
