package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
        board.initBoard();
    }


    @DisplayName("체스판이 비어있는지 확인한다.")
    @Test
    void empty_board() {
        // given
        board = new Board();
        // then
        assertThat(board.isEmpty()).isTrue();
    }

    @DisplayName("체스판이 초기화 되었는지 확인한다.")
    @Test
    void empty_board_false() {
        // given
        board = new Board();
        board.initBoard();

        // then
        assertThat(board.isEmpty()).isFalse();
    }


    @DisplayName("말이 없으면 보드가 이동한다.")
    @Test
    void move() {
        // given
        Position fromPawn = new Position(Column.E, Row.TWO);
        Position toPawn = new Position(Column.E, Row.THREE);

        Position fromQueen = new Position(Column.D, Row.ONE);
        Position toQueen = new Position(Column.E, Row.TWO);

        // when
        board.move(fromPawn, toPawn);

        // then
        assertThatNoException().isThrownBy(() -> board.move(fromQueen, toQueen));
    }

    @Test
    void move_queen() {
        // given
        Position fromPawn = new Position(Column.E, Row.TWO);
        Position toPawn = new Position(Column.E, Row.THREE);

        Position fromQueen = new Position(Column.D, Row.ONE);
        Position toQueen = new Position(Column.H, Row.FIVE);

        // when
        board.move(fromPawn, toPawn);

        // then
        assertThatNoException().isThrownBy(() -> board.move(fromQueen, toQueen));
    }

    @DisplayName("이동 경로에 말이 있으면 이동 할 수 없다,.")
    @Test
    void move_false() {
        // given
        Position fromQueen = new Position(Column.D, Row.ONE);
        Position toQueen = new Position(Column.E, Row.TWO);

        // then
        assertThatThrownBy(() -> board.move(fromQueen, toQueen)).hasMessageContaining("이동 경로");
    }

    @DisplayName("폰의 2칸 이동")
    @Test
    void pawn_move() {
        // given
        Position knightFrom = new Position(Column.G, Row.ONE);
        Position knightTo = new Position(Column.F, Row.THREE);

        Position pawnFrom = new Position(Column.F, Row.TWO);
        Position pawnTo = new Position(Column.F, Row.FOUR);

        // when
        board.move(knightFrom, knightTo);

        // then
        assertThatThrownBy(() -> board.move(pawnFrom, pawnTo)).hasMessageContaining("이동 경로");
    }
}
