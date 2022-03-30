package chess.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.BoardInitializer;
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
    @DisplayName("Pawn Piece가 같은 Column에 2개 있는 상태에서 점수를 계산한다.")
    void calculateScoreMoveBoardWhite() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));

        Score score = new Score(board, Color.WHITE);
        assertThat(score.getValue()).isEqualTo(37);
    }

    @Test
    @DisplayName("해당 턴의 참가자가 승리하는 경우를 확인한다.")
    void win() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));
        board.move(Position.create("b7"), Position.create("a8"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(whiteScore.decideResult(blackScore)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("해당 턴의 참가자가 무승부하는 경우를 확인한다.")
    void draw() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(whiteScore.decideResult(blackScore)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("해당 턴의 참가자가 패배하는 경우를 확인한다.")
    void lose() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));
        board.move(Position.create("b7"), Position.create("a8"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(blackScore.decideResult(whiteScore)).isEqualTo(Result.LOSE);
    }
}
