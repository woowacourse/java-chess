package chess.model.move;

import static chess.model.board.PositionFixture.E1;
import static chess.model.board.PositionFixture.E2;
import static chess.model.board.PositionFixture.E3;
import static chess.model.board.PositionFixture.E4;
import static chess.model.board.PositionFixture.E5;
import static chess.model.board.PositionFixture.F5;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.board.Board;
import chess.model.board.PawnBoard;
import chess.model.piece.type.Piece;
import chess.model.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardKingMoveTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }

    @Test
    @DisplayName("킹의 도착지가 적 기물이 있는 칸이라면 이동할 수 있다.")
    void move_king_givenValidEnemyTarget_thenSuccess() {
        // given
        final Board board = PawnBoard.create();
        board.move(E4, F5, WHITE);

        // when
        kingMove(board);

        // then
        final Map<Position, Piece> squares = board.getSquares();

        assertAll(
                () -> assertThat(squares.get(E5).isEmpty()).isFalse(),
                () -> assertThat(squares.get(E1).isEmpty()).isTrue(),
                () -> assertThat(squares.get(E5).update().getType()).isEqualTo(KING)
        );
    }

    private void kingMove(final Board board) {
        board.move(E1, E2, WHITE);
        board.move(E2, E3, WHITE);
        board.move(E3, E4, WHITE);
        board.move(E4, E5, WHITE);
    }

    @Test
    @DisplayName("킹의 도착지가 자신의 기물이 있는 칸이라면 예외가 발생한다.")
    void move_king_givenInvalidMyPieceTarget_thenFail() {
        // when
        assertThatThrownBy(() -> board.move(E1, E2, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 좌표로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("킹이 2칸 이상 움직이려고 하면 예외가 발생한다.")
    void move_king_givenTwoMoreDistance_thenFail() {
        // given
        final Board board = PawnBoard.create();

        // when, then
        assertThatThrownBy(() -> board.move(E1, E3, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
    }
}
