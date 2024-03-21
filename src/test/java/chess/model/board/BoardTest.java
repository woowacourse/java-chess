package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.piece.Color;
import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void 보드는_64개의_칸으로_구성한다() {
        // given
        Board board = new Board(new HashMap<>());

        // when, then
        assertThat(board.getSignatures().size()).isEqualTo(64);
    }

    @Test
    void 이동_경로에_다른_기물이_있으면_예외가_발생한다() {
        // given
        Board board = new InitialBoardGenerator().create();
        Movement movement = new Movement(Position.of(1, 1), Position.of(1, 3));

        // when, then
        assertThatThrownBy(() -> board.move(movement, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기물이_특정_위치로_움직일_수_있다면_움직인다() {
        // given
        Board board = new InitialBoardGenerator().create();
        Movement movement = new Movement(Position.of(2, 2), Position.of(2, 3));
        board.move(movement, Color.WHITE);

        // when, then
        List<String> boardLines = board.getLines().stream()
                .map(line -> String.join("", line))
                .toList();
        assertThat(boardLines).containsExactly(
                "RNBQKBNR",
                "PPPPPPPP",
                "........",
                "........",
                "........",
                ".p......",
                "p.pppppp",
                "rnbqkbnr"
        );
    }
}
