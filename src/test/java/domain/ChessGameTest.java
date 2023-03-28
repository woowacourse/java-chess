package domain;

import static domain.ChessColumn.A;
import static domain.ChessColumn.B;
import static domain.Rank.FIVE;
import static domain.Rank.FOUR;
import static domain.Rank.SEVEN;
import static domain.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.TeamColor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("폰은 적이 없을 때 대각선으로 갈 수 없다.")
    void pawnCanNotGoDiagonal() {
        ChessGame chessGame = new ChessGame();
        Square initPawnPosition = Square.of(3, 2);
        Square destination = Square.of(4, 3);
        assertThatThrownBy(() -> chessGame.move(initPawnPosition, destination))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("대각선으로 갈 수 없습니다.");
    }

    @Test
    @DisplayName("폰은 앞에 기물이 있는경우 앞으로 갈 수 없다.")
    void pawnCanNotGoStraight() {
        ChessGame chessGame = new ChessGame();
        chessGame.move(Square.of(B, TWO), Square.of(B, FOUR));
        chessGame.move(Square.of(B, SEVEN), Square.of(B, FIVE));

        assertThatThrownBy(() -> chessGame.move(Square.of(B, FOUR), Square.of(B, FIVE)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("폰은 기물이 있으면 앞으로 갈 수 없습니다.");
    }

    @Test
    @DisplayName("체스보드에 존재하는 각 기물에 대해 점수를 계산한다. (세로줄에 겹치는 폰x)")
    void calculateScoreWithNoDuplicatePawn() {
        ChessGame chessGame = new ChessGame();
        double actual = chessGame.getScore(TeamColor.BLACK);
        Assertions.assertThat(actual).isEqualTo(38);
    }

    /**
     * RNBQKBNR  8 (rank 8)
     * P.PPPPPP  7
     * ........  6
     * .p......  5
     * ........  4
     * ........  3
     * .ppppppp  2
     * rnbqkbnr  1
     */
    @Test
    @DisplayName("체스보드에 존재하는 각 기물에 대해 점수를 계산한다. (세로줄에 겹치는 폰o)")
    void calculateScoreWithDuplicatePawn() {
        ChessGame chessGame = new ChessGame();
        chessGame.move(Square.of(A, TWO), Square.of(A, FOUR));
        chessGame.move(Square.of(B, SEVEN), Square.of(B, FIVE));
        chessGame.move(Square.of(A, FOUR), Square.of(B, FIVE));

        double blackScore = chessGame.getScore(TeamColor.BLACK);
        Assertions.assertThat(blackScore).isEqualTo(37);

        double whiteScore = chessGame.getScore(TeamColor.WHITE);
        Assertions.assertThat(whiteScore).isEqualTo(37);
    }
}
