package chess.domain.board;

import chess.domain.piece.PieceColor;
import chess.domain.piece.Rook;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("보드")
class BoardTest {

    @Test
    @DisplayName("빈 스퀘어로 기물을 정상적으로 움직일 수 있다.")
    void moveToEmptySquare() {
        // given
        Square source = Square.from("b3");
        Square target = Square.from("b4");
        Rook piece = new Rook(PieceColor.BLACK, source);
        Board board = new Board(Set.of(piece));

        // when
        board.move(source, target);

        // then
        assertThat(board.findPiece(target)).hasValue(piece);
    }

    @Test
    @DisplayName("출발지 스퀘어에 기물이 존재하지 않을 경우 예외가 발생한다.")
    void exceptionOnMoveWhenNoPieceOnSourceSquare() {
        // given
        Board board = new Board(Set.of());
        Square source = Square.from("b3");
        Square target = Square.from("b4");

        // when & then
        assertThatCode(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("해당 위치에 기물이 존재하는지 않는지 확인한다.")
    void notExistOnSquare() {
        // given
        Square source = Square.from("b3");
        Board board = new Board(Set.of());

        // when
        boolean exist = board.existOnSquare(source);

        // then
        assertThat(exist).isFalse();
    }

    @Test
    @DisplayName("해당 위치에 기물이 존재하는지 확인한다.")
    void existOnSquare() {
        // given
        Square source = Square.from("b3");
        Rook piece = new Rook(PieceColor.BLACK, source);
        Board board = new Board(Set.of(piece));

        // when
        boolean exist = board.existOnSquare(source);

        // then
        assertThat(exist).isTrue();
    }

    @Test
    @DisplayName("해당 위치가 비어 있을 때 검은색 기물이 존재하지 않은지 확인한다.")
    void notExistBlackOnEmptySquare() {
        // given
        Square source = Square.from("b3");
        Board board = new Board(Set.of());

        // when
        boolean exist = board.existOnSquareWithColor(source, PieceColor.BLACK);

        // then
        assertThat(exist).isFalse();
    }

    @Test
    @DisplayName("해당 위치에 흰색 기물이 있을 때 검은색 기물이 존재하지 않은지 확인한다.")
    void notExistBlackOnSquareHaveWhite() {
        // given
        Square source = Square.from("b3");
        Board board = new Board(Set.of(new Rook(PieceColor.WHITE, source)));

        // when
        boolean exist = board.existOnSquareWithColor(source, PieceColor.BLACK);

        // then
        assertThat(exist).isFalse();
    }

    @Test
    @DisplayName("해당 위치에 검은색 기물이 있을 때 검은색 기물이 존재하는지 확인한다.")
    void existBlackOnSquare() {
        // given
        Square source = Square.from("b3");
        Board board = new Board(Set.of(new Rook(PieceColor.BLACK, source)));

        // when
        boolean exist = board.existOnSquareWithColor(source, PieceColor.BLACK);

        // then
        assertThat(exist).isTrue();
    }
}
