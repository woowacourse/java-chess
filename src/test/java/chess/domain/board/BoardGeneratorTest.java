package chess.domain.board;

import chess.domain.piece.piecefigure.Rook;
import chess.domain.piece.pieceinfo.TeamType;
import org.junit.jupiter.api.Test;

import static chess.domain.board.BoardInputForTest.DEFAULT_BOARD;
import static org.assertj.core.api.Assertions.assertThat;

class BoardGeneratorTest {
    @Test
    void create() {
        Board board = BoardGenerator.createBoard(DEFAULT_BOARD);

        assertThat(board.getCurrentPiece(Position.of("00"))).isEqualTo(Rook.of(TeamType.BLACK));
        System.out.println(board.getCurrentPiece(Position.of("00")));
    }
}