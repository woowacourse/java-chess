package chess.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.MovedPawn;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceScoreTest {

    @Test
    @DisplayName("비숍의 점수를 올바르게 계산한다.")
    void bishopScoreTest() {
        // given
        Bishop bishop = new Bishop(Color.WHITE);
        // when
        Score score = bishop.getScore();
        // then
        assertThat(score).isEqualTo(Score.of(3));
    }

    @Test
    @DisplayName("폰의 점수를 올바르게 계산한다.")
    void pawnScoreTest() {
        // given
        Pawn pawn = new MovedPawn(Color.WHITE);
        // when
        Score score = pawn.getScore();
        // then
        assertThat(score).isEqualTo(Score.of(1));
    }

    @Test
    @DisplayName("나이트의 점수를 올바르게 계산한다.")
    void knightScoreTest() {
        // given
        Knight knight = new Knight(Color.WHITE);
        // when
        Score score = knight.getScore();
        // then
        assertThat(score).isEqualTo(Score.of(2.5));
    }

    @Test
    @DisplayName("퀸의 점수를 올바르게 계산한다.")
    void queenScoreTest() {
        // given
        Queen queen = new Queen(Color.WHITE);
        // when
        Score score = queen.getScore();
        // then
        assertThat(score).isEqualTo(Score.of(9));
    }

    @Test
    @DisplayName("킹의 점수를 올바르게 계산한다.")
    void kingScoreTest() {
        // given
        King king = new King(Color.WHITE);
        // when
        Score score = king.getScore();
        // then
        assertThat(score).isEqualTo(Score.of(0));
    }

    @Test
    @DisplayName("룩의 점수를 올바르게 계산한다.")
    void rookScoreTest() {
        // given
        Rook rook = new Rook(Color.WHITE);
        // when
        Score score = rook.getScore();
        // then
        assertThat(score).isEqualTo(Score.of(5));
    }
}
