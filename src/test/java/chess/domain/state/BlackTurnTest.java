package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackTurnTest {

    @DisplayName("말을 움직인다")
    @Test
    void test() {
        Board board = Board.getInstance();
        State blackTurn = new BlackTurn(board);
        Position src = Position.of("a7");
        Position dest = Position.of("a6");

        blackTurn.movePiece(src, dest);

        assertThat(board.exist(dest, Pawn.class, Color.BLACK)).isTrue();
    }

    @DisplayName("상대의 말을 움직이면 에러가 발생한다")
    @Test
    void testMoveError() {
        Board board = Board.getInstance();
        State blackTurn = new BlackTurn(board);

        Position src = Position.of("a2");
        Position dest = Position.of("a3");

        assertThatThrownBy(() -> blackTurn.movePiece(src, dest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대방");
    }

    @DisplayName("빈 곳을 움직이면 에러가 발생한다")
    @Test
    void testBlankMoveError() {
        Board board = Board.getInstance();
        State blackTurn = new BlackTurn(board);

        Position src = Position.of("d5");
        Position dest = Position.of("a6");

        assertThatThrownBy(() -> blackTurn.movePiece(src, dest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("기물이 존재하지 않습니다");
    }
}
