package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.create();
    }

    @Test
    @DisplayName("White가 이겼을 경우 결과를 확인한다.")
    void win() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));
        board.move(Position.create("b7"), Position.create("a8"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(Result.decide(whiteScore, blackScore)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("White와 Black이 비겼을 경우 결과를 확인한다.")
    void draw() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(Result.decide(whiteScore, blackScore)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("Black이 진 경우 결과를 확인한다.")
    void lose() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));
        board.move(Position.create("b7"), Position.create("a8"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(Result.decide(blackScore, whiteScore)).isEqualTo(Result.LOSE);
    }

}
