package domain.board;

import domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class InitialBoardGeneratorTest {

    @Test
    @DisplayName("체스판을 초기화한다.")
    void initialize() {
        BoardGenerator boardGenerator = new InitialBoardGenerator();
        Board board = Board.generatedBy(boardGenerator);

        assertAll(
                () -> assertThat(board.findPieceAt(Position.of(1, 1))).isInstanceOf(Rook.class),
                () -> assertThat(board.findPieceAt(Position.of(2, 1))).isInstanceOf(Knight.class),
                () -> assertThat(board.findPieceAt(Position.of(3, 1))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceAt(Position.of(4, 1))).isInstanceOf(Queen.class),
                () -> assertThat(board.findPieceAt(Position.of(5, 1))).isInstanceOf(King.class),
                () -> assertThat(board.findPieceAt(Position.of(6, 1))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceAt(Position.of(7, 1))).isInstanceOf(Knight.class),
                () -> assertThat(board.findPieceAt(Position.of(8, 1))).isInstanceOf(Rook.class),

                () -> assertThat(board.findPieceAt(Position.of(1, 2))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(2, 2))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(3, 2))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(4, 2))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(5, 2))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(6, 2))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(7, 2))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(8, 2))).isInstanceOf(Pawn.class),

                () -> assertThat(board.findPieceAt(Position.of(1, 7))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(2, 7))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(3, 7))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(4, 7))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(5, 7))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(6, 7))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(7, 7))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceAt(Position.of(8, 7))).isInstanceOf(Pawn.class),

                () -> assertThat(board.findPieceAt(Position.of(1, 8))).isInstanceOf(Rook.class),
                () -> assertThat(board.findPieceAt(Position.of(2, 8))).isInstanceOf(Knight.class),
                () -> assertThat(board.findPieceAt(Position.of(3, 8))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceAt(Position.of(4, 8))).isInstanceOf(Queen.class),
                () -> assertThat(board.findPieceAt(Position.of(5, 8))).isInstanceOf(King.class),
                () -> assertThat(board.findPieceAt(Position.of(6, 8))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceAt(Position.of(7, 8))).isInstanceOf(Knight.class),
                () -> assertThat(board.findPieceAt(Position.of(8, 8))).isInstanceOf(Rook.class)
        );
    }
}
