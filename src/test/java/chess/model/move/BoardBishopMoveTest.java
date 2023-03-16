package chess.model.move;

import static chess.model.board.PositionFixture.B5;
import static chess.model.board.PositionFixture.C4;
import static chess.model.board.PositionFixture.D5;
import static chess.model.board.PositionFixture.E2;
import static chess.model.board.PositionFixture.F1;
import static chess.model.board.PositionFixture.F2;
import static chess.model.board.PositionFixture.G2;
import static chess.model.board.PositionFixture.H3;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.BISHOP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.board.Board;
import chess.model.board.PawnBoard;
import chess.model.board.Square;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardBishopMoveTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }

    @Test
    @DisplayName("이동 중에 기물이 존재하는 칸을 만나면 예외가 발생한다.")
    void move_givenInvalidSourceAndTarget_thenFail() {
        // when, then
        assertThatThrownBy(() -> board.move(F1, G2, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다");
    }

    @Test
    @DisplayName("비숍이 움직일 수 있는 방향으로 움직이면 성공한다.")
    void move_whenBishopValidTarget_thenSuccess() {
        // given
        final Board board = PawnBoard.create();

        // when
        board.move(F1, H3, WHITE);

        // then
        final List<Square> squares = board.getSquares();
        final int sourceIndex = F1.convertToIndex();
        final int targetIndex = H3.convertToIndex();

        assertAll(
                () -> assertThat(squares.get(targetIndex).isEmpty()).isFalse(),
                () -> assertThat(squares.get(sourceIndex).isEmpty()).isTrue(),
                () -> assertThat(squares.get(targetIndex).pick().getType()).isEqualTo(BISHOP)
        );
    }

    @Test
    @DisplayName("비숍이 움직일 수 없는 방향으로 움직이면 예외가 발생한다.")
    void bishop_givenInvalidDirection_thenFail() {
        // given
        final Board board = PawnBoard.create();

        // when, then
        assertThatThrownBy(() -> board.move(F1, F2, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("비숍의 도착지가 적 기물이 있는 칸이라면 성공한다.")
    void move_whenBishopValidEnemyTarget_thenSuccess() {
        // given
        final Board board = PawnBoard.create();
        board.move(C4, D5, WHITE);

        // when
        board.move(F1, B5, WHITE);

        // then
        final List<Square> squares = board.getSquares();
        final int sourceIndex = F1.convertToIndex();
        final int targetIndex = B5.convertToIndex();

        assertAll(
                () -> assertThat(squares.get(targetIndex).isEmpty()).isFalse(),
                () -> assertThat(squares.get(sourceIndex).isEmpty()).isTrue(),
                () -> assertThat(squares.get(targetIndex).pick().getType()).isEqualTo(BISHOP)
        );
    }

    @Test
    @DisplayName("비숍의 도착지가 자신의 기물이 있는 칸이라면 예외가 발생한다.")
    void move_whenBishopInValidTarget_thenFail() {
        // when
        assertThatThrownBy(() -> board.move(F1, E2, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다");
    }
}
