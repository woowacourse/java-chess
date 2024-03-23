package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("보드")
class BoardTest {

    @DisplayName("기물의 출발지와 목적지가 같으면 예외가 발생한다.")
    @Test
    void occurExceptionWhenSourceAndTargetAreSameSquare() {
        Board board = new Board(Map.of(
                Square.from("b3"), new Piece(PieceType.ROOK, PieceColor.BLACK)));
        Square source = Square.from("b3");
        Square target = Square.from("b3");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("해당 위치에 기물이 존재하지 않으면 예외가 발생한다.")
    void occurExceptionWhenNotExistPiece() {
        Board board = new Board(Map.of());
        Square source = Square.from("b3");
        Square target = Square.from("b4");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 기물의 이동 방식으로 갈 수 없는 목적지인 경우 예외가 발생한다.")
    void occurExceptionWhenCannotMove() {
        Board board = new Board(Map.of(
                Square.from("b3"), new Piece(PieceType.ROOK, PieceColor.BLACK)));
        Square source = Square.from("b3");
        Square target = Square.from("c4");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 경로에 다른 기물이 있으면 예외가 발생한다.")
    void occurExceptionWhenExistObstacleOnPath() {
        Board board = new Board(Map.of(
                Square.from("b3"), new Piece(PieceType.ROOK, PieceColor.BLACK),
                Square.from("b4"), new Piece(PieceType.ROOK, PieceColor.BLACK)
        ));
        Square source = Square.from("b3");
        Square target = Square.from("b5");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
