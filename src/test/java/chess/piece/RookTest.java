package chess.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    private final Piece rook = PieceType.ROOK.createPiece(Camp.WHITE);

    @DisplayName("시작 위치와 목적 위치가 일직선이 아닌 경우, 이동할 수 없다.")
    @Test
    void cantMoveTest() {
        Square source = Square.getInstanceOf(File.A, Rank.ONE);
        Square target = Square.getInstanceOf(File.B, Rank.EIGHT);

        assertThat(rook.canMove(source, target))
                .isFalse();
    }

    @DisplayName("시작 위치와 목적 위치가 일직선인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithSameFile() {
        Square source = Square.getInstanceOf(File.A, Rank.ONE);
        Square target = Square.getInstanceOf(File.A, Rank.EIGHT);

        assertThat(rook.canMove(source, target))
                .isTrue();
    }

    @DisplayName("시작 위치와 목적 위치가 일직선인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithSameRank() {
        Square source = Square.getInstanceOf(File.A, Rank.ONE);
        Square target = Square.getInstanceOf(File.H, Rank.ONE);

        assertThat(rook.canMove(source, target))
                .isTrue();
    }
}
