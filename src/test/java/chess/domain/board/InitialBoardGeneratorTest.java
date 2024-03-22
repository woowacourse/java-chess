package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialBoardGeneratorTest {

    @Test
    @DisplayName("체스판을 초기화한다.")
    void initialize() {
        BoardGenerator boardGenerator = new InitialBoardGenerator();
        Board board = Board.generatedBy(boardGenerator);

        assertAll(
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
