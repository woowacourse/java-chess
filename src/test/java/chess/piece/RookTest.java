package chess.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    private final Piece rook = PieceType.ROOK.createPiece(Camp.WHITE);

    @DisplayName("시작 위치와 목적 위치가 일직선이 아닌 경우, 이동할 수 없다.")
    @Test
    void cantMoveTest() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.B, Rank.EIGHT);

        Assertions.assertThat(rook.canMove(source, target))
                .isFalse();
    }

    @DisplayName("시작 위치와 목적 위치가 일직선인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithSameFile() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.EIGHT);

        Assertions.assertThat(rook.canMove(source, target))
                .isTrue();
    }

    @DisplayName("시작 위치와 목적 위치가 일직선인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithSameRank() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.H, Rank.ONE);

        Assertions.assertThat(rook.canMove(source, target))
                .isTrue();
    }
}
