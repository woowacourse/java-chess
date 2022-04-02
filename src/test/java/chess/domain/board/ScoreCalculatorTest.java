package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    @DisplayName("초기 상태의 체스판에서 흑색 진영의 점수는 38점이다.")
    @Test
    void calculateScoreOfBlack_38() {
        //given
        Board board = new Board();
        //then
        assertThat(ScoreCalculator.calculateScoreOfBlack(board.getPiecesByPosition())).isEqualTo(38);
    }

    @DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 흑색 진영의 점수는 37점이다.")
    @Test
    void calculateScoreOfBlack_37() {
        //given
        Board board = new Board();
        Position b2 = Position.of(Column.B, Row.TWO);
        Position b4 = Position.of(Column.B, Row.FOUR);
        Position c7 = Position.of(Column.C, Row.SEVEN);
        Position c5 = Position.of(Column.C, Row.FIVE);
        Position d2 = Position.of(Column.D, Row.TWO);
        Position d4 = Position.of(Column.D, Row.FOUR);

        board.move(b2, b4);
        board.move(c7, c5);
        board.move(d2, d4);
        board.move(c5, b4);
        //then
        assertThat(ScoreCalculator.calculateScoreOfBlack(board.getPiecesByPosition())).isEqualTo(37);
    }

    @DisplayName("초기 상태의 체스판에서 백색 진영의 점수는 38점이다.")
    @Test
    void calculateScoreOfWhite_38() {
        //given
        Board board = new Board();
        //then
        assertThat(ScoreCalculator.calculateScoreOfWhite(board.getPiecesByPosition())).isEqualTo(38);
    }

    @DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 백색 진영의 점수는 37점이다.")
    @Test
    void calculateScoreOfWhite_37() {
        //given
        Board board = new Board();
        Position b2 = Position.of(Column.B, Row.TWO);
        Position b4 = Position.of(Column.B, Row.FOUR);
        Position c7 = Position.of(Column.C, Row.SEVEN);
        Position c5 = Position.of(Column.C, Row.FIVE);
        board.move(b2, b4);
        board.move(c7, c5);
        board.move(b4, c5);
        //then
        assertThat(ScoreCalculator.calculateScoreOfWhite(board.getPiecesByPosition())).isEqualTo(37);
    }
}