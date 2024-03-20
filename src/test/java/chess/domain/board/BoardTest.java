package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임판")
class BoardTest {

    @DisplayName("초기화에 성공한다.")
    @Test
    void initialize() {
        //given
        Board board = BoardFactory.createBoard();
        int expectedSize = 32;

        //when & then
        assertThat(board.getPieces().keySet().size()).isEqualTo(expectedSize);
    }

    @DisplayName("해당 턴이 아닌 경우 예외가 발생한다")
    @Test
    void invalidTurn() {
        //given
        Board board = BoardFactory.createBoard();
        Square from = Square.from("c2");
        Square to = Square.from("c3");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동이 불가능할 경우 예외가 발생한다")
    @Test
    void invalidMovable() {
        //given
        Board board = BoardFactory.createBoard();
        Square from = Square.from("c2");
        Square to = Square.from("c5");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이동 경로에 다른 기물이 있으면 예외가 발생한다")
    @Test
    void checkRoute() {
        //given
        Board board = BoardFactory.createBoard();
        Square from = Square.from("c1");
        Square to = Square.from("f4");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트는 이동 경로에 다른 기물이 있어도 예외가 발생하지 않는다")
    @Test
    void checkKnightRoute() {
        //given
        Board board = BoardFactory.createBoard();
        Square from = Square.from("b1");
        Square to = Square.from("c3");

        //when & then
        assertThatCode(() -> board.move(from, to, Color.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("목적지에 있는 기물이 현재 기물과 같은 색이라면 예외가 발생한다")
    @Test
    void invalidDestination() {
        //given
        Board board = BoardFactory.createBoard();
        Square from = Square.from("a1");
        Square to = Square.from("a2");

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
