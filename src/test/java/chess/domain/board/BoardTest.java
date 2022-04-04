package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BasicBoardFactory());
    }

    @Test
    @DisplayName("기물이 이동하는지 확인한다.")
    void move() {
        Position from = Position.create("a2");
        Position to = Position.create("a3");
        Piece source = board.getBoard().get(from);

        board.move(from, to);

        assertAll(
                () -> assertThat(board.getBoard().get(from)).isEqualTo(new EmptyPiece()),
                () -> assertThat(board.getBoard().get(to)).isEqualTo(source)
        );
    }

    @Test
    @DisplayName("Pawn을 제외한 특정 색의 기물을 모두 반환한다.")
    void countPawn() {
        assertThat(board.findPieceNotPawn(Color.WHITE).size()).isEqualTo(8);
    }

    @Test
    @DisplayName("같은 Column에 있는 특정 색의 Pawn을 모두 반환한다.")
    void countTwoPawnOnSameColumn() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));

        assertThat(board.findPawnOnSameColumn(Color.WHITE, Column.B).size()).isEqualTo(2);
    }
}
