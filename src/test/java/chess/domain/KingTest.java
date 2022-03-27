package chess.domain;

import chess.domain.piece.King;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PieceFixture.whiteKing;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KingTest {

    @DisplayName("source와 target이 한 칸 차이 아니면 에러 테스트")
    @Test
    void notOneSquare() {
        King king = whiteKing;

        assertThatThrownBy(() -> king.canMove(WHITE_SOURCE, new Position(File.A, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 대각선으로 한 칸 차이일 때 테스트")
    @Test
    void possibleMove1() {
        King king = whiteKing;

        assertDoesNotThrow(() -> king.canMove(WHITE_SOURCE, new Position(File.B, Rank.THREE)));
    }

    @DisplayName("source와 target이 위로 한 칸 차이일 때 테스트")
    @Test
    void possibleMove2() {
        King king = whiteKing;

        assertDoesNotThrow(() -> king.canMove(WHITE_SOURCE, new Position(File.A, Rank.THREE)));
    }
}
