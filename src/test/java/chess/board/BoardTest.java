package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.init();
    }

    @Test
    @DisplayName("끝났는지 확인")
    void finished() {
        assertThat(board.isFinish()).isFalse();

        killKingOfBlack();

        assertThat(board.isFinish()).isTrue();
    }

    private void killKingOfBlack() {
        board.movePiece("b2", "b4");
        board.movePiece("c7", "c5");

        board.movePiece("b4", "c5");
        board.movePiece("d7", "d6");

        board.movePiece("c5", "d6");
        board.movePiece("b7", "b6");

        board.movePiece("d6", "d7");
        board.movePiece("a7", "a6");

        board.movePiece("d7", "e8");
    }

    private void killKingOfWhite() {
        board.movePiece("c2", "c4");
        board.movePiece("b7", "b5");

        board.movePiece("d2", "d3");
        board.movePiece("b5", "c4");

        board.movePiece("a2", "a3");
        board.movePiece("c4", "d3");

        board.movePiece("a3", "a4");
        board.movePiece("d3", "d2");

        board.movePiece("a4", "a5");
        board.movePiece("d2", "e1");
    }

    @Test
    @DisplayName("폰이 일직선에 없을때 점수 확인")
    void pawnsNotDuplicateScore() {
        killKingOfBlack();
        assertThat(board.score(Color.BLACK)).isEqualTo(36.0);

        board.init();
        killKingOfWhite();
        assertThat(board.score(Color.BLACK)).isEqualTo(37.0);
    }

    @Test
    @DisplayName("폰이 일직선에 있을때 점수 확인")
    void pawnsDuplicateScore() {
        killKingOfBlack();
        assertThat(board.score(Color.WHITE)).isEqualTo(37.0);

        board.init();
        killKingOfWhite();
        assertThat(board.score(Color.WHITE)).isEqualTo(36.0);
    }

    @Test
    @DisplayName("Black 킹이 죽은 후 승자 확인")
    void winnerIsWhite() {
        killKingOfBlack();

        assertThat(board.winner()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("White 킹이 죽은 후 승자 확인")
    void winnerIsBlack() {
        killKingOfWhite();

        assertThat(board.winner()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("게임이 끝나지 않을때 승자 확인")
    void name() {
        assertThat(board.winner()).isEqualTo(Color.BLANK);
    }
}
