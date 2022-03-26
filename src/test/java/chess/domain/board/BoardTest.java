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

    @DisplayName("무사히 이동됨")
    @Test
    void move() {
        // given
        Position from = new Position(Column.E, Row.TWO);
        Position to = new Position(Column.E, Row.THREE);

        // then
        assertThatNoException()
                .isThrownBy(() -> board.move(from, to));
    }

    @DisplayName("이동 경로에 말이 있으면 이동 할 수 없다.")
    @Test
    void valid_path() {
        // given
        Position from = new Position(Column.D, Row.ONE);
        Position to = new Position(Column.F, Row.THREE);

        // then
        assertThatThrownBy(() -> board.move(from, to))
                .hasMessage("이동 경로에 말이 있습니다.");
    }

    @DisplayName("도착 지점에 같은 팀의 말이 있는 경우")
    @Test
    void valid_arrive() {
        // given
        Position from = new Position(Column.D, Row.ONE);
        Position to = new Position(Column.E, Row.TWO);

        // then
        assertThatThrownBy(() -> board.move(from, to))
                .hasMessage("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
    }

    @DisplayName("자신의 차례인지 검증")
    @Test
    void valid_turn() {
        // given
        Position from = new Position(Column.A, Row.SEVEN);
        Position to = new Position(Column.A, Row.SIX);

        // then
        assertThatThrownBy(() -> board.move(from, to))
                .hasMessage("현재 차례는 WHITE입니다.");
    }
}
