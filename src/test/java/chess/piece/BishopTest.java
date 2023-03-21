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

public class BishopTest {
    private final Piece bishop = PieceType.BISHOP.createPiece(Camp.WHITE);

    @DisplayName("대각선이 아닌 경우, 이동할 수 없다.")
    @Test
    void cantMoveNotDiagonal() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.TWO);

        Assertions.assertThat(bishop.canMove(source, target))
                .isFalse();
    }

    @DisplayName("대각선일 경우, 이동할 수 있다.")
    @Test
    void canMoveDiagonal() {
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.H, Rank.EIGHT);

        Assertions.assertThat(bishop.canMove(source, target))
                .isTrue();
    }
}
