package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드에서 체스 말을 이동시킬 수 있다.")
    void movePiece() {
        Board board = new Board(new InitialPiecePosition());
        board.move(new Position(2, 1), new Position(4, 1));
        Map<Position, Piece> boardPieces = board.getBoard();
        assertAll(
                () -> assertThat(boardPieces.get(new Position(2, 1)))
                        .isEqualTo(new Piece(PieceType.EMPTY, Color.NONE)),
                () -> assertThat(boardPieces.get(new Position(4, 1)))
                        .isEqualTo(new Piece(PieceType.PAWN, Color.WHITE)));
    }

    @Test
    @DisplayName("보드에서 체스 말을 이동할 수 없는 경우 예외가 발생한다.")
    void movePieceThrowException() {
        Board board = new Board(new InitialPiecePosition());
        assertThatThrownBy(() -> board.move(new Position(1, 1), new Position(2, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }
}
