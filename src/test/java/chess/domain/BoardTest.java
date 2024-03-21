package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("보드")
class BoardTest {

    @Test
    @DisplayName("해당 위치에 기물이 존재하지 않는지 확인한다.")
    void existPiece() {
        Board board = new Board(Map.of());
        Square square = Square.from("b3");

        assertThat(board.isNotExistPiece(square)).isTrue();
    }

    @Test
    @DisplayName("해당 위치에 기물이 존재하지 않으면 예외가 발생한다.")
    void occurExceptionWhenNotExistPiece() {
        Board board = new Board(Map.of());
        Square source = Square.from("b3");
        Square target = Square.from("b4");

        assertThatCode(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Board.ERROR_NOT_EXIST_PIECE);

    }

    @Test
    @DisplayName("각 기물의 이동 방식으로 갈 수 없는 목적지인 경우 예외가 발생한다.")
    void occurExceptionWhenCannotMove() {
        Board board = new Board(Map.of(Square.from("b3"), new Rook(PieceColor.BLACK)));
        Square source = Square.from("b3");
        Square target = Square.from("c4");

        assertThatCode(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Board.ERROR_MOVE_NOT_AVAILABLE);
    }

    @Test
    @DisplayName(" 이동 경로에 다른 기물이 있으면 예외가 발생한다.")
    void occurExceptionWhenExistObstacleOnPath() {
        Board board = new Board(Map.of(
                Square.from("b3"), new Rook(PieceColor.BLACK),
                Square.from("b4"), new Rook(PieceColor.BLACK)
        ));
        Square source = Square.from("b3");
        Square target = Square.from("b5");

        assertThatCode(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Board.ERROR_MOVE_NOT_AVAILABLE);
    }
}
