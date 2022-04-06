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
        board.move(Position.create("12"), Position.create("14"));
        board.move(Position.create("14"), Position.create("15"));
        board.move(Position.create("15"), Position.create("16"));
        board.move(Position.create("16"), Position.create("27"));
        board.move(Position.create("27"), Position.create("18"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(Result.decide(whiteScore, blackScore)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("White와 Black이 비겼을 경우 결과를 확인한다.")
    void draw() {
        board.move(Position.create("12"), Position.create("14"));
        board.move(Position.create("14"), Position.create("15"));
        board.move(Position.create("15"), Position.create("16"));
        board.move(Position.create("16"), Position.create("27"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(Result.decide(whiteScore, blackScore)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("Black이 진 경우 결과를 확인한다.")
    void lose() {
        board.move(Position.create("12"), Position.create("14"));
        board.move(Position.create("14"), Position.create("15"));
        board.move(Position.create("15"), Position.create("16"));
        board.move(Position.create("16"), Position.create("27"));
        board.move(Position.create("27"), Position.create("18"));

        Score whiteScore = new Score(board, Color.WHITE);
        Score blackScore = new Score(board, Color.BLACK);

        assertThat(Result.decide(blackScore, whiteScore)).isEqualTo(Result.LOSE);
    }

}
