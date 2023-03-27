package chess.domain.chess;

import chess.domain.chess.vo.ScoreVO;
import chess.domain.piece.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessGameCalculatorTest {

    @Test
    @DisplayName("현재 체스판의 각 진영에 대한 점수를 계산한다.")
    void calculate() {
        // given
        final ChessGame chessGame = new ChessGame(CampType.WHITE);
        final ScoreVO expected = new ScoreVO(Score.create(38.0), Score.create(38.0));

        // when
        ScoreVO actual = ChessGameCalculator.calculate(chessGame);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
