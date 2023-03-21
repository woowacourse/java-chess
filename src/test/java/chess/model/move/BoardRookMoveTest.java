package chess.model.move;

import static chess.model.board.PositionFixture.F3;
import static chess.model.board.PositionFixture.G5;
import static chess.model.board.PositionFixture.H1;
import static chess.model.board.PositionFixture.H2;
import static chess.model.board.PositionFixture.H3;
import static chess.model.board.PositionFixture.H4;
import static chess.model.board.PositionFixture.H5;
import static chess.model.board.PositionFixture.H6;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.board.Board;
import chess.model.board.PawnBoard;
import chess.model.piece.type.Piece;
import chess.model.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardRookMoveTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }

    @Test
    @DisplayName("이동 중에 기물이 존재하는 칸을 만나면 예외가 발생한다.")
    void move_rook_givenInvalidSourceAndTarget_thenFail() {
        // when, then
        assertThatThrownBy(() -> board.move(H1, H3, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("룩이 움직일 수 있는 방향으로 움직이면 이동할 수 있다.")
    void move_rook_givenValidTarget_thenSuccess() {
        // given
        final Board board = PawnBoard.create();

        // when
        board.move(H1, H2, WHITE);

        // then
        final Map<Position, Piece> squares = board.getSquares();

        assertAll(
                () -> assertThat(squares.get(H2).isEmpty()).isFalse(),
                () -> assertThat(squares.get(H1).isEmpty()).isTrue(),
                () -> assertThat(squares.get(H2).getType()).isEqualTo(ROOK)
        );
    }

    @Test
    @DisplayName("룩이 움직일 수 없는 방향으로 움직이면 예외가 발생한다.")
    void move_rook_givenInvalidDirection_thenFail() {
        // given
        final Board board = PawnBoard.create();

        // when, then
        assertThatThrownBy(() -> board.move(H1, F3, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("룩의 도착지가 적 기물이 있는 칸이라면 이동할 수 있다.")
    void move_rook_givenValidEnemyTarget_thenSuccess() {
        // given
        final Board board = PawnBoard.create();
        board.move(H4, G5, WHITE);

        // when
        board.move(H1, H5, WHITE);

        // then
        final Map<Position, Piece> squares = board.getSquares();

        assertAll(
                () -> assertThat(squares.get(H5).isEmpty()).isFalse(),
                () -> assertThat(squares.get(H1).isEmpty()).isTrue(),
                () -> assertThat(squares.get(H5).getType()).isEqualTo(ROOK)
        );
    }

    @Test
    @DisplayName("룩의 도착지가 자신의 기물이 있는 칸이라면 예외가 발생한다.")
    void move_rook_givenInValidTarget_thenFail() {
        // when
        assertThatThrownBy(() -> board.move(H1, H2, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 좌표로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("룩이 움질일 때 중간의 기물이 있으면 예외가 발생한다.")
    void move_rook_givenInValidRoute_thenFail() {
        // given
        board.move(H2, H3, WHITE);

        // when, then
        assertThatThrownBy(() -> board.move(H1, H6, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다.");
    }
}
