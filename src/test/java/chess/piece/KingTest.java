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

class KingTest {
    private final Piece king = PieceType.KING.createPiece(Camp.WHITE);

    @DisplayName("이동 거리가 2 이상인 경우, 이동할 수 없다.")
    @Test
    void cantMoveOverMovableDistance() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.THREE);

        Assertions.assertThat(king.canMove(source, target))
                .isFalse();
    }

    @DisplayName("이동 거리가 1인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithMovableRankDistance() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.TWO);

        Assertions.assertThat(king.canMove(source, target))
                .isTrue();
    }

    @DisplayName("이동 거리가 1인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithMovableFileDistance() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.B, Rank.ONE);

        Assertions.assertThat(king.canMove(source, target))
                .isTrue();
    }

    @DisplayName("이동 거리가 1인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithMovableDiagonalDistance() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.B, Rank.TWO);

        Assertions.assertThat(king.canMove(source, target))
                .isTrue();
    }

}
