package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.King;
import chess.piece.Piece;
import chess.piece.Pieces;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("입력받은 위치에 존재하는 기물을 반환한다.")
    void findPieceByPosition() {
        // given
        Board board = new Board(new Pieces());
        Position position = new Position(File.E, Rank.ONE);

        // when
        final Piece findPiece = board.findPieceByPosition(position);

        // then
        assertThat(findPiece).isInstanceOf(King.class);
    }
}
