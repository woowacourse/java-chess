package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class EmptyTest {

    @Test
    @DisplayName("빈공간을 만든다.")
    void createEmpty() {
        Piece piece = new Empty();

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("빈공간은 움직일 수 없다.")
    void throwsExceptionWithTryToMove() {
        Piece piece = new Empty();
        Point from = Point.of("a3");
        Point to = Point.of("a4");
        Board board = BoardFixtures.empty();

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> piece.move(board.getPointPieces(), from, to));
    }
}
