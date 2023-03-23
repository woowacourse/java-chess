package chess.model.move;

import static chess.model.board.PositionFixture.D1;
import static chess.model.board.PositionFixture.D2;
import static chess.model.board.PositionFixture.D3;
import static chess.model.board.PositionFixture.D4;
import static chess.model.board.PositionFixture.D5;
import static chess.model.board.PositionFixture.E5;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.QUEEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.board.Board;
import chess.model.board.PawnBoard;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardQueenMoveTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }

    @Test
    @DisplayName("이동 중에 기물이 존재하는 칸을 만나면 예외가 발생한다.")
    void move_queen_givenInvalidTarget_thenFail() {
        // when, then
        assertThatThrownBy(() -> board.move(D1, D3, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("퀸의 도착지가 적 기물이 있는 칸이라면 이동할 수 있다.")
    void move_queen_givenValidEnemyTarget_thenSuccess() {
        // given
        final Board board = PawnBoard.create();
        board.move(D4, E5, WHITE);

        // when
        board.move(D1, D5, WHITE);

        // then
        final Map<Position, Piece> squares = board.getSquares();
        assertAll(
                () -> assertThat(squares.get(D5).isEmpty()).isFalse(),
                () -> assertThat(squares.get(D1).isEmpty()).isTrue(),
                () -> assertThat(squares.get(D5).getType()).isEqualTo(QUEEN)
        );
    }

    @Test
    @DisplayName("퀸의 도착지가 자신의 기물이 있는 칸이라면 예외가 발생한다.")
    void move_whenQueenInValidTarget_thenFail() {
        // when
        assertThatThrownBy(() -> board.move(D1, D2, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("퀸이 움질일 때 중간에 기물이 있으면 예외가 발생한다.")
    void move_queen_givenInValidRoute_thenFail() {
        // given
        board.move(D2, D3, WHITE);

        // when, then
        assertThatThrownBy(() -> board.move(D1, D5, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다.");
    }
}
