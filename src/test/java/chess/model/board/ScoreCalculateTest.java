package chess.model.board;

import static chess.model.piece.PieceColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreCalculateTest {

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
                () -> assertThat(score.getScore()).isEqualTo(38)
        );
    }
}
