package chess.domain;

import static chess.domain.BoardFixtures.generateEmptyBoard;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("board의 특정 위치에 기물을 채울 수 있다.")
    @Test
    void 기물을_채운다() {
        Board board = new Board(generateEmptyBoard());

        Position position = new Position("a2");
        Pawn piece = new Pawn(Color.WHITE);
        board.fill(position, piece);

        assertThat(board.findPiece(position)).isEqualTo(piece);
    }
}
