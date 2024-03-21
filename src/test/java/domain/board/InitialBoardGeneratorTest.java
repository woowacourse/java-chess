package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialBoardGeneratorTest {

    @Test
    @DisplayName("체스판을 초기화한다.")
    void initialize() {
        BoardGenerator boardGenerator = new InitialBoardGenerator();
        Board board = Board.generatedBy(boardGenerator);

        assertAll(
            () -> assertThat(board.findPieceAt(new Position(new File(1), new Rank(8)))).isInstanceOf(Rook.class),
            () -> assertThat(board.findPieceAt(new Position(new File(2), new Rank(8)))).isInstanceOf(Knight.class),
            () -> assertThat(board.findPieceAt(new Position(new File(3), new Rank(8)))).isInstanceOf(Bishop.class),
            () -> assertThat(board.findPieceAt(new Position(new File(4), new Rank(8)))).isInstanceOf(Queen.class),
            () -> assertThat(board.findPieceAt(new Position(new File(5), new Rank(8)))).isInstanceOf(King.class),
            () -> assertThat(board.findPieceAt(new Position(new File(6), new Rank(8)))).isInstanceOf(Bishop.class),
            () -> assertThat(board.findPieceAt(new Position(new File(7), new Rank(8)))).isInstanceOf(Knight.class),
            () -> assertThat(board.findPieceAt(new Position(new File(8), new Rank(8)))).isInstanceOf(Rook.class)
        );
    }
}
