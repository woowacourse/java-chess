package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("Board를 초기화한다.")
    void create() {
        assertThat(new Board(BoardInitializer.initialize())).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("Position 피스의 색을 가져온다.")
    void getPieceColorByPosition() {
        Board board = new Board(BoardInitializer.initialize());
        assertThat(board.getPieceColorByPosition(Position.valueOf("a8"))).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("Position을 입력하면 piece를 반환한다.")
    void getPiece() {
        Board board = new Board(BoardInitializer.initialize());
        assertThat(board.getPiece(Position.valueOf("a8"))).isEqualTo(new Rook(Color.BLACK));
    }

    @Test
    @DisplayName("Board가 move하면 타겟의 기물을 반환한다.")
    void move() {
        Board board = new Board(BoardInitializer.initialize());
        assertThat(board.movePiece(Position.valueOf("a7"), Position.valueOf("a6"))).isInstanceOf(Blank.class);
    }
}
