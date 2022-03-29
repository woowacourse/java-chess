package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(BoardInitializer.create());
    }

    @Test
    @DisplayName("초기 상태에서 점수를 계산한다.")
    void calculateScore() {
        Score score = new Score(board, Color.BLACK);
        assertThat(score.getValue()).isEqualTo(38);
    }

    @Test
    @DisplayName("Pawn Piece가 잡힌 상태에서 점수를 계산한다.")
    void calculateScoreMoveBoardBlack() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));

        Score score = new Score(board, Color.BLACK);
        assertThat(score.getValue()).isEqualTo(37);
    }

    @Test
    @DisplayName("Pawn Piece가 같은 File에 2개 있는 상태에서 점수를 계산한다.")
    void calculateScoreMoveBoardWhite() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));

        Score score = new Score(board, Color.WHITE);
        assertThat(score.getValue()).isEqualTo(37);
    }

}
