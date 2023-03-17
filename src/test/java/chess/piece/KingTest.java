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

class KingTest {
    private final Piece king = PieceType.KING.createPiece(Camp.WHITE);

    @DisplayName("이동 거리가 2 이상인 경우, 이동할 수 없다.")
    @Test
    void cantMoveOverMovableDistance() {
        Square source = Square.getInstanceOf(File.A, Rank.ONE);
        Square target = Square.getInstanceOf(File.A, Rank.THREE);

        assertThat(king.canMove(source, target))
                .isFalse();
    }

    @DisplayName("이동 거리가 1인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithMovableRankDistance() {
        Square source = Square.getInstanceOf(File.A, Rank.ONE);
        Square target = Square.getInstanceOf(File.A, Rank.TWO);

        assertThat(king.canMove(source, target))
                .isTrue();
    }

    @DisplayName("이동 거리가 1인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithMovableFileDistance() {
        Square source = Square.getInstanceOf(File.A, Rank.ONE);
        Square target = Square.getInstanceOf(File.B, Rank.ONE);

        assertThat(king.canMove(source, target))
                .isTrue();
    }

    @DisplayName("이동 거리가 1인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithMovableDiagonalDistance() {
        Square source = Square.getInstanceOf(File.A, Rank.ONE);
        Square target = Square.getInstanceOf(File.B, Rank.TWO);

        assertThat(king.canMove(source, target))
                .isTrue();
    }
}
