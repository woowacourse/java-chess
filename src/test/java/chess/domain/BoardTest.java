package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(BoardInitializer.create());
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
    @DisplayName("Pawn의 개수를 구한다.")
    void countPawn() {
        assertThat(board.countPiece(new PawnPiece(Color.WHITE))).isEqualTo(8);
    }

    @Test
    @DisplayName("같은 파일에 Pawn의 개수를 구한다.")
    void countPawnOnSameFile() {
        assertThat(board.countPieceOnSameFile(new PawnPiece(Color.BLACK), File.A)).isEqualTo(1);
    }

    @Test
    @DisplayName("같은 파일에 Pawn의 개수를 구한다.")
    void countTwoPawnOnSameFile() {
        board.move(Position.create("a2"), Position.create("a4"));
        board.move(Position.create("a4"), Position.create("a5"));
        board.move(Position.create("a5"), Position.create("a6"));
        board.move(Position.create("a6"), Position.create("b7"));

        assertThat(board.countPieceOnSameFile(new PawnPiece(Color.WHITE), File.B)).isEqualTo(2);
    }
}
