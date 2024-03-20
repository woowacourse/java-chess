package chess.domain;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("출발 좌표에 말이 없을때 예외가 발생한다.")
    void moveByBlank() {
        Board board = new Board();
        Position from = new Position(4, 4);
        Position to = new Position(5, 5);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(Color.WHITE, from, to))
                .withMessage("이동할 수 있는 말이 없습니다.");
    }

    @Test
    @DisplayName("흰색의 차례에 검은색 말을 움직이려 하면 예외가 발생한다.")
    void moveByInvalidPieceColor() {
        Board board = new Board(); // TODO : 폰으로 바꾸기
        Position from = new Position(5, 8);
        Position to = new Position(5, 7);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(Color.WHITE, from, to))
                .withMessage("상대 말은 이동할 수 없습니다.");
    }

    @Test
    // TODO: 추후 변경 필요
    void testTest() {
        Board board = new Board();
        Position from = new Position(1, 1);
        Position to = new Position(1, 8);

        board.move(Color.WHITE, from, to);
        Map<Position, PieceType> board1 = board.collectBoard();

        Assertions.assertThat(board1.get(from)).isEqualTo(PieceType.NONE);
        Assertions.assertThat(board1.get(to)).isEqualTo(PieceType.WHITE_ROOK);
    }
}