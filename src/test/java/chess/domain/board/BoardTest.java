package chess.domain.board;

import static chess.domain.board.Board.INVALID_TARGET_POSITION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.pieces.pawn.WhitePawn;
import chess.domain.position.Position;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BoardMaker());
    }

    @Test
    @DisplayName("생성된 Board는 64개의 Position과 Piece를 가진다.")
    void 생성된_Board는_64개의_Position과_Piece를_가진다() {
        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("기물을 이동한다.")
    void 기물을_이동한다() {
        Position currentPosition = new Position(6, 0);
        Position targetPosition = new Position(4, 0);

        board.movePiece(currentPosition, targetPosition);

        Map<Position, Piece> values = board.getBoard();

        assertAll(
                () -> assertThat(values.get(currentPosition)).isInstanceOf(EmptyPiece.class),
                () -> assertThat(values.get(targetPosition)).isInstanceOf(WhitePawn.class)
        );
    }

    @Test
    @DisplayName("적절한 이동이 아닌 경우 예외가 발생한다.")
    void 적절한_이동이_아닌_경우_예외가_발생한다() {
        Position currentPosition = new Position(1, 0);
        Position targetPosition = new Position(7, 0);

        assertThatIllegalArgumentException().isThrownBy(
                () -> board.movePiece(currentPosition, targetPosition)
        );
    }

    @Test
    @DisplayName("동일한 좌표로 이동할 수 없다.")
    void 동일한_좌표로_이동할_수_없다() {
        Position currentPosition = new Position(1, 0);
        Position targetPosition = new Position(1, 0);

        assertThatIllegalArgumentException().isThrownBy(
                () -> board.movePiece(currentPosition, targetPosition)
        ).withMessage(INVALID_TARGET_POSITION);
    }
}
