package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("move시 해당 칸에 체스 말이 없으면 에러를 발생시킨다.")
    void move_error() {
        Board board = Board.create();

        assertThatThrownBy(() -> board.move("a3", "a4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치에 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("move가 불가능하면 에러를 발생시킨다.")
    void move_error1() {
        Board board = Board.create();

        assertThatThrownBy(() -> board.move("a1", "a4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("move가 가능하면 도착 위치로 말을 이동시킨다.")
    void move_success() {
        Board board = Board.create();

        Board newBoard = board.move("b1", "c3");

        assertThat(newBoard.findPiece(Coordinate.of("c3"))).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("왕이 둘다 살아있으면 true를 반환")
    void is_both_king_alive() {
        Board board = Board.create();

        assertThat(board.isBothKingAlive()).isTrue();
    }

    @Test
    @DisplayName("왕이 한명이라도 죽으면 false를 반환")
    void is_both_king_not_alive() {
        Board board = Board.create();
        board.getValue().put(Coordinate.of("e1"), Piece.of(Symbol.EMPTY.name(), Team.NONE.name()));

        assertThat(board.isBothKingAlive()).isFalse();
    }
}
