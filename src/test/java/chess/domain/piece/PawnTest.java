package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("폰은 아래로 한칸 전진할 수 있다.")
    @Test
    void isMovable_moveDown() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> pawn = board.find(Position.from(XAxis.A, YAxis.SEVEN));

        // when
        boolean actual = pawn.get().isMovable(Position.from(XAxis.A, YAxis.SEVEN), Position.from(XAxis.A, YAxis.SIX));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 위로 한칸 전진할 수 있다.")
    @Test
    void isMovable_moveUp() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> pawn = board.find(Position.from(XAxis.A, YAxis.TWO));

        // when
        boolean actual = pawn.get().isMovable(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.THREE));

        // then
        assertThat(actual).isTrue();
    }

}