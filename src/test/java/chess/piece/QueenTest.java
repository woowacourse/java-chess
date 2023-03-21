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

class QueenTest {
    private final Piece queen = PieceType.QUEEN.createPiece(Camp.WHITE);

    @DisplayName("대각선 및 직선이 아닌 경우, 이동할 수 없다.")
    @Test
    void cantMoveNotDiagonalAndStraight() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.B, Rank.THREE);

        Assertions.assertThat(queen.canMove(source, target))
                .isFalse();
    }

    @DisplayName("대각선일 경우, 이동할 수 있다.")
    @Test
    void canMoveDiagonal() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.H, Rank.EIGHT);

        Assertions.assertThat(queen.canMove(source, target))
                .isTrue();
    }

    @DisplayName("시작 위치와 목적 위치가 일직선인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithSameFile() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.EIGHT);

        Assertions.assertThat(queen.canMove(source, target))
                .isTrue();
    }

    @DisplayName("시작 위치와 목적 위치가 일직선인 경우, 이동할 수 있다.")
    @Test
    void canMoveTestWithSameRank() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.H, Rank.ONE);

        Assertions.assertThat(queen.canMove(source, target))
                .isTrue();
    }
}
