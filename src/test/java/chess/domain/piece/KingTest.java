package chess.domain.piece;

import chess.domain.postion.File;
import chess.domain.postion.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_KING;
import static chess.domain.postion.File.A;
import static chess.domain.postion.Rank.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KingTest {

    @DisplayName("source와 target이 한 칸 차이 아니면 에러 테스트")
    @Test
    void notOneSquare() {
        King king = WHITE_KING;

        assertThatThrownBy(() -> king.canMove(new Position(A, TWO), new Position(A, FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 대각선으로 한 칸 차이일 때 테스트")
    @Test
    void possibleMove1() {
        King king = WHITE_KING;

        assertDoesNotThrow(() -> king.canMove(new Position(A, TWO), new Position(File.B, THREE)));
    }

    @DisplayName("source와 target이 위로 한 칸 차이일 때 테스트")
    @Test
    void possibleMove2() {
        King king = WHITE_KING;

        assertDoesNotThrow(() -> king.canMove(new Position(A, TWO), new Position(A, THREE)));
    }
}
