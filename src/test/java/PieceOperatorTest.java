import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.Board;
import chess.PieceOperator;
import chess.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceOperatorTest {

    private Board board;
    private PieceOperator pieceOperator;

    @BeforeEach
    @DisplayName("보드의 초기 설정")
    void setUp() {
        board = new Board();
        pieceOperator = new PieceOperator(board);

        pieceOperator.initialize();
    }

    @Test
    @DisplayName("킹 이동 테스트(이동 위치에 아군 말이 있는 경우 예외처리)")
    void moveKing() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            pieceOperator.move(Point.of("e1"), Point.of("e2"))
        ).withMessage("아군 말이 있는 곳에는 이동할 수 없습니다");

        assertThatIllegalArgumentException().isThrownBy(() ->
            pieceOperator.move(Point.of("e1"), Point.of("e3"))
        ).withMessage("해당 위치로는 이동할 수 없는 말입니다.");
    }
}
