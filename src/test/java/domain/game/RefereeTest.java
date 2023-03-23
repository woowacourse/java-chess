package domain.game;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RefereeTest {
    @DisplayName("점수를 계산하면 White, Black진영 각각의 점수가 Map으로 반환된다.")
    @Test
    void calculateSideScoresTest() {
        //given
        Referee referee = new Referee();
        Map<Position, Piece> chessBoard = new ChessBoardGenerator().generate();

        //when
        Map<Side, Double> scores = referee.calculateScore(chessBoard);

        //then
        assertAll(
                () -> assertThat(scores.get(Side.WHITE)).isEqualTo(38),
                () -> assertThat(scores.get(Side.BLACK)).isEqualTo(38)
        );
    }

}