package chess;

import chess.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BoardTest {

    @Test
    @DisplayName("체스판을 생성하는 테스트")
    void createBoard() {
        Board board = Board.create(Pieces.create());
        assertThat(board).isExactlyInstanceOf(Board.class);
    }


    @Test
    @DisplayName("룩의 진행방향에 말이 있으면 예외 처리")
    void moveFailureWhenExistPieceTest() {
        Board board = Board.create(Pieces.create());
        List<String> command = List.of("a8", "a5");
        assertThatThrownBy(
                () -> board.move(command)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩의 target위치에 아군 말이 있으면 예외 처리")
    void moveFailureTest() {
        Board board = Board.create(Pieces.create());
        List<String> command = List.of("a8", "a7");
        assertThatThrownBy(
                () -> board.move(command)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩의 target위치에 아군 말이 없으면 움직임에 성공한다")
    void moveTest() {
        List<Piece> pieces = List.of(
                new Rook(Position.of('a', '8'), Team.BLACK),
                new Empty(Position.of('a', '7')),
                new Empty(Position.of('a', '6')),
                new Pawn(Position.of('a', '5'), Team.WHITE)
        );
        Board board = Board.create(Pieces.of(pieces));
        List<String> command = List.of("a8", "a5");


        assertDoesNotThrow(
                () -> board.move(command)
        );
    }
}
